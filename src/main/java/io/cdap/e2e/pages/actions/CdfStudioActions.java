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
import io.cdap.e2e.utils.AssertionHelper;
import io.cdap.e2e.utils.ConstantsUtil;
import io.cdap.e2e.utils.ElementHelper;
import io.cdap.e2e.utils.SeleniumDriver;
import io.cdap.e2e.utils.SeleniumHelper;
import io.cdap.e2e.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents Cdf Studio Page Actions
 */
public class CdfStudioActions {
  private static final Logger logger = LoggerFactory.getLogger(CdfStudioActions.class);

  static {
    SeleniumHelper.getPropertiesLocators(CdfStudioLocators.class);
  }

  /**
   * Select Data Pipeline type using the select dropdown
   *
   * @param option String: Batch or Realtime
   */
  public static void selectDataPipelineType(String option) {
    ElementHelper.clickOnElement(CdfStudioLocators.dataPipelineTypeDropdown);
    // ElementHelper.click() is avoided here as 'scrollToElement()' fails because of the 'Copy to Clipboard' chrome
    // notification. Refer: https://cdap.atlassian.net/browse/CDAP-18885 for screenshot
    CdfStudioLocators.locateDataPipelineTypeDropdownOption(option).click();
    WaitHelper.waitForElementToBeSelected(CdfStudioLocators.locateDataPipelineTypeDropdownOption(option));
  }

  /**
   * Expand Plugin group if it not already expanded
   *
   * @param pluginGroupName String: Source, Transform, Analytics, Sink, etc.
   */
  public static void expandPluginGroupIfNotAlreadyExpanded(String pluginGroupName) {
    ElementHelper.clickIfDisplayed(CdfStudioLocators.locatorOfPluginGroupCollapsed(pluginGroupName));
    WaitHelper.waitForElementToBeDisplayed(CdfStudioLocators.locatePluginGroupExpanded(pluginGroupName));
  }

  /**
   * Select Plugin from the list
   *
   * @param pluginName
   */
  public static void selectPluginFromList(String pluginName) {
    logger.info("Click on plugin from the list: " + pluginName);
    ElementHelper.clickOnElement(CdfStudioLocators.locatePluginNameInList(pluginName));
  }

  /**
   * Select Sink Plugin from the list
   *
   * @param pluginName
   */
  public static void selectSinkPlugin(String pluginName) {
    expandPluginGroupIfNotAlreadyExpanded(ConstantsUtil.SINK_PLUGIN_GROUPNAME);
    logger.info("Click on Sink plugin: " + pluginName + " from the list");
    ElementHelper.clickOnElement(CdfStudioLocators.locateSinkPluginNameInList(pluginName));
  }

  /**
   * Select Realtime Source Plugin from the list
   *
   * @param pluginName
   */
  public static void selectRealtimeSourcePlugin(String pluginName) {
    logger.info("Click on Realtime Source plugin: " + pluginName + " from the list");
    ElementHelper.clickOnElement(CdfStudioLocators.locateRealtimeSourcePluginNameInList(pluginName));
  }

  /**
   * Navigate to the Plugin's properties page by hovering over the Plugin node and clicking on the Properties button
   *
   * @param pluginName
   */
  public static void navigateToPluginPropertiesPage(String pluginName) {
    ElementHelper.hoverOverElement(CdfStudioLocators.locatePluginNodeInCanvas(pluginName));
    ElementHelper.clickOnElement(CdfStudioLocators.locatePluginPropertiesButton(pluginName));
  }

  /**
   * Click on the Pipeline name on the top panel
   */
  public static void pipelineName() {
    ElementHelper.clickOnElement(CdfStudioLocators.pipelineName);
  }

  /**
   * Fill Pipeline's name
   *
   * @param pipelinName Pipeline name
   */
  public static void pipelineNameIp(String pipelinName) {
    ElementHelper.sendKeys(CdfStudioLocators.pipelineNameIp, pipelinName);
  }

  /**
   * Fill Pipeline's description
   *
   * @param pipelineDescription Pipeline description
   */
  public static void fillPipelineDescription(String pipelineDescription) {
    ElementHelper.sendKeys(CdfStudioLocators.pipelineDescriptionTextarea, pipelineDescription);
  }

  /**
   * Click on the Save button in Pipeline metadata dialog to save Pipeline's name and description
   */
  public static void pipelineSave() {
    ElementHelper.clickOnElement(CdfStudioLocators.pipelineSave);
  }

  /**
   * Fill Pipeline name in the Pipeline metadata dialog and click on the Save button
   *
   * @param pipelineName Pipeline name
   */
  public static void fillPipelineNameAndSave(String pipelineName) {
    pipelineName();
    pipelineNameIp(pipelineName);
    pipelineSave();
    WaitHelper.waitForElementToBeDisplayed(CdfStudioLocators.statusBanner);
    WaitHelper.waitForElementToBeHidden(CdfStudioLocators.statusBanner);
  }

  /**
   * Click on the Preview button on the header panel to open the Preview menu
   */
  public static void openPreviewMenu() {
    ElementHelper.clickOnElement(CdfStudioLocators.previewButton);
  }

  /**
   * Click on the Run button inside Preview menu
   */
  public static void clickPreviewRunButton() {
    ElementHelper.clickOnElement(CdfStudioLocators.runButton);
  }

  /**
   * Open the Pipeline Preview menu and click on the Run button
   */
  public static void openPipelinePreviewMenuAndClickOnRunButton() {
    openPreviewMenu();
    clickPreviewRunButton();
  }

