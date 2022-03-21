/*
 * Copyright © 2021 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package stepsdesign;

import io.cdap.e2e.pages.actions.CdfLogActions;
import io.cdap.e2e.pages.actions.CdfPipelineRunAction;
import io.cdap.e2e.pages.actions.CdfPluginPropertiesActions;
import io.cdap.e2e.pages.actions.CdfStudioActions;
import io.cdap.e2e.pages.locators.CdfStudioLocators;
import io.cdap.e2e.utils.CdfHelper;
import io.cdap.e2e.utils.ConstantsUtil;
import io.cdap.e2e.utils.PluginPropertyUtils;
import io.cdap.e2e.utils.SeleniumHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CDF pipeline related step design.
 */
public class PipelineSteps implements CdfHelper {
  private static final Logger logger = LoggerFactory.getLogger(PipelineSteps.class);
  public static Map<String, String> runtimeArguments = new HashMap<>();
  public static String pipelineName;

  static {
    SeleniumHelper.getPropertiesLocators(CdfStudioLocators.class);
  }

  List<String> propertiesSchemaColumnList = new ArrayList<>();
  Map<String, String> sourcePropertiesOutputSchema = new HashMap<>();

  @Given("Open Datafusion Project to configure pipeline")
  public void openDatafusionProjectToConfigurePipeline() throws IOException, InterruptedException {
    openCdf();
  }

  @When("Select data pipeline type as: {string}")
  public void selectDataPipelineType(String type) {
    CdfStudioActions.selectDataPipelineType(type);
  }

  @When("Expand Plugin group in the LHS plugins list: {string}")
  public void expandPluginGroup(String pluginGroup) {
    CdfStudioActions.expandPluginGroupIfNotAlreadyExpanded(pluginGroup);
  }

  @When("Select plugin: {string} from the plugins list as: {string}")
  public void selectPlugin(String pluginName, String pluginGroupName) {
    logger.info("Click on plugin: " + pluginName + " from the Plugin group: " + pluginGroupName);
    CdfStudioActions.selectPluginFromList(pluginName);
  }

  @When("Select Sink plugin: {string} from the plugins list")
  public void selectSinkPlugin(String pluginName) {
    CdfStudioActions.selectSinkPlugin(pluginName);
  }

  @When("Select Realtime Source plugin: {string} from the plugins list")
  public void selectRealtimeSourcePlugin(String pluginName) {
    CdfStudioActions.selectRealtimeSourcePlugin(pluginName);
  }

  @When("Navigate to the properties page of plugin: {string}")
  public void navigateToPluginPropertiesPage(String pluginName) {
    CdfStudioActions.navigateToPluginPropertiesPage(pluginName);
  }

  @When("Close the Plugin Properties page")
  public void closePluginPropertiesPage() {
    CdfPluginPropertiesActions.clickCloseButton();
  }

  @Then("Connect source as {string} and sink as {string} to establish connection")
  public void connectSourceAndSinkToEstablishConnection(String source, String sink) {
    connectSourceAndSink(source, sink);
  }

  @Then("Connect source as {string} having title {string} and sink{int} as {string} having title {string} " +
    "to establish connection")
  public void connectSourceAsHavingTitleAndSinkAsHavingTitleToEstablishConnection(String source, String sourceTitle
    , int index, String sink, String sinkTitle) {
    CdfStudioActions.connectSourceAndSink(source, sourceTitle, index, sink, sinkTitle);
  }

  @When("Click on the Validate button")
  public void clickOnValidateButton() {
    CdfPluginPropertiesActions.clickValidateButton();
  }

  @Then("Validate {string} plugin properties")
  public void validatePluginProperties(String plugin) {
    logger.info("Validate plugin: " + plugin + " and verify validation success message");
    CdfPluginPropertiesActions.clickValidateButton();
    CdfPluginPropertiesActions.verifyIfPluginIsValidatedSuccessfully();
  }

  @Then("Validate mandatory property error for {string}")
  public void validateMandatoryPropertyErrorFor(String property) {
    CdfPluginPropertiesActions.clickValidateButton();
    CdfPluginPropertiesActions.verifyRequiredPropertyHasNoValueErrorMessage(property);
    CdfPluginPropertiesActions.verifyPluginPropertyInlineErrorMessageColor(property);
  }

  @Then("Verify mandatory property error for below listed properties:")
  public void verifyMandatoryPropertyErrorForListOfProperties(DataTable table) {
    List<String> list = table.asList();

    for (String property : list) {
      CdfPluginPropertiesActions.verifyRequiredPropertyHasNoValueErrorMessage(property);
      CdfPluginPropertiesActions.verifyPluginPropertyInlineErrorMessageColor(property);
    }
  }

