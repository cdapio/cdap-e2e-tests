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

import json
import requests
import jq
import sys

# Get the Package name and Package version.
package_name = sys.argv[1]
package_version = sys.argv[2]


# Get the specs of the Plugin from response.
res = requests.get(f"https://hub.cdap.io/v2/packages/{package_name}/{package_version}/spec.json")
assert res.ok or print(f"Specs not found for the Plugin. Status code: {res.status_code}")
response_data = res.json()
actions = response_data["actions"][0]
plugin_details = actions["arguments"]

# Get the required details from specs.
plugin_name = plugin_details[0]["value"]
plugin_version = plugin_details[1]["value"]
plugin_config = plugin_details[3]["value"]
plugin_jar = plugin_details[4]["value"]

# Get the Jar content of the Plugin from the Hub and upload the content to CDAP namespace.
jar_file = requests.get(f"https://hub.cdap.io/v2/packages/{package_name}/{plugin_version}/{plugin_jar}")

res = requests.post(
    f"http://localhost:11015/v3/namespaces/default/artifacts/{plugin_name}",
    headers = {
        "Content-Type": "application/octet-stream",
        "Artifact-Version": plugin_version,
        },
    data = jar_file.content,
)
assert res.ok or print(res.text)

# Get the Json content of Plugin from the Hub and upload the content.
json_file = requests.get(f"https://hub.cdap.io/v2/packages/{package_name}/{plugin_version}/{plugin_config}")

# Getting the properties of the plugin from json response.
data = json_file.json()
filter_expression = jq.compile('.properties')
filtered_data = filter_expression.input(data).all()
data_to_upload = json.dumps(filtered_data[0])

# Uploading the Json content.
res = requests.put(
    f"http://localhost:11015/v3/namespaces/default/artifacts/{plugin_name}/versions/{plugin_version}/properties",
    headers = {
        "Content-Type": "application/json",
        },
    data = data_to_upload,
)
assert res.ok or print(res.text)