  /**
   * Click on the Preview menu button to Open/Close the Preview menu
   */
  public static void previewSelect() {
    ElementHelper.clickOnElement(CdfStudioLocators.preview);
  }

  /**
   * Click on the Run button inside Preview - Configure dialog
   */
  public static void clickPreviewConfigRunButton() {
    ElementHelper.clickOnElement(CdfStudioLocators.previewConfigRunButton);
  }

  /**
   * Verify the Pipeline's Preview Status within the
   * Timeout: {@link ConstantsUtil#PIPELINE_PREVIEW_TIMEOUT_SECONDS}
   *
   * @param expectedPreviewStatus
   */
  public static void verifyPipelinePreviewStatus(String expectedPreviewStatus) {
    AssertionHelper.verifyElementContainsText(
      CdfStudioLocators.statusBannerText, expectedPreviewStatus, ConstantsUtil.PIPELINE_PREVIEW_TIMEOUT_SECONDS);

    if (!expectedPreviewStatus.equalsIgnoreCase("failed")) {
      WaitHelper.waitForElementToBeHidden(CdfStudioLocators.statusBanner);
    }
  }

  /**
   * Verify the Pipeline's Preview Status within the given timeout
   *
   * @param expectedPreviewStatus
   */
  public static void verifyPipelinePreviewStatus(String expectedPreviewStatus, long timeoutInSeconds) {
    AssertionHelper.verifyElementContainsText(
      CdfStudioLocators.statusBannerText, expectedPreviewStatus, timeoutInSeconds);

    if (!expectedPreviewStatus.equalsIgnoreCase("failed")) {
      WaitHelper.waitForElementToBeHidden(CdfStudioLocators.statusBanner);
    }
  }

  /**
   * Click on the Deploy button
   */
  public static void pipelineDeploy() {
    ElementHelper.clickOnElement(CdfStudioLocators.pipelineDeploy);
    WaitHelper.waitForElementToBeDisplayed(CdfStudioLocators.deployingPipelineMessage);
    WaitHelper.waitForElementToBeHidden(
      CdfStudioLocators.locatorOfdeployingPipelineMessage(), ConstantsUtil.PIPELINE_DEPLOY_TIMEOUT_SECONDS);
  }

  /**
   * To get the error message from the banner
   *
   * @return
   */
  public static String bannerErrorMessage() {
    return ElementHelper.getElementText(CdfStudioLocators.statusBannerText);
  }

  /**
   * Click on the Plugin group title: Sink
   */
  public static void clickSink() {
    ElementHelper.clickOnElement(CdfStudioLocators.sink);
  }

  /**
   * Connect Source plugin with Sink plugin
   *
   * @param source Source Plugin name
   * @param sink   Sink Plugin name
   */
  public static void connectSourceAndSink(String source, String sink) {
    WaitHelper.waitForElementToBeDisplayed(CdfStudioLocators.locateSourceEndpointInCanvas(source));
    WaitHelper.waitForElementToBeDisplayed(CdfStudioLocators.locateSinkNodeInCanvas(sink));
    ElementHelper.dragAndDrop(
      CdfStudioLocators.locateSourceEndpointInCanvas(source),
      CdfStudioLocators.locateSinkNodeInCanvas(sink));
  }

  /**
   * Connect Source plugin with Sink plugin
   *
   * @param source      Source Plugin name
   * @param sourceTitle Source Plugin's title attribute value
   * @param sink        Sink Plugin name
   * @param sinkTitle   Sink Plugin's title attribute value
   */
  public static void connectSourceAndSink(String source, String sourceTitle, String sink, String sinkTitle) {
    WebElement sinkNode = CdfStudioLocators.sinkNodeWithTitle(sink, sinkTitle);
    WaitHelper.waitForElementToBeDisplayed(sinkNode);
    ElementHelper.dragAndDrop(CdfStudioLocators.sourceEndpointWithTitle(source, sourceTitle), sinkNode);
  }

  /**
   * Connect Source plugin with Sink plugin
   *
   * @param source      Source Plugin name
   * @param sourceTitle Source Plugin's title attribute value
   * @param index       Index to identify plugins with the same name
   * @param sink        Sink Plugin name
   * @param sinkTitle   Sink Plugin's title attribute value
   */
  public static void connectSourceAndSink(String source, String sourceTitle, int index, String sink, String sinkTitle) {
    int yOffset = (index - 1) * 80;
    WebElement sinkNode = CdfStudioLocators.sinkNodeWithTitle(sink, sinkTitle);
    ElementHelper.dragAndDropByOffset(sinkNode, 0, yOffset);
    connectSourceAndSink(source, sourceTitle, sink, sinkTitle);
  }

  /**
   * @deprecated Use {@link io.cdap.e2e.utils.CdfHelper#selectSourcePlugin(String)}
   */
  @Deprecated
  public static void selectGCS() {
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
   * @deprecated Use {@link io.cdap.e2e.utils.CdfHelper#selectSourcePlugin(String)}
   */
  @Deprecated
  public static void selectBQ() {
    SeleniumHelper.waitAndClick(CdfStudioLocators.sourceBigQuery);
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

  /**
   * Click on the Validate button inside Plugin's properties page
   *
   * @deprecated Use {@link CdfPluginPropertiesActions#clickValidateButton()}
   */
  @Deprecated
  public static void clickValidateButton() {
    CdfPluginPropertiesActions.clickValidateButton();
  }

  /**
   * Click on the Close button in the Plugin's properties page
   *
   * @deprecated Use {@link CdfPluginPropertiesActions#clickCloseButton()}
   */
  @Deprecated
  public static void clickCloseButton() {
    CdfPluginPropertiesActions.clickCloseButton();
  }
}
