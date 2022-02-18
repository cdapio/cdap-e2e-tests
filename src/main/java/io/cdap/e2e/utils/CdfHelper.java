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

package io.cdap.e2e.utils;

import io.cdap.e2e.pages.actions.CdfGcsActions;
import io.cdap.e2e.pages.actions.CdfLogActions;
import io.cdap.e2e.pages.actions.CdfPipelineRunAction;
import io.cdap.e2e.pages.actions.CdfStudioActions;
import io.cdap.e2e.pages.locators.CdfGCSLocators;
import io.cdap.e2e.pages.locators.CdfPipelineRunLocators;
import io.cdap.e2e.pages.locators.CdfSchemaLocators;
import io.cdap.e2e.pages.locators.CdfStudioLocators;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepsdesign.BeforeActions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Represents CdfHelper
 */
public interface CdfHelper {

  CdfSchemaLocators SCHEMA_LOCATORS = SeleniumHelper.getPropertiesLocators(CdfSchemaLocators.class);

  default void openCdf() throws IOException, InterruptedException {
    SeleniumDriver.openPage(SeleniumHelper.readParameters(ConstantsUtil.CDFURL));
    try {
      SeleniumDriver.getDriver().switchTo().alert().accept();
      SeleniumDriver.waitForPageToLoad();
    } catch (NoAlertPresentException Ex) {
      SeleniumDriver.waitForPageToLoad();
    }
  }

  default int countOfNoOfRecordsTransferredToBigQueryIn(String tableName) throws IOException, InterruptedException {
    int countRecords;
    countRecords = BigQueryClient.countBqQuery(PluginPropertyUtils.pluginProp(tableName));
    BeforeActions.scenario.write("**********No of Records Transferred in table" +
                                   PluginPropertyUtils.pluginProp(tableName) + "*:" + countRecords);
    Assert.assertTrue(countRecords > 0);
    return countRecords;
  }

  default void gcsProperties(String formatType) throws IOException, InterruptedException {
    CdfGcsActions.enterReferenceName();
    CdfGcsActions.enterProjectId();
    CdfGcsActions.selectFormat(formatType);
    CdfGcsActions.skipHeader();
    CdfGcsActions.getSchema();
    SeleniumHelper.waitElementIsVisible(CdfGCSLocators.getSchemaButton);
  }

  default void saveAndDeployPipeline() throws InterruptedException {
    CdfStudioActions.pipelineName();
    CdfStudioActions.pipelineNameIp("Pipeline-" + UUID.randomUUID().toString());
    CdfStudioActions.pipelineSave();
    SeleniumHelper.waitElementIsVisible(CdfPipelineRunLocators.savedSuccessMessage, ConstantsUtil.ONE);
    CdfStudioActions.pipelineDeploy();
  }

  default void runThePipelineInRuntime() throws InterruptedException {
    CdfPipelineRunAction.runClick();
  }

