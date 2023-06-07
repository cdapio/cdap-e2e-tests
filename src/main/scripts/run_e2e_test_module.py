# Copyright © 2021 Cask Data, Inc.
#
# Licensed under the Apache License, Version 2.0 (the "License"); you may not
# use this file except in compliance with the License. You may obtain a copy of
# the License at

# http://www.apache.org/licenses/LICENSE-2.0

# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
# WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
# License for the specific language governing permissions and limitations under
# the License.

import io
import json
import os
import requests
import subprocess
import sys
import xml.etree.ElementTree as ET
import zipfile
import shutil

def run_shell_command(cmd):
    process = subprocess.run(cmd.split(" "), stderr=subprocess.PIPE)
    if process.returncode != 0:
        print("Process completed with error: ", process.stderr)
    assert process.returncode == 0

if __name__ == "__main__":
    # Start CDAP sandbox
    print("Downloading CDAP sandbox")
    sandbox_url = "https://github.com/cdapio/cdap-build/releases/download/latest/cdap-sandbox-6.10.0-SNAPSHOT.zip"
    sandbox_dir = sandbox_url.split("/")[-1].split(".zip")[0]
    r = requests.get(sandbox_url)
    z = zipfile.ZipFile(io.BytesIO(r.content))
    z.extractall("./sandbox")
    print("Start the sandbox")
    run_shell_command(f"chmod +x sandbox/{sandbox_dir}/bin/cdap")
    os.system("export JAVA_OPTS=-Xmx24G")
    os.system("export SPARK_COMPAT=spark3_2.12")
    run_shell_command(f"sandbox/{sandbox_dir}/bin/cdap sandbox start")
   
    
    # Build the plugin
    os.chdir("plugin")
    module_to_build : str = sys.argv[1]
    print(f"Building plugin {module_to_build}")
    run_shell_command(f"mvn clean package -pl {module_to_build} -am -DskipTests")
    # Get plugin artifact name and version from pom.xml.
    os.chdir(f"{module_to_build}")
    root = ET.parse('pom.xml').getroot()
    plugin_name = root.find('{http://maven.apache.org/POM/4.0.0}artifactId').text
    plugin_version = root.find('{http://maven.apache.org/POM/4.0.0}version').text
    os.chdir("target")
    plugin_properties = {}
    plugin_parents = []
    # Get plugin properties and parent from plugin json.
    with open(f'{plugin_name}-{plugin_version}.json') as f:
        obj = json.loads(f.read())
        plugin_properties = obj['properties']
        plugin_parents = obj['parents']

    data = None
    with open(f'{plugin_name}-{plugin_version}.jar', 'rb') as f:
        data = f.read()

    # Install the plugin on the sandbox.
    print("Installing plugin")
    res=requests.post(f"http://localhost:11015/v3/namespaces/default/artifacts/{plugin_name}", headers={"Content-Type": "application/octet-stream", "Artifact-Extends": '/'.join(plugin_parents), "Artifact-Version": plugin_version}, data=data)
    assert res.ok or print(res.text)
    res=requests.put(f"http://localhost:11015/v3/namespaces/default/artifacts/{plugin_name}/versions/{plugin_version}/properties", json=plugin_properties)
    assert res.ok or print(res.text)
   
    #Building Framework
    os.chdir("../../../e2e")
    print("Preparing e2e framework")
    run_shell_command("mvn clean install")
    os.chdir("../plugin")
    
    # Run e2e tests
    print("Running e2e integration tests")
    assertion_error = None
    try:
        run_shell_command(f"mvn verify -pl {module_to_build} -amd -P e2e-tests")
    except AssertionError as e:
        assertion_error = e
    finally:
        os.chdir("..")
             
cwd = os.getcwd()
print("Copying sandbox logs to e2e-debug")
shutil.copytree(cwd+"/sandbox/"+sandbox_dir+"/data/logs", cwd+"/plugin/target/e2e-debug/sandbox/data/logs")
shutil.copytree(cwd+"/sandbox/"+sandbox_dir+"/logs", cwd+"/plugin/target/e2e-debug/sandbox/logs")
if assertion_error != None:
    raise assertion_error