  @Then("Verify invalid credentials validation message for below listed properties:")
  public void verifyInvalidCredentialsValidationMessageForListOfProperties(DataTable table) {
    List<String> list = table.asList();

    for (String property : list) {
      CdfPluginPropertiesActions.verifyInvalidCredentialsErrorMessage(property);
      CdfPluginPropertiesActions.verifyPluginPropertyInlineErrorMessageColor(property);
    }
  }

  @Then("Verify that the Plugin Property: {string} is displaying an in-line error message: {string}")
  public void verifyInLineErrorMessageDisplayedOnPluginProperty(String propertyName, String errorMessageLocation) {
    CdfPluginPropertiesActions.verifyPluginPropertyInlineError(propertyName, errorMessageLocation);
  }

  @Then("Verify that the Plugin is displaying an error message: {string} on the header")
  public void verifyErrorMessageDisplayedOnPluginHeader(String errorMessageLocation) {
    CdfPluginPropertiesActions.verifyErrorMessageOnHeader(errorMessageLocation);
  }

  @Then("Verify plugin properties validation fails with {int} error")
  public void verifyPluginPropertiesValidationFailsWithError(int errorCount) {
    CdfPluginPropertiesActions.clickValidateButton();
    CdfPluginPropertiesActions.verifyPluginPropertiesValidationFailsWithErrorMessage(errorCount);
  }

  @Then("Validate output schema with expectedSchema {string}")
  public void validateOutputSchemaWithExpectedSchema(String schemaJsonArray) {
    CdfPluginPropertiesActions.clickGetSchemaButton();
    propertiesSchemaColumnList = CdfPluginPropertiesActions.getListOfFieldsFromOutputSchema();
    sourcePropertiesOutputSchema = CdfPluginPropertiesActions.getOutputSchema();
    CdfPluginPropertiesActions.verifyOutputSchemaMatchesExpectedSchema(schemaJsonArray);
  }

  @When("Click on the Get Schema button")
  public void clickOnGetSchemaButton() {
    CdfPluginPropertiesActions.clickGetSchemaButton();
  }

  @Then("Capture the generated Output Schema")
  public void getOutputSchema() {
    propertiesSchemaColumnList = CdfPluginPropertiesActions.getListOfFieldsFromOutputSchema();
    sourcePropertiesOutputSchema = CdfPluginPropertiesActions.getOutputSchema();
  }

  @Then("Save the pipeline")
  public void saveThePipeline() {
    pipelineName = "TestPipeline-" + RandomStringUtils.randomAlphanumeric(10);
    CdfStudioActions.fillPipelineNameAndSave(pipelineName);
  }

  @Then("Preview and run the pipeline")
  public void previewAndRunThePipeline() {
    CdfStudioActions.openPipelinePreviewMenuAndClickOnRunButton();
  }

  @Then("Enter runtime argument value {string} for key {string}")
  public void enterRuntimeArgumentValueForKey(String value, String runtimeArgumentKey) {
    CdfStudioActions.enterRuntimeArgumentValue(runtimeArgumentKey, PluginPropertyUtils.pluginProp(value));
  }

  @Then("Enter runtime argument value from environment variable {string} for key {string}")
  public void enterRuntimeArgumentValueFromEnvironmentVariableForKey(String envVariableKey,
                                                                     String runtimeArgumentKey) {
    CdfStudioActions.enterRuntimeArgumentValue(runtimeArgumentKey,
                                               System.getenv(PluginPropertyUtils.pluginProp(envVariableKey)));
  }

  @Then("Run the preview of pipeline with runtime arguments")
  public void previewAndRunThePipelineWithRuntimeArguments() {
    CdfStudioActions.clickPreviewConfigRunButton();
  }

  @Then("Wait till pipeline preview is in running state")
  public void waitTillPipelinePreviewIsInRunningState() {
    CdfStudioActions.waitTillPipelinePreviewRunCompletes();
  }

  @Then("Wait till pipeline preview is in running state with a timeout of {long} seconds")
  public void waitTillPipelinePreviewIsInRunningState(long timeoutInSeconds) {
    CdfStudioActions.waitTillPipelinePreviewRunCompletes(timeoutInSeconds);
  }

