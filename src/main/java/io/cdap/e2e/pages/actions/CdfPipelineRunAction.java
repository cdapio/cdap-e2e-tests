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

package io.cdap.e2e.pages.actions;

import io.cdap.e2e.pages.locators.CdfPipelineRunLocators;
import io.cdap.e2e.utils.AssertionHelper;
import io.cdap.e2e.utils.ConstantsUtil;
import io.cdap.e2e.utils.ElementHelper;
import io.cdap.e2e.utils.PageHelper;
import io.cdap.e2e.utils.RetryUtils;
import io.cdap.e2e.utils.SeleniumDriver;
import io.cdap.e2e.utils.SeleniumHelper;
import io.cdap.e2e.utils.WaitHelper;
import org.junit.Assert;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Represents Cdf Pipeline Run Page Actions
 */
public class CdfPipelineRunAction {

  static {
    SeleniumHelper.getPropertiesLocators(CdfPipelineRunLocators.class);
  }

  /**
   * Click on the Run button
   */
  public static void runClick() {
    ElementHelper.clickOnElement(CdfPipelineRunLocators.run);
  }

  /**
   * Click on the Run button inside 'Runtime Arguments' dialog
   */
  public static void clickDeployedConfigRunButton() {
    ElementHelper.clickOnElement(CdfPipelineRunLocators.deployedConfigRunButton);
  }

  /**
   * Get the Pipeline's Status bubble color
   *
   * @return color attribute value
   */
  public static String getColorAttributeOfPipelineStatusBubble() {
    return CdfPipelineRunLocators.runPipelineStatus.getAttribute(ConstantsUtil.COLOR);
  }

  /**
   * Check if the Pipeline is in the expected Status
   *
   * @param element WebElement of Pipeline's expected Status
   * @return boolean
   */
  private static boolean validatePipelineExpectedStatus(WebElement element) {
    return ElementHelper.isElementDisplayed(element);
  }

  /**
   * Check if the pipeline is in Running Status
   *
   * @return Boolean
   */
  public static Boolean isRunning() {
    return validatePipelineExpectedStatus(CdfPipelineRunLocators.runningStatus);
  }

  /**
   * Check if the pipeline is in Starting Status
   *
   * @return Boolean
   */
  public static Boolean isStarting() {
    return validatePipelineExpectedStatus(CdfPipelineRunLocators.startingStatus);
  }

  /**
   * Check if the pipeline is in Provisioning Status
   *
   * @return Boolean
   */
  public static Boolean isProvisioning() {
    return validatePipelineExpectedStatus(CdfPipelineRunLocators.provisioningStatus);
  }

  /**
   * Wait for the pipeline to be in the expected Status
   *
   * @param pipelineStatus   Expected Pipeline Status
   * @param timeoutInSeconds Timeout
   */
  public static void waitForPipelineToTransitionToStatus(String pipelineStatus, long timeoutInSeconds) {
    WaitHelper.waitForElementToBeDisplayed(
      CdfPipelineRunLocators.locatePipelineStatus(pipelineStatus), timeoutInSeconds);
  }

  /**
   * Wait till the Pipeline's status changes (from Running) to either Succeeded, Failed or Stopped within the
   * Timeout: {@link ConstantsUtil#IMPLICIT_TIMEOUT_SECONDS}
   */
  public static void waitTillPipelineRunCompletes() throws InterruptedException {
    // Adding a page refresh in case tests are running on CDF to update the pipeline status.
      RetryUtils.retry(ConstantsUtil.PIPELINE_REFRESH_TIMEOUT_SECONDS, ConstantsUtil.PIPELINE_RUN_TIMEOUT_SECONDS,
        10, () -> {
        PageHelper.refreshCurrentPage();
        return !(isRunning());
        }
      );

    waitTillPipelineRunCompletes(ConstantsUtil.IMPLICIT_TIMEOUT_SECONDS);
  }

