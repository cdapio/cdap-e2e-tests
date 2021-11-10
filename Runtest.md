# How to run the test against a) Local sandbox and b) Remote CDAP instance. 

## Local sandbox: 

Provide the local sandbox URL in ConnectionParameters.properties file. 

In the ConnectionParameters.properties file, provide the local sandbox URL. The path of the property file is 
cdap-e2e-tests>src>main>resource>ConnectionParameters.properties

Property name and its value is CDFURL=http://localhost:11011/pipelines/ns/default/studio
All the configuration details need to be present in this file.

**Configuration Details required for Setup:**

The cdap-e2e is a framework and google-cloud/src/e2e-test is a plugin specific repo, which contains a plugin specific test and step definition.
* cdap-e2e-tests
* google-cloud

_Clone the cdap-e2e-tests framework of develop branch, which contains the common helper classes:_

1. Open IntelliJ IDEA tool.
2. From the File menu select New and choose Project From Version Control option.
3. Provide the Github URL in the URL and click on Clone to clone the project.

_URL: https://github.com/cdapio/cdap-e2e-tests_

4. Provide the required details in the ConnectionParameters.properties file that is common for all the connectors. 

_Please find the list of connectors along with their specific configuration details:-_

1. GCS: When we have GCS as source, we have to provide the path of the file located in the GCS bucket which already exists. All the different file formats paths used for testing are configured here, it also includes the GCP credentials. 

GCSbucket- Provide the bucket path
For example, gs://<bucket>/path/to/directory/
			 project-id= XXXXX

### Add Plugin specific Actions and Locators:

Open cdap-e2e-tests framework. Add the following under pages:

**Add Locators**
1. It contains the Web Element identification, Xpath of the respective fields of the plugin in properties.

**Add Actions**
1. It contains locator actions of what action needs to be performed on the respective field of the plugin in properties.
2. It contains the Util classes example cdfHelper class which contains common methods related to CDF and can implement them in step definitions, and SeleniumHelper class which contains common java functions.
3. After taking the latest pull every time, in the terminal enter maven clean install command to compile. Create a JAR and inject the dependencies in integration project.


_Clone the google-cloud, data-integration framework which contains the plugin specific details:_

1. Now clone the google-cloud and data-integration project. From the File menu select New and choose Project From Version Control option.
2. Provide the Github URL in the URL. Click Clone to clone the project.
_URL: https://github.com/data-integrations/google-cloud_
3. Select Maven and verify if the e2e tests profile is selected. Click reload all maven projects to reload the dependencies.
4. Edit the configurations for cloud service for Test runner to maintain the service account. The service account key has permission to access and connect to the GCP client, which is used for creation and validation.
>GOOGLE_APPLICATION_CREDENTIALS=key [Path of JSON service key]
5. To run the profile enter mvn verify -P e2e-tests command in terminal.
6. After the build is successful, then the target folder will be created automatically.
7. In the console we can see the below screenshot in execution from maven displaying the results.

**Test Runner:**

We can run the feature file and execute from the Test runner. Open the Test runner file and give the feature file to be executed.
The Test Runner file has glue and tags configurations that describe the location and path of the step definition file and tags which are defined in the feature file. It can be specified like below.
glue = {"io.cdap.plugin.gcp.stepsdesign", "stepsdesign"},
tags = {"@BQMT"},

## Remote CDAP instance

Provide the sandbox URL in ConnectionParameters.properties file. 

In the ConnectionParameters.properties file, provide the sandbox URL to access the Remote CDAP instance. The path of the property file is 
cdap-e2e-tests>src>main>resource>ConnectionParameters.properties

Property name and its value is CDFURL=http://*`ip address`*:*`port`*/pipelines/ns/default/studio
