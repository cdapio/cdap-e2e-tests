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

import io.cdap.e2e.pages.actions.CdfConnectionActions;
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

  @Then("Click on the Preview Data link on the Source plugin node: {string}")
  public static void clickPreviewDataLinkOnSourcePluginNode(String pluginName) {
    CdfStudioActions.clickPreviewDataLinkOnSourcePluginNode(pluginName);
  }

  @Then("Click on the Preview Data link on the Sink plugin node: {string}")
  public static void clickPreviewDataLinkOnSinkPluginNode(String pluginName) {
    CdfStudioActions.clickPreviewDataLinkOnSinkPluginNode(pluginName);
  }

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
    CdfStudioActions.selectPluginFromList(pluginName, pluginGroupName);
  }

  @When("Select Sink plugin: {string} from the plugins list")
  public void selectSinkPlugin(String pluginName) {
    CdfStudioActions.selectSinkPlugin(pluginName);
  }

  @When("Select Realtime Source plugin: {string} from the plugins list")
  public void selectRealtimeSourcePlugin(String pluginName) {
    CdfStudioActions.selectRealtimeSourcePlugin(pluginName);
  }

  @Then("Verify plugin: {string} node is displayed on the canvas with a timeout of {long} seconds")
  public void verifyPluginNodeIsDisplayedOnTheCanvas(String pluginName, long timeoutInSeconds) {
    CdfStudioActions.verifyPluginNodeIsDisplayedOnCanvas(pluginName, timeoutInSeconds);
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

  @Then("Connect plugins: {string} and {string} to establish connection")
  public void connectPluginsToEstablishConnection(String fromPlugin, String toPlugin) {
    CdfStudioActions.establishConnection(fromPlugin, toPlugin);
  }

  @Then("Connect node type: {string} of plugin: {string} to plugin: {string}")
  public void connectPluginsToEstablishConnection(String nodeType, String fromPlugin, String toPlugin) {
    CdfStudioActions.establishConnection(nodeType, fromPlugin, toPlugin);
  }

  @When("Click on the Macro button of Property: {string} and set the value to: {string}")
  public void fillValueInMacroEnabledInputProperty(String property, String value) {
    CdfPluginPropertiesActions.clickMacroButtonOfProperty(property);
    CdfPluginPropertiesActions.fillValueInMacroEnabledInputProperty(property, value);
  }

  @When("Click on the Macro button of Property: {string} and set the value in textarea: {string}")
  public void fillValueInMacroEnabledTextareaProperty(String property, String value) {
    CdfPluginPropertiesActions.clickMacroButtonOfProperty(property);
    CdfPluginPropertiesActions.fillValueInMacroEnabledTextareaProperty(property, value);
  }

  @Then("Select Macro action of output schema property: {string} and set the value to {string}")
  public void selectMacroActionOfOutputSchemaPropertyAndSetTheValueTo(String property, String value) {
    CdfPluginPropertiesActions.selectMacroActionOfOutputSchemaProperty();
    CdfPluginPropertiesActions.fillValueInMacroEnabledInputProperty(property, value);
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

  @Then("Verify the Output Schema matches the Expected Schema: {string}")
  public void verifyOutputSchemaMatchesExpectedSchema(String expectedSchemaJsonArrayLocation) {
    propertiesSchemaColumnList = CdfPluginPropertiesActions.getListOfFieldsFromOutputSchema();
    sourcePropertiesOutputSchema = CdfPluginPropertiesActions.getOutputSchema();
    CdfPluginPropertiesActions.verifyOutputSchemaMatchesExpectedSchema(expectedSchemaJsonArrayLocation);
  }

  @Then("Change datatype of fields in output schema with : {string}")
  public void changeDatatypeOfFieldsInOutputSchemaWith(String jsonChangeFieldList) {
    CdfPluginPropertiesActions.changeDataTypesInOutputSchema(jsonChangeFieldList);
  }

  @When("Click on the Get Schema button")
  public void clickOnGetSchemaButton() {
    CdfPluginPropertiesActions.clickGetSchemaButton();
  }

  /**
   * Usage Example:
   * Then Verify the Output Schema matches the Expected Schema for listed Hierarchical fields:
   * | FieldName  |         SchemaJsonArray              |
   * | fieldOne   | expectedSchemaJsonArrayForFieldOne   |
   * | fieldTwo   | expectedSchemaJsonArrayForFieldTwo   |
   * | fieldThree | expectedSchemaJsonArrayForFieldThree |
   *
   * @param table
   */
  @Then("Verify the Output Schema matches the Expected Schema for listed Hierarchical fields:")
  public void verifyOutputSchemaMatchesExpectedSchemaForHierarchicalFields(DataTable table) {
    List<Map<String, String>> data = table.asMaps(String.class, String.class);

    for (Map<String, String> row : data) {
      CdfPluginPropertiesActions.verifyOutputSchemaForHierarchicalField(row.get("FieldName"), row.get(
        "SchemaJsonArray"));
    }
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

  @Then("Verify sink plugin's Preview Data for Input Records table and the Input Schema matches the Output Schema of " +
    "Source plugin")
  public void verifySinkPluginPreviewDataInputRecordsTableAndInputSchemaMatchesOutputSchemaOfSourcePlugin() {
    CdfPluginPropertiesActions
      .verifyInputRecordsTableColumnsUnderPreviewTabMatchesInputSchemaFields(propertiesSchemaColumnList);
    CdfPluginPropertiesActions.clickOnTab(ConstantsUtil.PROPERTIES_TAB);
    CdfPluginPropertiesActions.verifyInputSchemaMatchesOutputSchema(sourcePropertiesOutputSchema);
  }

  @Then("Close the preview")
  public void closeThePreview() {
    CdfStudioActions.closePreviewMenu();
  }

  @Then("Close the preview data")
  public void closeThePreviewData() {
    CdfPluginPropertiesActions.clickCloseButton();
    CdfStudioActions.closePreviewMenu();
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

  @Then("Wait for pipeline to be in status: {string} with a timeout of {long} seconds")
  public void waitForPipelineToBeInStatus(String pipelineStatus, long timeoutInSeconds) {
    CdfPipelineRunAction.waitForPipelineToTransitionToStatus(pipelineStatus, timeoutInSeconds);
  }

  @Then("Wait till pipeline is in running state")
  public void waitTillPipelineIsInRunningState() {
    CdfPipelineRunAction.waitTillPipelineRunCompletes();
  }

  @Then("Wait till pipeline is in running status with a timeout of {long} seconds")
  public void waitTillPipelineIsInRunningState(long timeoutInSeconds) {
    CdfPipelineRunAction.waitTillPipelineRunCompletes(timeoutInSeconds);
  }

  @Then("Wait till data transfer begins by waiting for a non-zero records 'In' count on Plugin node: {string} " +
    "with a timeout of {long} seconds")
  public void waitTillDataTransferBegins(String pluginNodeId, long timeoutInSeconds) {
    CdfPipelineRunAction.waitTillDataTransferBegins(pluginNodeId, timeoutInSeconds);
  }

  @Then("Stop the pipeline")
  public void stopPipeline() {
    CdfPipelineRunAction.stopPipeline();
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

  /**
   * Usage Example:
   * Open Pipeline logs and verify Log entries having below listed Level and Message:
   * | Level |       Message        |
   * | ERROR | expected log message |
   * | WARN  | expected log message |
   * | DEBUG | expected log message |
   * <p>
   * NOTE: Partial messages will work as well
   *
   * @param table
   */
  @Then("Open Pipeline logs and verify Log entries having below listed Level and Message:")
  public void openPipelineLogsAndVerifyLogEntry(DataTable table) {
    List<Map<String, String>> data = table.asMaps(String.class, String.class);
    CdfPipelineRunAction.logsClick();
    CdfLogActions.viewAdvancedLogs();

    for (Map<String, String> row : data) {
      CdfLogActions.verifyPipelineLogsForExpectedLogEntry(row.get("Level"), row.get("Message"));
    }
  }

  @Then("Validate OUT record count is equal to IN record count")
  public void validateOUTRecordCountIsEqualToINRecordCount() {
    Assert.assertEquals(recordOut(), recordIn());
  }

  @Then("Enter input plugin property: {string} with value: {string}")
  public void enterInputPluginPropertyWithValue(String pluginProperty, String value) {
    CdfPluginPropertiesActions.enterValueInInputProperty(pluginProperty, value);
  }

  @Then("Replace input plugin property: {string} with value: {string}")
  public void replaceInputPluginPropertyWithValue(String pluginProperty, String value) {
    CdfPluginPropertiesActions.replaceValueInInputProperty(pluginProperty, value);
  }

  @Then("Enter textarea plugin property: {string} with value: {string}")
  public void enterTextareaPluginPropertyWithValue(String pluginProperty, String value) {
    CdfPluginPropertiesActions.enterValueInTextareaProperty(pluginProperty, value);
  }

  @Then("Enter input plugin property: {string} with value: {string} for Credentials and Authorization related fields")
  public void enterInputPluginPropertyWithValueForCredentials(String pluginProperty, String envVariableKey) {
    CdfPluginPropertiesActions.enterValueInInputPropertyFromEnv(pluginProperty, envVariableKey);
  }

  @Then("Replace input plugin property: {string} with value: {string} for Credentials and Authorization related fields")
  public void replaceInputPluginPropertyWithValueForCredentials(String pluginProperty, String envVariableKey) {
    CdfPluginPropertiesActions.replaceValueInInputPropertyFromEnv(pluginProperty, envVariableKey);
  }

  @Then("Replace textarea plugin property: {string} with value: {string}")
  public void replaceTextareaPluginPropertyWithValue(String pluginProperty, String value) {
    CdfPluginPropertiesActions.replaceValueInTextareaProperty(pluginProperty, value);
  }

  @Then("Click plugin property: {string} button")
  public void clickPluginPropertyButton(String pluginProperty) {
    CdfPluginPropertiesActions.clickPluginPropertyButton(pluginProperty);
  }

  @Then("Click plugin property: {string}")
  public void clickPluginPropertyElement(String pluginProperty) {
    CdfPluginPropertiesActions.clickPluginPropertyElement(pluginProperty);
  }

  @Then("Select radio button plugin property: {string} with value: {string}")
  public void selectRadioButtonPluginPropertyValue(String pluginProperty, String value) {
    CdfPluginPropertiesActions.selectPluginPropertyRadioButton(pluginProperty, value);
  }

  @Then("Select dropdown plugin property: {string} with option value: {string}")
  public void selectDropdownPluginPropertyOptionValue(String pluginProperty, String option) {
    CdfPluginPropertiesActions.selectPluginPropertyDropdownOption(pluginProperty, option);
  }

  @Then("Enter key value pairs for plugin property: {string} with values from json: {string}")
  public void enterKeyValuePairsForPluginPropertyWithValuesFromJson(String pluginProperty, String jsonKeyValuePairs) {
    CdfPluginPropertiesActions.enterKeyValuePairsForProperty(pluginProperty, jsonKeyValuePairs);
  }

  @Then("Override Service account details if set in environment variables")
  public void overrideServiceAccountDetailsIfSetInEnvVars() {
    CdfPluginPropertiesActions.overrideServiceAccountDetailsIfProvided();
  }

  @Then("Override Service account details in Wrangler connection page if set in environment variables")
  public void overrideServiceAccountDetailsInWranglerConnectionPageIfSetInEnvVars() {
    CdfPluginPropertiesActions.overrideServiceAccountDetailsInWranglerConnectionPageIfProvided();
  }

  @Then("Click on the Browse button inside plugin properties")
  public void clickBrowseButtonInsidePluginProperties() {
    CdfPluginPropertiesActions.clickBrowseButton();
  }

  @Then("Click on the Browse Connections button")
  public void clickBrowseConnectionsButton() {
    CdfConnectionActions.clickBrowseConnectionsButton();
  }

  @Then("Select connection: {string}")
  public void selectConnection(String connectionName) {
    CdfConnectionActions.selectConnection(connectionName);
  }

  @Given("Open Wrangler connections page")
  public void openWranglerConnectionsPage() throws IOException {
    CdfConnectionActions.openWranglerConnectionsPage();
  }

  @Then("Verify the test connection is successful")
  public void verifyTheTestConnectionIsSuccessful() {
    CdfConnectionActions.verifyTheTestConnectionIsSuccessful();
  }

  @Then("Verify the connection with name: {string} is created successfully")
  public void verifyTheConnectionWithNameIsCreatedSuccessfully(String connectionName) {
    CdfConnectionActions.verifyConnectionIsCreatedSuccessfully(connectionName);
  }

  @Then("Select connection data row with name: {string}")
  public void selectConnectionDataRowWithName(String dataName) {
    CdfConnectionActions.selectConnectionDataWithName(dataName);
  }

  @Then("Select connection data rows with path: {string}")
  public void selectConnectionDataRowsWithPath(String dataPath) {
    CdfConnectionActions.selectConnectionDataWithPath(dataPath);
  }

  @Then("Wait till connection data loading completes with a timeout of {long} seconds")
  public void waitTillConnectionDataLoadingCompletes(long timeoutInSeconds) {
    CdfConnectionActions.waitTillConnectionDataLoadingCompletes(timeoutInSeconds);
  }

  @Then("Click SELECT button inside connection data row with name: {string}")
  public void clickSelectButtonInsideConnectionDataRowWithName(String dataName) {
    CdfConnectionActions.clickSelectButtonOfConnectionDataRow(dataName);
  }

  @Then("Verify connection datatable is displayed for the data: {string}")
  public void verifyConnectionDatatableIsDisplayedForTheData(String dataPath) {
    CdfConnectionActions.verifyTheConnectionDataTableDisplayed(dataPath);
  }

  @Then("Click Create Pipeline button and choose the type of pipeline as: {string}")
  public void clickCreatePipelineButtonAndChoosePipelineType(String pipelineType) {
    CdfConnectionActions.clickCreatePipelineButton();
    CdfConnectionActions.selectPipelineType(pipelineType);
  }

  @Then("Expand connections of type: {string}")
  public void expandConnectionsOfType(String connectionType) {
    CdfConnectionActions.expandConnections(connectionType);
  }

  @Then("Open action menu for connection: {string} of type: {string}")
  public void openActionMenuForConnectionOfType(String connectionName, String connectionType) {
    CdfConnectionActions.openConnectionActionMenu(connectionType, connectionName);
  }

  @Then("Select action: {string} for connection: {string} of type: {string}")
  public void selectActionForConnectionOfType(String action, String connectionName,
                                                            String connectionType) {
    CdfConnectionActions.selectConnectionAction(connectionType, connectionName, action);
  }

  @Then("Verify connection: {string} of type: {string} is deleted successfully")
  public void verifyConnectionOfTypeIsDeletedSuccessfully(String connectionName, String connectionType) {
    CdfConnectionActions.verifyConnectionIsNotPresent(connectionType, connectionName);
  }

  @Then("Verify input plugin property: {string} contains value: {string}")
  public void verifyInputPluginPropertyContainsValue(String pluginProperty, String value) {
    CdfPluginPropertiesActions.verifyInputPluginPropertyContainsValue(pluginProperty, value);
  }

  @Then("Verify dropdown plugin property: {string} is selected with option: {string}")
  public void verifyDropdownPluginPropertySelectedWithOption(String pluginProperty, String option) {
    CdfPluginPropertiesActions.verifyDropdownPluginPropertySelectedWithOption(pluginProperty, option);
  }

  @Then("Verify plugin property: {string} contains text: {string}")
  public void verifyPluginPropertyContainsText(String pluginProperty, String text) {
    CdfPluginPropertiesActions.verifyPluginPropertyContainsText(pluginProperty, text);
  }

  @Then("Verify toggle plugin property: {string} is toggled to: {string}")
  public void verifyTogglePluginPropertyIsToggledTo(String pluginProperty, String toggleValue) {
    CdfPluginPropertiesActions.verifyPluginPropertyToggleState(pluginProperty, toggleValue);
  }

  @Then("Verify radio button plugin property: {string} is selected with value: {string}")
  public void verifyRadioButtonPluginPropertyIsSelectedWithValue(String pluginProperty, String value) {
    CdfPluginPropertiesActions.verifyRadioButtonPluginPropertySelectedValue(pluginProperty, value);
  }

  @Then("Move plugins: {string} by xOffset {int} and yOffset {int}")
  public void movePlugin(String pluginName, int xOffset, int yOffset) {
    CdfStudioActions.movePlugin(pluginName, xOffset, yOffset);
  }

  @Then("Open pipeline actions menu")
  public void openPipelineActionsMenu() {
    CdfPipelineRunAction.openPipelineActionsMenu();
  }

  @Then("Select pipeline action: {string}")
  public void selectPipelineAction(String action) {
    CdfPipelineRunAction.selectPipelineAction(action);
  }
}
