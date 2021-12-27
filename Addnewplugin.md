# How to add e2e test for new Plugin:

## Add New Tests

1. All the automation code should strictly be under the src/e2e-test/ of main project repo for which automation needs to be done.
2. For logging: Place logback-test.xml under src/e2e-test/resources folder and give this location in resources tag under build section of pom profile, and also add logback-classic dependency.

**Configuration Details required for Setup:**

1. Create a maven profile in the main project for which e2e tests are required, with a dependency of e2e test framework artifact, along with your Runner file inside the failsafe plugin.

NOTE: All the properties/ actions/ locators which are common to plugins, need to be or will be present in the framework code. Plugin specific properties/ actions/ locators should reside in the main project module (for eg: google-cloud).

2. TestRunners and its step definition should also reside in the main project module.
3. Specify service account credentials by setting environment variable, GOOGLE_APPLICATION_CREDENTIALS=Path of JSON service key.

**Add Feature:**
>Refer [documentation](https://cucumber.io/docs/gherkin/reference/) about features.

1. All the Feature files are at path src/e2e-test/features/.
2. Plugin specific code is under their respective package name at src/e2e-test/java/io/cdap/plugin/ with below sub packages:

   **a. tests.runner**</br>It contains TestRunner which will glue the feature file with the stepsdesign.</br>
   **b. stepsdesign**</br>It maps the test case steps in the feature files(by Given/When/Then).</br>
   **c. actions**</br>It contains locator actions of what action needs to be performed on the respective field.</br>
   **d. locators**</br>It contains the Web Element identification of the respective field.

## Enable e2e tests for new plugin repository

1. TestRunner.java is included in e2e-tests profile (in pom.xml) to enable the e2e tests for any new plugin.
2. Make sure that the TestRunner's tags option should match with the feature's scenario tags.
