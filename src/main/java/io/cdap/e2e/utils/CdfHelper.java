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
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepsdesign.BeforeActions;

import java.io.IOException;
import java.util.UUID;

/**
 * Represents CdfHelper
 */
public interface CdfHelper {

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
    countRecords = GcpClient.countBqQuery(SeleniumHelper.readParameters(tableName));
    BeforeActions.scenario.write("**********No of Records Transferred in table" +
                                   SeleniumHelper.readParameters(tableName) + "*:" + countRecords);
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
    return Integer.valueOf(incount.replaceAll(",", "").toString());
  }

  default int recordOut() {
    String outcount = CdfPipelineRunLocators.recordsOutCount.getText();
    return Integer.valueOf(outcount.replaceAll(",", "").toString());
  }

  default void deleteBigQueryTable(String table) throws IOException, InterruptedException {
    GcpClient.dropBqQuery(SeleniumHelper.readParameters(table));
    BeforeActions.scenario.write("BigQuery Table Deleted Successfully");
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
}
