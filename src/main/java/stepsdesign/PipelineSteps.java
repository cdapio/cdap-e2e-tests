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

import io.cdap.e2e.pages.actions.CdfPipelineRunAction;
import io.cdap.e2e.pages.actions.CdfStudioActions;
import io.cdap.e2e.pages.locators.CdfSchemaLocators;
import io.cdap.e2e.pages.locators.CdfStudioLocators;
import io.cdap.e2e.utils.AssertionHelper;
import io.cdap.e2e.utils.CdfHelper;
import io.cdap.e2e.utils.ConstantsUtil;
import io.cdap.e2e.utils.ElementHelper;
import io.cdap.e2e.utils.JsonUtils;
import io.cdap.e2e.utils.PluginPropertyUtils;
import io.cdap.e2e.utils.SeleniumDriver;
import io.cdap.e2e.utils.SeleniumHelper;
import io.cdap.e2e.utils.WaitHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * CDF pipeline related step design.
 */
public class PipelineSteps implements CdfHelper {

  static {
    SeleniumHelper.getPropertiesLocators(CdfStudioLocators.class);
  }

  List<String> propertiesSchemaColumnList = new ArrayList<>();
  Map<String, String> sourcePropertiesOutputSchema = new HashMap<>();

  @Given("Open Datafusion Project to configure pipeline")
  public void openDatafusionProjectToConfigurePipeline() throws IOException, InterruptedException {
    openCdf();
  }

  @When("Select data pipeline type as 'Data Pipeline - Batch'")
  public void selectDataPipelineTypeAsBatch() {
    CdfStudioActions.selectDataPipelineTypeAsBatch();
  }

  @When("Select data pipeline type as 'Data Pipeline - Realtime'")
  public void selectDataPipelineTypeAsRealtime() {
    CdfStudioActions.selectDataPipelineTypeAsRealtime();
  }

  @When("Select plugin: {string} from the plugins list")
  public void selectPlugin(String pluginName) {
    CdfStudioActions.selectPluginFromList(pluginName);
  }

  @When("Navigate to the properties page of plugin: {string}")
  public void navigateToPluginPropertiesPage(String pluginName) {
    CdfStudioActions.navigateToPluginPropertiesPage(pluginName);
  }

  @Then("Connect source as {string} and sink as {string} to establish connection")
  public void connectSourceAndSinkToEstablishConnection(String source, String sink) {
    connectSourceAndSink(source, sink);
  }

  @Then("Validate {string} plugin properties")
  public void validatePluginProperties(String plugin) {
    CdfStudioActions.clickValidateButton();
    WaitHelper.waitForElementToBeDisplayed(CdfStudioLocators.pluginValidationSuccessMsg);
    String expectedMessage = PluginPropertyUtils.errorProp(ConstantsUtil.VALIDATION_SUCCESS_MESSAGE);
    String actualMessage = CdfStudioLocators.pluginValidationSuccessMsg.getText();
    Assert.assertEquals(plugin + " plugin properties validation success message should be displayed"
      , expectedMessage, actualMessage);
  }

  @Then("Validate mandatory property error for {string}")
  public void validateMandatoryPropertyErrorFor(String property) {
    CdfStudioActions.clickValidateButton();
    WaitHelper.waitForElementToBeDisplayed(CdfStudioLocators.validateButton);
    PluginPropertyUtils.validateMandatoryPropertyError(property);
  }

  @Then("Verify plugin properties validation fails with {int} error")
  public void verifyPluginPropertiesValidationFailsWithError(int errorCount) {
    CdfStudioActions.clickValidateButton();
    WaitHelper.waitForElementToBeDisplayed(CdfStudioLocators.validateButton);
    String errorText = "error";
    if (errorCount > 1) {
      errorText = "errors";
    }
    String expectedErrorMessage = PluginPropertyUtils.errorProp(ConstantsUtil.VALIDATION_ERROR_MESSAGE)
      .replace("COUNT", String.valueOf(errorCount)).replace("ERROR", errorText);
    String actualErrorMessage = CdfStudioLocators.pluginValidationErrorMsg.getText();
    Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
  }

  @Then("Validate output schema with expectedSchema {string}")
  public void validateOutputSchemaWithExpectedSchema(String schemaJsonArray) {
    CdfSchemaLocators.getSchemaLoadComplete.click();
    WaitHelper.waitForElementToBeDisplayed(CdfSchemaLocators.getSchemaLoadComplete);
    WaitHelper.waitForElementToBeDisplayed(CdfSchemaLocators.outputSchemaColumnNames.get(0));
    Map<String, String> expectedOutputSchema =
      JsonUtils.convertKeyValueJsonArrayToMap(PluginPropertyUtils.pluginProp(schemaJsonArray));
    validateSchema(expectedOutputSchema);
    int index = 0;
    for (WebElement element : CdfSchemaLocators.outputSchemaColumnNames) {
      propertiesSchemaColumnList.add(element.getAttribute("value"));
      sourcePropertiesOutputSchema.put(element.getAttribute("value"),
        CdfSchemaLocators.outputSchemaDataTypes.get(index).getAttribute("title"));
      index++;
    }
  }

