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
import xml.etree.ElementTree as ET
import zipfile
import shutil
import sys
import argparse
import urllib.request

def run_shell_command(cmd):
    process = subprocess.run(cmd.split(" "), stderr=subprocess.PIPE)
    if process.returncode != 0:
        print("Process completed with error: ", process.stderr)
    assert process.returncode == 0

# Parse command line optional arguments
parser=argparse.ArgumentParser()
parser.add_argument('--testRunner', help='TestRunner class to execute tests')
parser.add_argument('--module', help='Module for which tests need to be run')
parser.add_argument('--framework', help='Pass this param if workflow is triggered from e2e framework repo')
args=parser.parse_args()

# Start CDAP sandbox
print("Downloading CDAP sandbox")
sandbox_url = "https://builds.cask.co/artifact/CDAP-BUT/shared/build-latestSuccessful/SDK/cdap/cdap-standalone/target/cdap-sandbox-6.8.0-SNAPSHOT.zip"
sandbox_dir = sandbox_url.split("/")[-1].split(".zip")[0]
r = requests.get(sandbox_url)
z = zipfile.ZipFile(io.BytesIO(r.content))
z.extractall("./sandbox")

print("Installing gcs connector jar")
gcs_jar_url = "https://storage.googleapis.com/hadoop-lib/gcs/gcs-connector-hadoop2-2.2.5.jar"
gcs_jar_fname = f"sandbox/{sandbox_dir}/lib/gcs-connector-hadoop2-2.2.5.jar"
urllib.request.urlretrieve(gcs_jar_url, gcs_jar_fname)

print("Start the sandbox")
run_shell_command(f"chmod +x sandbox/{sandbox_dir}/bin/cdap")
my_env = os.environ.copy()
my_env["_JAVA_OPTIONS"] = "-Xmx24G"
sandbox_start_cmd = "sandbox/" + sandbox_dir + "/bin/cdap sandbox restart"
process = subprocess.Popen(sandbox_start_cmd, shell=True, env=my_env)
process.communicate()
assert process.returncode == 0

module_to_build = ""
if args.module:
    module_to_build = args.module

# Build the plugin
os.chdir("plugin")
if module_to_build:
    print(f"Building plugin module: {module_to_build}")
    run_shell_command(f"mvn clean package -pl {module_to_build} -am -DskipTests")
else:
    print("Building plugin")
    run_shell_command("mvn clean package -DskipTests")

# Get plugin artifact name and version from pom.xml.
root = ET.parse('pom.xml').getroot()
plugin_version = root.find('{http://maven.apache.org/POM/4.0.0}version').text
if module_to_build:
    os.chdir(f"{module_to_build}")
    root = ET.parse('pom.xml').getroot()
    plugin_name = root.find('{http://maven.apache.org/POM/4.0.0}artifactId').text
else:
    plugin_name = root.find('{http://maven.apache.org/POM/4.0.0}artifactId').text

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

if module_to_build:
    os.chdir("../../..")
else:
    os.chdir("../..")
print("cwd:", os.getcwd())
print("ls:", os.listdir())

# Run e2e tests
if args.framework:
    print("Preparing e2e framework")
    os.chdir("e2e")
    run_shell_command("mvn clean install")
    os.chdir("../plugin")
else:
    os.chdir("plugin")
    run_shell_command("mvn dependency:purge-local-repository -DmanualInclude=io.cdap.tests.e2e:cdap-e2e-framework")

print("Running e2e integration tests")

testrunner_to_run = ""
if args.testRunner:
    testrunner_to_run = args.testRunner

try:
    if module_to_build:
        if testrunner_to_run:
            print("TestRunner to run : " + testrunner_to_run)
            run_shell_command(f"mvn verify -P e2e-tests -pl {module_to_build} -DTEST_RUNNER={testrunner_to_run}")
        else:
            run_shell_command(f"mvn verify -P e2e-tests -pl {module_to_build}")
    else:
        if testrunner_to_run:
            print("TestRunner to run : " + testrunner_to_run)
            run_shell_command(f"mvn clean verify -P e2e-tests -DTEST_RUNNER={testrunner_to_run}")
        else:
            run_shell_command("mvn clean verify -P e2e-tests")
except AssertionError as e:
    raise e
finally:
    os.chdir("..")
    cwd = os.getcwd()
    print("Copying sandbox logs to e2e-debug")
    if module_to_build:
        shutil.copytree(cwd+"/sandbox/"+sandbox_dir+"/data/logs", cwd+"/plugin/"+module_to_build+"/target/e2e-debug/sandbox/data/logs")
        shutil.copytree(cwd+"/sandbox/"+sandbox_dir+"/logs", cwd+"/plugin/"+module_to_build+"/target/e2e-debug/sandbox/logs")
    else:
        shutil.copytree(cwd+"/sandbox/"+sandbox_dir+"/data/logs", cwd+"/plugin/target/e2e-debug/sandbox/data/logs")
        shutil.copytree(cwd+"/sandbox/"+sandbox_dir+"/logs", cwd+"/plugin/target/e2e-debug/sandbox/logs")