  /**
   * Wait till the Pipeline's status changes (from Running) to either Succeeded, Failed or Stopped within the given
   * timeout
   *
   * @param timeoutInSeconds timeout
   */
  public static void waitTillPipelineRunCompletes(long timeoutInSeconds) {
    SeleniumDriver.getWaitDriver(timeoutInSeconds).until(ExpectedConditions.or(
      ExpectedConditions.visibilityOf(CdfPipelineRunLocators.succeededStatus),
      ExpectedConditions.visibilityOf(CdfPipelineRunLocators.failedStatus),
      ExpectedConditions.visibilityOf(CdfPipelineRunLocators.stoppedStatus)
    ));
  }

  /**
   * Stop the Pipeline
   */
  public static void stopPipeline() {
    ElementHelper.clickOnElement(CdfPipelineRunLocators.stop);
  }

  /**
   * Verify the Pipeline's Status
   *
   * @param status Deployed/Provisioning/Starting/Running/Succeeded/Failed
   */
  public static void verifyPipelineRunStatus(String status) {
    AssertionHelper.verifyElementDisplayed(CdfPipelineRunLocators.locatePipelineStatus(status),
      "Pipeline status should be " + status);
  }

  /**
   * Click on the Logs button
   */
  public static void logsClick() {
    ElementHelper.clickOnElement(CdfPipelineRunLocators.logs);
  }

  /**
   * View Raw Logs
   */
  public static void viewRawLogs() {
    int attempts = 0;
    while (attempts < 5) {
      try {
        ElementHelper.clickOnElement(CdfPipelineRunLocators.logsArrow);
        break;
      } catch (StaleElementReferenceException e) {
        if (attempts == 4) {
          throw e;
        }
      }
      attempts++;
    }

    ElementHelper.clickOnElement(CdfPipelineRunLocators.viewRawLogs);
  }

  /**
   * Capture Raw Logs
   *
   * @return logs
   */
  public static String captureRawLogs() {
    viewRawLogs();
    PageHelper.switchToWindow(ConstantsUtil.ONE);
    String logs = CdfPipelineRunLocators.logsTextbox.getText();
    Assert.assertNotNull(logs);
    PageHelper.closeCurrentWindow();
    PageHelper.switchBackToMainWindow();
    return logs;
  }

  /**
   * Write logs to file - if file already preset then append logs
   *
   * @param file    filename
   * @param message logs separator message
   * @param rawLogs captured raw logs
   * @throws FileNotFoundException
   */
  public static void writeRawLogsToFile(File file, String message, String rawLogs) throws FileNotFoundException {
    PrintWriter out;
    if (file.exists()) {
      out = new PrintWriter(new FileOutputStream(file, true));
    } else {
      out = new PrintWriter(file);
    }
    out.println(message);
    out.println(rawLogs);
    out.close();
  }

  /**
   * Get Count displayed on Source plugin as records 'Out'
   *
   * @return Records Out count
   */
  public static int getCountDisplayedOnSourcePluginAsRecordsOut() {
    String outCount = CdfPipelineRunLocators.recordsOutCount.getText();
    return Integer.parseInt(outCount.replaceAll(",", ""));
  }

  /**
   * Get Count displayed on Sink plugin as records 'In'
   *
   * @return
   */
  public static int getCountDisplayedOnSinkPluginAsRecordsIn() {
    String inCount = CdfPipelineRunLocators.recordsInCount.getText();
    return Integer.parseInt(inCount.replaceAll(",", ""));
  }

  /**
   * Wait till Data transfer begins in the Pipeline. Ex. Sink node's "In" count should show non-zero value
   *
   * @param pluginNodeId
   * @param timeoutInSeconds
   */
  public static void waitTillDataTransferBegins(String pluginNodeId, long timeoutInSeconds) {
    WaitHelper.waitForElementToBeDisplayed(
      CdfPipelineRunLocators.locateRecordsInCountOnPluginNodeAsNonZero(pluginNodeId),
      timeoutInSeconds);
  }

  /**
   * Open the pipeline actions menu
   */
  public static void openPipelineActionsMenu() {
    ElementHelper.clickOnElement(CdfPipelineRunLocators.pipelineActionsButton);
  }

  /**
   * Select the action for pipeline
   *
   * @param action i.e. Duplicate / Export / Delete
   */
  public static void selectPipelineAction(String action) {
    ElementHelper.clickOnElement(CdfPipelineRunLocators.locatePipelineAction(action));
  }
}