  @Then("Save the pipeline")
  public void saveThePipeline() {
    CdfStudioActions.pipelineName();
    CdfStudioActions.pipelineNameIp("TestPipeline" + UUID.randomUUID());
    CdfStudioActions.pipelineSave();
    WaitHelper.waitForElementToBeDisplayed(CdfStudioLocators.statusBanner);
    WebDriverWait wait = new WebDriverWait(SeleniumDriver.getDriver(), 5);
    wait.until(ExpectedConditions.invisibilityOf(CdfStudioLocators.statusBanner));
  }

  @Then("Preview and run the pipeline")
  public void previewAndRunThePipeline() {
    ElementHelper.clickOnElement(CdfStudioLocators.preview);
    CdfStudioLocators.runButton.click();
  }

  @Then("Verify the preview of pipeline is {string}")
  public void verifyThePreviewOfPipelineIs(String previewStatus) {
    WebDriverWait wait = new WebDriverWait(SeleniumDriver.getDriver(), 180);
    wait.until(ExpectedConditions.visibilityOf(CdfStudioLocators.statusBanner));
    Assert.assertTrue("Preview of pipeline should " + previewStatus,
      CdfStudioLocators.statusBannerText.getText().contains(previewStatus));
    if (!previewStatus.equalsIgnoreCase("failed")) {
      wait.until(ExpectedConditions.invisibilityOf(CdfStudioLocators.statusBanner));
    }
  }

  @Then("Verify preview output schema matches the outputSchema captured in properties")
  public void verifyPreviewOutputSchemaMatchesTheOutputSchemaCapturedInProperties() {
    List<String> previewSchemaColumnList = new ArrayList<>();
    for (WebElement element : CdfStudioLocators.previewInputRecordColumnNames) {
      previewSchemaColumnList.add(element.getAttribute("title"));
    }
    Assert.assertTrue("Schema column list should be equal to preview column list",
      previewSchemaColumnList.equals(propertiesSchemaColumnList));
    CdfStudioLocators.previewPropertiesTab.click();
    Map<String, String> previewSinkInputSchema = new HashMap<>();
    int index = 0;
    for (WebElement element : CdfSchemaLocators.inputSchemaColumnNames) {
      previewSinkInputSchema.put(element.getAttribute("value"),
        CdfSchemaLocators.inputSchemaDataTypes.get(index).getAttribute("title"));
      index++;
    }
    Assert.assertTrue("Schema should match", previewSinkInputSchema.equals(sourcePropertiesOutputSchema));
  }

  @Then("Close the preview")
  public void closeThePreview() {
    CdfStudioActions.previewSelect();
  }

  @Then("Close the preview data")
  public void closeThePreviewData() {
    CdfStudioActions.clickCloseButton();
    CdfStudioActions.previewSelect();
  }

  @Then("Deploy the pipeline")
  public void deployThePipeline() {
    WaitHelper.waitForElementToBeDisplayed(CdfStudioLocators.pipelineDeploy);
    CdfStudioActions.pipelineDeploy();
  }

  @Then("Save and Deploy Pipeline")
  public void saveAndDeployPipeline() {
    CdfStudioActions.pipelineName();
    CdfStudioActions.pipelineNameIp("TestPipeline" + UUID.randomUUID());
    CdfStudioActions.pipelineSave();
    WaitHelper.waitForElementToBeDisplayed(CdfStudioLocators.statusBanner);
    WaitHelper.waitForElementToBeHidden(CdfStudioLocators.statusBanner);
    CdfStudioActions.pipelineDeploy();
  }

  @Then("Run the Pipeline in Runtime")
  public void runThePipelineInRuntime() throws InterruptedException {
    CdfPipelineRunAction.runClick();
  }

  @Then("Wait till pipeline is in running state")
  public void waitTillPipelineIsInRunningState() {
    WebDriverWait wait = new WebDriverWait(SeleniumDriver.getDriver(), 300);
    wait.until(ExpectedConditions.or(
      ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-cy='Succeeded']")),
      ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-cy='Failed']"))));
  }

  @Then("Verify the pipeline status is {string}")
  public void verifyThePipelineStatusIs(String status) {
    WebElement pipelineStatus = SeleniumDriver.getDriver().findElement(By.xpath("//*[@data-cy='" + status + "']"));
    AssertionHelper.verifyElementDisplayed(pipelineStatus, "Pipeline status should be " + status);
  }

  @Then("Open and capture logs")
  public void openAndCaptureLogs() {
    try {
      CdfPipelineRunAction.logsClick();
      BeforeActions.scenario.write(CdfPipelineRunAction.captureRawLogs());
      PrintWriter out = new PrintWriter(BeforeActions.file);
      out.println(CdfPipelineRunAction.captureRawLogs());
    } catch (Exception e) {
      BeforeActions.scenario.write("Exception in capturing logs : " + e);
    }
  }

  @Then("Validate OUT record count is equal to IN record count")
  public void validateOUTRecordCountIsEqualToINRecordCount() {
    Assert.assertEquals(recordOut(), recordIn());
  }
}
