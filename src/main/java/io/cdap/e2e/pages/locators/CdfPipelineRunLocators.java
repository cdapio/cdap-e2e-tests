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

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Represents CdfPipelineRunLocators
 */
public class CdfPipelineRunLocators {
  @FindBy(xpath = "//*[@class=\"icon-svg icon-play\"]")
  public WebElement run;

  @FindBy(xpath = "//*[@class=\"icon-svg icon-sliders configure-icon\"]")
  public WebElement configure;

  @FindBy(xpath = "//*[@class=\"btn pipeline-action-btn pipeline-stop-btn\"]")
  public WebElement stop;

  @FindBy(xpath = "//*[@class=\"icon-svg icon-circle\"]")
  public WebElement runPipelineStatus;

  @FindBy(xpath = "//*[@data-cy=\"Deployed\"]")
  public WebElement deployedStatus;

  @FindBy(xpath = "//*[@data-cy=\"Provisioning\"]")
  public WebElement provisioningStatus;

  @FindBy(xpath = "//*[@data-cy=\"Stopped\"]")
  public WebElement stoppedStatus;

  @FindBy(xpath = "//*[@data-cy=\"Starting\"]")
  public WebElement startingStatus;

  @FindBy(xpath = "//*[@data-cy=\"Failed\"]")
  public WebElement failedStatus;

  @FindBy(xpath = "//*[@data-cy=\"Succeeded\"]")
  public WebElement succeededStatus;

  @FindBy(xpath = "//*[@data-cy=\"Running\"]")
  public WebElement runningStatus;

  @FindBy(xpath = "//*[@class=\"run-logs-btn\"]")
  public WebElement logs;

  @FindBy(xpath = "(//*[@type=\"button\"])[7]")
  public WebElement logsArrow;

  @FindBy(xpath = "//*[contains(text(), 'View Raw Logs') ]")
  public WebElement viewRawLogs;

  @FindBy(how = How.XPATH, using = "(//*[contains(@class, 'metric-records-out-label')])[1]/following-sibling::span")
  public static WebElement recordsInCount;

  @FindBy(how = How.XPATH, using = "(//*[contains(@class, 'metric-records-out-label')])[2]/following-sibling::span")
  public static WebElement recordsOutCount;

  @FindBy(how = How.XPATH, using = "//*[contains(text(),'saved successfully.')]")
  public static WebElement savedSuccessMessage;

  @FindBy(how = How.XPATH, using = "/html/body/pre")
  public static WebElement logsTextbox;
}
