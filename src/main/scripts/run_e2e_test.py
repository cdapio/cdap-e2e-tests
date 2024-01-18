# Copyright © 2023 Cask Data, Inc.
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
import yaml
import re


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
parser.add_argument('--mvnTestRunProfiles', help='Maven build profiles to run the e2e tests')
parser.add_argument('--mvnProjectBuildProfiles', help='Maven project build profiles')
args=parser.parse_args()

# Start CDAP sandbox
print("Downloading CDAP sandbox")
sandbox_url = "https://github.com/cdapio/cdap-build/releases/download/latest/cdap-sandbox-6.11.0-SNAPSHOT.zip"
sandbox_dir = sandbox_url.split("/")[-1].split(".zip")[0]
r = requests.get(sandbox_url)
z = zipfile.ZipFile(io.BytesIO(r.content))
z.extractall("./sandbox")

print("Installing gcs connector jar")
gcs_jar_url = "https://storage.googleapis.com/hadoop-lib/gcs/gcs-connector-hadoop2-2.2.16.jar"
gcs_jar_fname = f"sandbox/{sandbox_dir}/lib/gcs-connector-hadoop2-2.2.9.jar"
urllib.request.urlretrieve(gcs_jar_url, gcs_jar_fname)

print("Start the sandbox")
run_shell_command(f"chmod +x sandbox/{sandbox_dir}/bin/cdap")
my_env = os.environ.copy()
my_env["_JAVA_OPTIONS"] = "-Xmx32G"
sandbox_start_cmd = "sandbox/" + sandbox_dir + "/bin/cdap sandbox restart"
process = subprocess.Popen(sandbox_start_cmd, shell=True, env=my_env)
process.communicate()
assert process.returncode == 0

# Setting the task executor memory
res = requests.put('http://localhost:11015/v3/preferences', headers= {'Content-Type': 'application/json'}, json={'task.executor.system.resources.memory': 4096})
assert res.ok or print(res.text)

# Upload required plugins from CDAP Hub
plugin_details_file = open(os.path.join('e2e', 'src', 'main', 'scripts', 'required_plugins.yaml'))
plugin_details = yaml.load(plugin_details_file, Loader=yaml.FullLoader)

for plugin, details in plugin_details['plugins'].items():
    artifact_name = details.get('artifact_name')
    artifact_version = details.get('artifact_version')
    subprocess.run(["python3.8", os.path.join('e2e', 'src', 'main', 'scripts', 'upload_required_plugins.py'), artifact_name, artifact_version])

module_to_build = ""
if args.module:
    module_to_build = args.module

# Build the plugin
os.chdir("plugin")
if module_to_build:
    print(f"Building plugin module: {module_to_build}")
    if args.mvnProjectBuildProfiles:
        run_shell_command(f"mvn clean install -pl {module_to_build} -am -DskipTests -P {args.mvnProjectBuildProfiles}")
    else:
        run_shell_command(f"mvn clean install -pl {module_to_build} -am -DskipTests")
else:
    print("Building plugin")
    if args.mvnProjectBuildProfiles:
        run_shell_command(f"mvn clean package -DskipTests -P {args.mvnProjectBuildProfiles}")
    else:
        run_shell_command("mvn clean package -DskipTests")

# Get plugin artifact name and version from pom.xml.
plugin_name = ""
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

def upload_drivers(module_to_build):
    driver_details_file = open(os.path.join('e2e', 'src', 'main', 'scripts', 'driver_details.yaml'))
    driver_details = yaml.load(driver_details_file, Loader=yaml.FullLoader)
    if module_to_build in driver_details['modules']:
        url = driver_details['modules'][module_to_build]['url']
        driver_prop = str(driver_details['modules'][module_to_build]['driver_prop'])
        artifact_name = driver_details['modules'][module_to_build]['artifact_name']
        artifact_version = driver_details['modules'][module_to_build]['artifact_version']
        get_driver_jar = requests.get(url)
        assert get_driver_jar.ok or print(get_driver_jar.text)
        driverData = get_driver_jar.content
        print(f"Installing {artifact_name} driver")
        res=requests.post(f"http://localhost:11015/v3/namespaces/default/artifacts/{artifact_name}", headers={"Content-Type": "application/octet-stream", "Artifact-Version": artifact_version, "Artifact-Plugins": driver_prop}, data=driverData)
        assert res.ok or print(res.text)

if module_to_build:
    os.chdir("../../..")
    upload_drivers(module_to_build)
else:
    os.chdir("../..")

# Uploading MySQL Driver while building BQMT-Plugin in Google Cloud repo.
# Checking if the submodule to build is BigQuery Multi Table, only then driver should be downloaded.
if plugin_name == 'google-cloud':
    input_string = args.testRunner
    pattern = r'bigquerymultitable'
    submodule = re.search(pattern, input_string)
    desired_module = ''
    if submodule:
        desired_module = submodule.group(0)
        if desired_module == 'bigquerymultitable':
            print("Uploading MySql Driver.")
            upload_drivers('mysql-plugin')

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

testprofile_to_run = "e2e-tests"
if args.mvnTestRunProfiles:
    testprofile_to_run = args.mvnTestRunProfiles

try:
    if module_to_build:
        if testrunner_to_run:
            print("TestRunner to run : " + testrunner_to_run)
            run_shell_command(f"mvn verify -P {testprofile_to_run} -pl {module_to_build} -DTEST_RUNNER={testrunner_to_run}")
        else:
            run_shell_command(f"mvn verify -P {testprofile_to_run} -pl {module_to_build}")
    else:
        if testrunner_to_run:
            print("TestRunner to run : " + testrunner_to_run)
            run_shell_command(f"mvn clean verify -P {testprofile_to_run} -DTEST_RUNNER={testrunner_to_run}")
        else:
            run_shell_command(f"mvn clean verify -P {testprofile_to_run}")
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