  @Then("Open and capture pipeline preview logs")
  public void openAndCapturePipelinePreviewLogs() {
    CdfStudioActions.closeStatusBannerIfDisplayed();
    CdfStudioActions.clickPreviewLogsButton();
    try {
      String rawLogs = CdfPipelineRunAction.captureRawLogs();
      String logsSeparatorMessage = ConstantsUtil.LOGS_SEPARATOR_MESSAGE
        .replace("MESSAGE", "PIPELINE PREVIEW RUN LOGS");
      BeforeActions.scenario.write(rawLogs);
      CdfPipelineRunAction.writeRawLogsToFile(BeforeActions.file, logsSeparatorMessage, rawLogs);
    } catch (Exception e) {
      BeforeActions.scenario.write("Exception in capturing logs : " + e);
    }
  }

  @Then("Verify the preview run status of pipeline in the logs is {string}")
  public void verifyThePreviewRunStatusOfOfPipelineInTheLogsIs(String previewStatus) {
    CdfStudioActions.verifyPipelinePreviewStatusInLogs(previewStatus);
  }

  @Then("Close the pipeline logs")
  public void closeThePipelineLogs() {
    CdfLogActions.closeLogs();
  }

  @Then("Verify the preview of pipeline is {string}")
  public void verifyThePreviewOfPipelineIs(String previewStatus) {
    CdfStudioActions.verifyPipelinePreviewStatus(previewStatus);
  }

  @Then("Verify the preview of pipeline is {string} with a timeout of {long} seconds")
  public void verifyThePreviewOfPipelineIs(String previewStatus, long timeoutInSeconds) {
    CdfStudioActions.verifyPipelinePreviewStatus(previewStatus, timeoutInSeconds);
  }

  @Then("Verify preview output schema matches the outputSchema captured in properties")
  public void verifyPreviewOutputSchemaMatchesTheOutputSchemaCapturedInProperties() {
    CdfPluginPropertiesActions
      .verifyInputRecordsTableColumnsUnderPreviewTabMatchesInputSchemaFields(propertiesSchemaColumnList);
    CdfPluginPropertiesActions.clickOnTab(ConstantsUtil.PROPERTIES_TAB);
    CdfPluginPropertiesActions.verifyInputSchemaMatchesOutputSchema(sourcePropertiesOutputSchema);
  }

  @Then("Close the preview")
  public void closeThePreview() {
    CdfStudioActions.previewSelect();
  }

  @Then("Close the preview data")
  public void closeThePreviewData() {
    CdfPluginPropertiesActions.clickCloseButton();
    CdfStudioActions.previewSelect();
  }

  @Then("Deploy the pipeline")
  public void deployThePipeline() {
    CdfStudioActions.pipelineDeploy();
  }

  @Then("Save and Deploy Pipeline")
  public void saveAndDeployPipeline() {
    pipelineName = "TestPipeline-" + RandomStringUtils.randomAlphanumeric(10);
    CdfStudioActions.fillPipelineNameAndSave(pipelineName);
    CdfStudioActions.pipelineDeploy();
  }

  @Then("Run the Pipeline in Runtime")
  public void runThePipelineInRuntime() {
    CdfPipelineRunAction.runClick();
  }

  @Then("Run the Pipeline in Runtime with runtime arguments")
  public void runThePipelineInRuntimeWithRuntimeArguments() {
    CdfPipelineRunAction.clickDeployedConfigRunButton();
  }

  @Then("Wait till pipeline is in running state")
  public void waitTillPipelineIsInRunningState() {
    CdfPipelineRunAction.waitTillPipelineRunCompletes();
  }

  @Then("Wait till pipeline is in running status with a timeout of {long} seconds")
  public void waitTillPipelineIsInRunningState(long timeoutInSeconds) {
    CdfPipelineRunAction.waitTillPipelineRunCompletes(timeoutInSeconds);
  }

  @Then("Verify the pipeline status is {string}")
  public void verifyThePipelineStatusIs(String status) {
    CdfPipelineRunAction.verifyPipelineRunStatus(status);
  }

  @Then("Open and capture logs")
  public void openAndCaptureLogs() {
    try {
      CdfPipelineRunAction.logsClick();
      String rawLogs = CdfPipelineRunAction.captureRawLogs();
      String logsSeparatorMessage = ConstantsUtil.LOGS_SEPARATOR_MESSAGE
        .replace("MESSAGE", "DEPLOYED PIPELINE RUNTIME LOGS");
      BeforeActions.scenario.write(rawLogs);
      CdfPipelineRunAction.writeRawLogsToFile(BeforeActions.file, logsSeparatorMessage, rawLogs);
    } catch (Exception e) {
      BeforeActions.scenario.write("Exception in capturing logs : " + e);
    }
  }

  @Then("Validate OUT record count is equal to IN record count")
  public void validateOUTRecordCountIsEqualToINRecordCount() {
    Assert.assertEquals(recordOut(), recordIn());
  }
}