  default void waitTillPipelineToComplete() {
    WebDriverWait wait = new WebDriverWait(SeleniumDriver.getDriver(), 600000);
    wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOfElementLocated(
      By.xpath("//*[@data-cy='Succeeded']")), ExpectedConditions.visibilityOfElementLocated(
      By.xpath("//*[@data-cy='Failed']"))));
  }

  default void verifyThePipelineStatusIsForTheCurrentPipeline(String status) {
    Assert.assertTrue(SeleniumDriver.getDriver().findElement(By.xpath("//*[@data-cy='" + status + "']")).isDisplayed());
  }

  default void captureLogs() {
    CdfPipelineRunAction.logsClick();
    BeforeActions.scenario.write(CdfPipelineRunAction.captureRawLogs());
  }

  default void succeedPipelineVerification() {
    CdfLogActions.validateSucceeded();
  }

  default int recordIn() {
    String incount = CdfPipelineRunLocators.recordsInCount.getText();
    return Integer.parseInt(incount.replaceAll(",", ""));
  }

  default int recordOut() {
    String outcount = CdfPipelineRunLocators.recordsOutCount.getText();
    return Integer.parseInt(outcount.replaceAll(",", ""));
  }

  default void deleteBigQueryTable(String table) throws IOException, InterruptedException {
    BigQueryClient.dropBqQuery(PluginPropertyUtils.pluginProp(table));
    BeforeActions.scenario.write("BigQuery Table Deleted Successfully");
  }

  default void selectSourcePlugin(String pluginName) {
    SeleniumDriver.getDriver().findElement(By.xpath("//*[@data-cy='plugin-" + pluginName + "-batchsource']")).click();
  }

  default void selectSinkPlugin(String pluginName) {
    SeleniumDriver.getDriver().findElement(By.xpath("//*[@data-cy='plugin-" + pluginName + "-batchsink']")).click();
  }

  default void waitForSinkOnCanvas(String pluginName) {
    String a = "//*[contains(@data-cy,'plugin-node-" + pluginName + "']";
    SeleniumHelper.waitElementIsVisible(
      SeleniumDriver.getDriver().findElement(By.xpath("//*[contains(@data-cy,'plugin-node-" + pluginName + "')]")));
  }

  default WebElement linkSinkPlugin(String pluginName) {
    String a = "//*[contains(@data-cy,'plugin-node-" + pluginName + "']";
    return SeleniumDriver.getDriver().findElement(By.xpath("//*[contains(@data-cy,'plugin-node-" + pluginName + "')]"));
  }

  default void connectSourceAndSink(String source, String sink) {
    By sinkNode = By.xpath("//*[contains(@data-cy,'plugin-node-" + sink + "') and @data-type='batchsink']");
    SeleniumHelper.waitElementIsVisible(SeleniumDriver.getDriver().findElement(sinkNode));
    SeleniumHelper.dragAndDrop(
      SeleniumDriver.getDriver().findElement(By.xpath("//*[contains(@class,'plugin-endpoint_" + source + "')]")),
      SeleniumDriver.getDriver().findElement(sinkNode));
  }

  default void connectSourceAndSinkWithTitles(String source, String sourceTitle, String sink, String sinkTitle) {
    WebElement sinkNode = CdfStudioLocators.sinkNodeWithTitle(sink, sinkTitle);
    SeleniumHelper.waitElementIsVisible(sinkNode);
    SeleniumHelper.dragAndDrop(CdfStudioLocators.sourceEndpointWithTitle(source, sourceTitle), sinkNode);
  }

  default void openSourcePluginProperties(String plugin) {
    SeleniumDriver.getDriver().findElement(
      By.xpath("//*[contains(@data-cy,'plugin-node-" + plugin + "') and " +
                 "@data-type='batchsource']//div[@class='node-metadata']/div[2]")).click();
  }

  default void openSinkPluginProperties(String plugin) {
    SeleniumDriver.getDriver().findElement(
      By.xpath("//*[contains(@data-cy,'plugin-node-" + plugin + "') and " +
                 "@data-type='batchsink']//div[@class='node-metadata']/div[2]")).click();
  }

  default void openSourcePluginPreviewData(String plugin) {
    SeleniumDriver.getDriver().findElement(
      By.xpath("//*[@data-type='batchsource']//*[contains(@data-cy,'-preview-data-btn') " +
                 "and contains(@data-cy,'" + plugin + "') and @class='node-preview-data-btn ng-scope']")).click();
  }

  default void openSinkPluginPreviewData(String plugin) {
    SeleniumDriver.getDriver().findElement(
      By.xpath("//*[@data-type='batchsink']//*[contains(@data-cy,'-preview-data-btn') " +
                 "and contains(@data-cy,'" + plugin + "') and @class='node-preview-data-btn ng-scope']")).click();
  }

  default boolean compareTransferredRecords() {
    int inCount = recordIn();
    int outCount = recordOut();
    return outCount != 0 && inCount == outCount;
  }

  default void validateSchema(Map<String, String> expectedOutputSchema) {
    Map<String, String> actualOutputSchema = new HashMap<>();
    int index = 0;
    for (WebElement element : SCHEMA_LOCATORS.outputSchemaColumnNames) {
      actualOutputSchema.put(element.getAttribute("value"),
                             SCHEMA_LOCATORS.outputSchemaDataTypes.get(index).getAttribute("title"));
      index++;
    }
    Assert.assertTrue("Schema displayed on UI should match with expected Schema",
                      actualOutputSchema.equals(expectedOutputSchema));
  }

  default void clickMacroButton(String pluginProperty) {
    CdfStudioLocators.macroButton(pluginProperty).click();
  }

  default void enterMacro(String pluginProperty, String argument) {
    SeleniumHelper.replaceElementValue(CdfStudioLocators.macroInput(pluginProperty), "${" + argument + "}");
  }
}
