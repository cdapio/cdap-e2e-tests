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

import io.cdap.e2e.pages.locators.CdfStudioLocators;
import io.cdap.e2e.utils.ElementHelper;
import io.cdap.e2e.utils.SeleniumDriver;
import io.cdap.e2e.utils.SeleniumHelper;
import io.cdap.e2e.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents Cdf Studio page Actions
 */
public class CdfStudioActions {
  private static final Logger logger = LoggerFactory.getLogger(CdfStudioActions.class);
  public static CdfStudioLocators cdfStudioLocators;

  static {
    cdfStudioLocators = SeleniumHelper.getPropertiesLocators(CdfStudioLocators.class);
  }

  public static void selectDataPipelineType(String option) {
    ElementHelper.clickOnElement(CdfStudioLocators.dataPipelineTypeDropdown);
    /* TODO: Replace JsExecutor click with normal click: https://cdap.atlassian.net/browse/CDAP-18885 */
    ElementHelper.clickOnElementUsingJsExecutor(CdfStudioLocators.locateDataPipelineTypeDropdownOption(option));
  }

  public static void expandPluginGroupIfNotAlreadyExpanded(String pluginGroupName) {
    ElementHelper.clickIfDisplayed(CdfStudioLocators.locatePluginGroupCollapsed(pluginGroupName));
    WaitHelper.waitForElementToBeDisplayed(CdfStudioLocators.locatePluginGroupExpanded(pluginGroupName));
  }

  public static void selectPluginFromList(String pluginName) {
    logger.info("Click on plugin from the list: " + pluginName);
    ElementHelper.clickOnElement(CdfStudioLocators.locatePluginNameInList(pluginName));
  }

  public static void navigateToPluginPropertiesPage(String pluginName) {
    ElementHelper.hoverOverElement(CdfStudioLocators.locatePluginNodeInCanvas(pluginName));
    ElementHelper.clickOnElement(CdfStudioLocators.locatePluginPropertiesButton(pluginName));
  }

  /**
   * @deprecated Use {@link io.cdap.e2e.utils.CdfHelper#selectSourcePlugin(String)}
   */
  @Deprecated
  public static void selectGCS() throws InterruptedException {
    WebElement element = CdfStudioLocators.gcsBucket;
    SeleniumHelper.waitAndClick(element);
  }

  /**
   * @deprecated Use {@link io.cdap.e2e.utils.CdfHelper#selectSourcePlugin(String)}
   */
  @Deprecated
  public static void clickSource() {
    CdfStudioLocators.source.click();
  }

  /**
   * @deprecated Use {@link CdfStudioActions#clickSink()} and
   * {@link io.cdap.e2e.utils.CdfHelper#selectSinkPlugin(String)}
   */
  @Deprecated
  public static void sinkBigQuery() {
    CdfStudioLocators.sink.click();
    CdfStudioLocators.bigQueryObject.click();
  }

  public static void runAndPreviewData() {
    CdfStudioLocators.previewButton.click();
    CdfStudioLocators.runButton.click();
  }

  public static void previewSelect() {
    CdfStudioLocators.preview.click();
  }

  public static void pipelineName() {
    CdfStudioLocators.pipelineName.click();
  }

  public static void pipelineNameIp(String pipelinName) {
    CdfStudioLocators.pipelineNameIp.sendKeys(pipelinName);
  }

  public static void pipelineSave() {
    CdfStudioLocators.pipelineSave.click();
  }

  public static void pipelineDeploy() {
    ElementHelper.clickOnElement(CdfStudioLocators.pipelineDeploy);
  }

  public static String bannerErrorMessage() {
    return CdfStudioLocators.statusBannerText.getText();
  }

  public static void clickSink() {
    CdfStudioLocators.sink.click();
  }

  public static void clickValidateButton() {
    CdfStudioLocators.validateButton.click();
  }

  /**
   * @deprecated Use either {@link io.cdap.e2e.utils.CdfHelper#openSinkPluginProperties(String)}
   * or {@link io.cdap.e2e.utils.CdfHelper#openSourcePluginProperties(String)} as per plugin type.
   */
  @Deprecated
  public static void clickProperties(String plugin) {
    SeleniumDriver.getDriver().findElement(
      By.xpath("//*[contains(@title,'" + plugin + "')]//following-sibling::div")).click();
  }

  /**
   * @deprecated Use {@link io.cdap.e2e.utils.CdfHelper#connectSourceAndSink(String, String)}
   */
  @Deprecated
  public static void connectSourceAndSink(String source, String sink) {
    SeleniumHelper.waitElementIsVisible(SeleniumDriver.getDriver().findElement(
      By.xpath("//*[contains(@title,'" + sink + "')]")));
    SeleniumHelper.dragAndDrop(
      SeleniumDriver.getDriver().findElement(By.xpath("//*[contains(@class,'plugin-endpoint_" + source + "')]")),
      SeleniumDriver.getDriver().findElement(By.xpath("//*[contains(@title,'" + sink + "')]")));
  }

  /**
   * @deprecated Use {@link io.cdap.e2e.utils.CdfHelper#selectSourcePlugin(String)}
   */
  @Deprecated
  public static void selectBQ() throws InterruptedException {
    SeleniumHelper.waitAndClick(CdfStudioLocators.sourceBigQuery);
  }

  public static void clickCloseButton() {
    ElementHelper.clickOnElement(CdfStudioLocators.closeButton);
  }

  /**
   * @deprecated Use {@link CdfStudioActions#clickSink()} and
   * {@link io.cdap.e2e.utils.CdfHelper#selectSinkPlugin(String)}
   */
  @Deprecated
  public static void sinkGcs() {
    CdfStudioLocators.sink.click();
    CdfStudioLocators.gcs.click();
  }

  public static void clickConfigRunButton() {
    CdfStudioLocators.configRun.click();
  }

  public static void clickPreviewRunButton() {
    CdfStudioLocators.runButton.click();
  }
}
