# E2E-Test Framework

##1. How to run the e2e test against :

### a) Local CDAP sandbox:

Provide the local CDAP sandbox URL in [connectionParameters.properties](src/main/resources/connectionParameters.properties) file.
>cdfurl=http://localhost:11011/pipelines/ns/default/studio

Other keys (like @TC-Demo-1_GCS, Data-Set, etc) can be modified as needed. <br>

**Configuration Details required for Setup:**

1. Create a maven profile in the main project for which e2e tests are required, with a dependency of the framework artifact, along with your Runner file inside the failsafe plugin.

NOTE: All the properties/ actions/ locators which are common to plugins, need to be or will be present in the framework code. Plugin specific properties/ actions/ locators should reside in the main project module (for eg: google-cloud).

2. TestRunners and its step definition should also resides in the main project module. 


3. The service account file has permission to access and connect to the GCP client, which is used for creation and validation.
   Environment variable for 'Service account of cloud service': 
>GOOGLE_APPLICATION_CREDENTIALS=*`Path of JSON service key`*

### b) Remote CDAP instance:

Modify the key cdfurl in [connectionParameters.properties](src/main/resources/connectionParameters.properties) to connect to remote CDAP instance.
>cdfurl=[http://*`ip address`*:*`port`*/pipelines/ns/default/studio]()

##2. How to debug test failures. where logs and screenshots can be found. REFER [Debugtest.md](Debugtest.md)
##3. (a) How to add new tests. ex - for new plugin in google-cloud. (b) How to enable e2e tests for a new plugin repository. REFER [Addnewplugin.md](Addnewplugin.md)