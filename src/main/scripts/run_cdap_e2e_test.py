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

import os
import subprocess
import zipfile
import shutil
import argparse
import re
import configparser
import sys


def run_shell_command(cmd):
    process = subprocess.run(cmd.split(" "), stderr=subprocess.PIPE)
    if process.returncode != 0:
        print("Process completed with error: ", process.stderr)
    assert process.returncode == 0


def get_sandbox_version(directory_path):
    pattern = re.compile(r"cdap-sandbox-(\d+\.\d+\.\d+)-SNAPSHOT.zip")
    # Iterate over files and folders in the directory
    for entry in os.listdir(directory_path):
        if pattern.match(entry):
            version_match = pattern.search(entry)
            if version_match:
                version = version_match.group(1)
                return version


def get_hydrator_branch(gitmodules_path, submodule_path):
    config = configparser.ConfigParser()
    config.read(gitmodules_path)

    # Check if the submodule exists in the .gitmodules file
    if config.has_section(f"submodule \"{submodule_path}\""):
        branch = config.get(f"submodule \"{submodule_path}\"", "branch")
        return branch
    else:
        return None


# Parse command line optional arguments
parser = argparse.ArgumentParser()
parser.add_argument('--testRunner', help='TestRunner class to execute tests')
parser.add_argument('--cdapBranch', help='CDAP branch against which PR is raised')
args = parser.parse_args()

print("Building CDAP Sandbox")
os.chdir("./plugin")
run_shell_command("git submodule update --init --recursive --remote")
run_shell_command("mvn clean install -DskipTests -P templates,spark-dev")
my_env = os.environ.copy()
my_env["MAVEN_OPTS"] = "-Xmx1024m -XX:MaxPermSize=128m"
my_env["_JAVA_OPTIONS"] = "-Xmx32G"

# Building the plugins
os.chdir("..")
# Extracting hydrator-plugins branch from cdap-build gitmodules.
cdap_branch = ""
if args.cdapBranch:
    cdap_branch = args.cdapBranch

if not cdap_branch:
    print("Error: cdapBranch is empty.")
    sys.exit(1)

cdap_build_branch = cdap_branch
cdap_build_repository_url = "https://github.com/cdapio/cdap-build.git"
subprocess.run(["git", "clone", "-b", cdap_build_branch, cdap_build_repository_url])
# .gitmodules file path and hydrator submodule path.
gitmodules_path = "./cdap-build/.gitmodules"
submodule_path = "app-artifacts/hydrator-plugins"
branch = get_hydrator_branch(gitmodules_path, submodule_path)

if branch is not None:
    print(f"The branch of submodule '{submodule_path}' is: {branch}")
else:
    print(f"Submodule '{submodule_path}' not found in .gitmodules file.")

hydrator_repository_branch = branch
hydrator_repository_url = "https://github.com/cdapio/hydrator-plugins.git"
subprocess.run(["git", "clone", "-b", hydrator_repository_branch, hydrator_repository_url])
os.chdir("./hydrator-plugins")
run_shell_command("git submodule update --init --recursive --remote")
run_shell_command("mvn clean install -DskipTests")
os.chdir("../plugin")
# Building the sandbox
run_shell_command('mvn clean package -pl cdap-standalone,cdap-app-templates/cdap-etl -am -amd -DskipTests -P '
                  f'templates,dist,release,unit-tests -Dadditional.artifacts.dir=../../hydrator-plugins/')
os.chdir("./cdap-standalone/target")
sandbox_version = get_sandbox_version(os.getcwd())
sandbox_zip = f"cdap-sandbox-{sandbox_version}-SNAPSHOT.zip"
with zipfile.ZipFile(sandbox_zip, 'r') as z:
    z.extractall(os.getcwd())

sandbox_dir = sandbox_zip.replace(".zip", "")
run_shell_command(f"chmod +x {sandbox_dir}/bin/cdap")
sandbox_start_cmd = f"{sandbox_dir}/bin/cdap sandbox restart"
process = subprocess.Popen(f"{sandbox_start_cmd}", shell=True, env=my_env)
process.communicate()
assert process.returncode == 0
# Run e2e tests
print("Running e2e integration tests for CDAP")
testrunner_to_run = ""
if args.testRunner:
    testrunner_to_run = args.testRunner
os.chdir("../../..")
assertion_error = None
try:
    os.chdir("./plugin/cdap-e2e-tests")
    if testrunner_to_run:
        print("TestRunner to run : " + testrunner_to_run)
        run_shell_command(f"mvn verify -P e2e-tests -DTEST_RUNNER={testrunner_to_run}")
    else:
        run_shell_command(f"mvn verify -P e2e-test")
except AssertionError as e:
    assertion_error = e
finally:
    os.chdir("../..")

cwd = os.getcwd()
print("Copying sandbox logs to e2e-debug")
shutil.copytree(cwd + "/sandbox/" + sandbox_dir + "/data/logs",
                cwd + "/plugin/target/cdap-e2e-tests/e2e-debug/sandbox/data/logs")
shutil.copytree(cwd + "/sandbox/" + sandbox_dir + "/logs", cwd + "/plugin/cdap-e2e-tests/target/e2e-debug/sandbox/logs")
if assertion_error != None:
    raise assertion_error
