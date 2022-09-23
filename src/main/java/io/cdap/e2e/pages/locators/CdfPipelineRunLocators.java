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
package io.cdap.e2e.pages.locators;

import io.cdap.e2e.utils.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Represents Cdf Pipeline Run Page Locators
 */
public class CdfPipelineRunLocators {
  @FindBy(xpath = "//div[@data-cy='pipeline-run-btn']//*[contains(@class, 'icon-play')]")
  public static WebElement run;

  @FindBy(xpath = "//div[@data-cy='pipeline-configure-btn']//*[contains(@class, 'configure-icon')]")
  public static WebElement configure;

  @FindBy(xpath = "//div[contains(@class, 'pipeline-stop-btn')]//*[contains(@class, 'icon-stop')]")
  public static WebElement stop;

  @FindBy(xpath = "//span[contains(@class, 'run-status-bubble')]//*[contains(@class, 'icon-circle')]")
  public static WebElement runPipelineStatus;

  @FindBy(xpath = "//*[@data-cy=\"Deployed\"]")
  public static WebElement deployedStatus;

  @FindBy(xpath = "//*[@data-cy=\"Provisioning\"]")
  public static WebElement provisioningStatus;

  @FindBy(xpath = "//*[@data-cy=\"Stopped\"]")
  public static WebElement stoppedStatus;

  @FindBy(xpath = "//*[@data-cy=\"Starting\"]")
  public static WebElement startingStatus;

  @FindBy(xpath = "//*[@data-cy=\"Failed\"]")
  public static WebElement failedStatus;

  @FindBy(xpath = "//*[@data-cy=\"Succeeded\"]")
  public static WebElement succeededStatus;

  @FindBy(xpath = "//*[@data-cy=\"Running\"]")
  public static WebElement runningStatus;

  public static WebElement locatePipelineStatus(String status) {
    return SeleniumDriver.getDriver().findElement(By.xpath("//*[@data-cy='" + status + "']"));
  }

  @FindBy(xpath = "//div[@data-cy='log-viewer-btn-toggle']//div[contains(@class, 'run-logs-btn')]")
  public static WebElement logs;

  @FindBy(xpath = "//span[text()='Download All']/parent::a/following-sibling::div//button")
  public static WebElement logsArrow;

  @FindBy(xpath = "//*[contains(text(), 'View Raw Logs') ]")
  public static WebElement viewRawLogs;

  @FindBy(how = How.XPATH, using = "(//*[contains(@class, 'metric-records-out-label')])[1]/following-sibling::span")
  public static WebElement recordsInCount;

  public static WebElement locateRecordsInCountOnPluginNodeAsNonZero(String pluginNodeId) {
    String xpath = "//*[@data-nodeid='" + pluginNodeId + "']" +
      "//*[contains(text(),'In')]//following-sibling::span[text()!='0']";
    return SeleniumDriver.getDriver().findElement(By.xpath(xpath));
  }

  @FindBy(how = How.XPATH, using = "(//*[contains(@class, 'metric-records-out-label')])[2]/following-sibling::span")
  public static WebElement recordsOutCount;

  @FindBy(how = How.XPATH, using = "/html/body/pre")
  public static WebElement logsTextbox;

  @FindBy(how = How.XPATH, using = "//button[@data-cy='run-deployed-pipeline-modal-btn']")
  public static WebElement deployedConfigRunButton;

  /* TODO: Move this locator to CdfStudioLocators class */
  @FindBy(how = How.XPATH, using = "//*[contains(text(),'saved successfully.')]")
  public static WebElement savedSuccessMessage;

  @FindBy(how = How.XPATH, using = "//div[contains(@class,'pipeline-actions-btn')]")
  public static WebElement pipelineActionsButton;

  public static WebElement locatePipelineAction(String action) {
    return SeleniumDriver.getDriver().findElement(
      By.xpath("//div[contains(@class,'pipeline-actions-popper')]//li[text()='" + action + "']"));
  }
}
