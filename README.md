# E2E-Test Framework

##1. How to run the test against a) Local sandbox and b) Remote CDAP instance.

### Local sandbox:

Provide the local sandbox URL in [ConnectionParameters.properties](src/main/resources/ConnectionParameters.properties) file.

In the ConnectionParameters.properties file, provide the local sandbox URL in
[ConnectionParameters.properties](src/main/resources/ConnectionParameters.properties)

All the configuration details need to be present in this file specific to test.
Here configuration parameters are defined to use everytime wherever required. Changes are done directly here if need to change any particular value. Below parameter values need to be changed to run the test against different sandbox.

Property name and its value is CDFURL=http://localhost:11011/pipelines/ns/default/studio
Project-ID=cdf-athena<br>
@TC-Demo-1_GCS=gs://cdf-athena-test/test.csv<br>
@TC-Demo-2_GCS=gs://cdf-athena-test/test-DataTypes.csv<br>
Data-Set=test_automation<br>
tableDemo=DemoCheck1<br>

**Configuration Details required for Setup:**

1. The cdap-e2e-tests is a framework and plugin specific repo containing plugin specific test and step definition, resides in the main project module.

    Example: google-cloud

_Clone the cdap-e2e-tests framework of develop branch, which contains the common helper classes_

2. Provide the required details in the ConnectionParameters.properties file that is common for all the connectors.

_Example google-cloud for plugin repo with e2e test framework integration._

* Edit the configurations for cloud service for Test runner to maintain the service account. The service account key has permission to access and connect to the GCP client, which is used for creation and validation.
>GOOGLE_APPLICATION_CREDENTIALS=key [Path of JSON service key]
* To run the profile enter mvn verify -P e2e-tests command in terminal.
* After the build is successful, then the target folder will be created automatically.
* In the console, check the execution from maven displaying the results.

**Test Runner:**

We can run the feature file and execute from the Test runner. Open the Test runner file and give the feature file to be executed.
The Test Runner file has glue and tags configurations that describe the location and path of the step definition file and tags which are defined in the feature file. It can be specified like below.
glue = {"io.cdap.plugin.gcp.stepsdesign", "stepsdesign"},
tags = {"@BQMT"},

### Remote CDAP instance

Provide the CDAP URL in ConnectionParameters.properties file.

In the ConnectionParameters.properties file, provide the CDAP URL to access the Remote CDAP instance here in
[ConnectionParameters.properties](src/main/resources/ConnectionParameters.properties)

Property name and its value:

CDFURL=[http://*`ip address`*:*`port`*/pipelines/ns/default/studio]()

##2. How to debug test failures. where logs and screenshots can be found. REFER [Debugtest.md](Debugtest.md)
##3. (a) How to add new tests. ex - for new plugin in google-cloud. (b) How to enable e2e tests for a new plugin repository. REFER [Addnewplugin.md](Addnewplugin.md)