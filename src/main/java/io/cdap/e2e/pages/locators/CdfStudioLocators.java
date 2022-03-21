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
 * Represents Cdf Studio Page Locators
 */
public class CdfStudioLocators {

  @FindBy(how = How.XPATH, using = "//div[contains(@class, 'left-top-section')]//select")
  public static WebElement dataPipelineTypeDropdown;

  @FindBy(how = How.XPATH, using = "//*[@data-cy=\"plugin-GCSFile-batchsource\"]")
  public static WebElement source;

  @FindBy(how = How.XPATH, using = "//*[@data-cy=\"plugin-GCSFile-batchsource\"]")
  public static WebElement gcsBucket;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='plugin-BigQueryTable-batchsink']")
  public static WebElement bigQueryObject;

  @FindBy(how = How.XPATH, using = "//div[@data-cy='plugin-Sink-group']//span[normalize-space(text())='Sink']")
  public static WebElement sink;

  @FindBy(how = How.XPATH, using = "//*[contains(@class,'plugin-endpoint_SAP-ODP')]")
  public static WebElement fromSAPODP;

  @FindBy(how = How.XPATH, using = "//*[contains(@class,'plugin-endpoint_GCS')]")
  public static WebElement fromGCS;

  @FindBy(how = How.XPATH, using = "//*[@title='BigQuery']")
  public static WebElement toBigQiery;

  @FindBy(how = How.XPATH, using = "//*[@title=\"BigQuery\"]//following-sibling::div")
  public static WebElement bigQueryProperties;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='pipeline-preview-btn']")
  public static WebElement previewButton;

  @FindBy(how = How.XPATH, using = "//div[@data-cy='preview-top-run-btn']//div[normalize-space(text())='Run']")
  public static WebElement runButton;

  @FindBy(how = How.XPATH, using = "//*[contains(text(),'Preview')]")
  public static WebElement preview;

  @FindBy(how = How.XPATH, using = "//div[@data-cy='pipeline-metadata']//div[contains(@class, 'pipeline-name')]")
  public static WebElement pipelineName;

  @FindBy(how = How.XPATH, using = "//*[@id=\"pipeline-name-input\"]")
  public static WebElement pipelineNameIp;

  @FindBy(how = How.XPATH, using = "//textarea[@data-cy='pipeline-description-input']")
  public static WebElement pipelineDescriptionTextarea;

  @FindBy(how = How.XPATH, using = "//div[@data-cy='pipeline-metadata']//button[contains(@class, 'save-button')]")
  public static WebElement pipelineSave;

  @FindBy(how = How.XPATH, using = "//*[contains(text(),'saved successfully.')]")
  public static WebElement savedSuccessMessage;

  @FindBy(how = How.XPATH, using = "//*[@data-cy=\"deploy-pipeline-btn\"]")
  public static WebElement pipelineDeploy;

  public static By locatorOfdeployingPipelineMessage() {
    return By.xpath("//h2[contains(text(), 'Deploying Pipeline')]");
  }

  @FindBy(how = How.XPATH, using = "//h2[contains(text(), 'Deploying Pipeline')]")
  public static WebElement deployingPipelineMessage;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='preview-configure-run-btn']")
  public static WebElement previewConfigRunButton;

  @FindBy(how = How.XPATH, using = "//div[contains(@class,'log-viewer')]")
  public static WebElement previewLogsButton;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='valium-banner-hydrator']")
  public static WebElement statusBanner;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='valium-banner-hydrator']//span")
  public static WebElement statusBannerText;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='valium-banner-hydrator']//button")
  public static WebElement statusBannerCloseButton;

  public static By statusBannerCloseButtonLocator = By.xpath("//*[@data-cy='valium-banner-hydrator']//button");

  @FindBy(how = How.XPATH, using = "//*[@data-cy='plugin-BigQueryTable-batchsource']")
  public static WebElement sourceBigQuery;

  @FindBy(how = How.XPATH, using = "//span[contains(@class, 'fa-remove')]")
  public static WebElement closeButton;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='plugin-GCS-batchsink']")
  public static WebElement gcs;

  @FindBy(how = How.XPATH, using = "//*[contains(@class,'plugin-endpoint_BigQuery')]")
  public static WebElement fromBigQuery;

  @FindBy(how = How.XPATH, using = "//*[@title='GCS']")
  public static WebElement toGCS;

  @FindBy(how = How.XPATH, using = "//*[@title=\"GCS\"]//following-sibling::div")
  public static WebElement gcsProperties;

  public static WebElement runtimeArgsValue(String runtimeArgsKey) {
    return SeleniumDriver.getDriver().findElement(
      By.xpath("//input[@value='" + runtimeArgsKey + "']/ancestor::div[@data-cy='runtimeargs-key']" +
        "/following-sibling::div//textarea"));
  }

  public static WebElement sourceEndpointWithTitle(String source, String sourceTitle) {
    return SeleniumDriver.getDriver().findElement(
      By.xpath("//div[@title='" + sourceTitle + "']/ancestor::div[contains(@data-cy,'plugin-node-" + source + "') " +
        "and @data-type='batchsource']//*[contains(@data-cy,'plugin-endpoint-" + source + "')]"));
  }

  public static WebElement sinkNodeWithTitle(String sink, String sinkTitle) {
    return SeleniumDriver.getDriver().findElement(
      By.xpath("//*[contains(@data-cy,'plugin-node-" + sink + "') " +
        "and @data-type='batchsink']//div[@title='" + sinkTitle + "']"));
  }

  public static WebElement locateSourceEndpointInCanvas(String sourcePluginName) {
    return SeleniumDriver.getDriver().findElement(
      By.xpath("//*[contains(@class,'plugin-endpoint_" + sourcePluginName + "')]"));
  }

  public static WebElement locateSinkNodeInCanvas(String sinkPluginName) {
    return SeleniumDriver.getDriver().findElement(
      By.xpath("//*[contains(@data-cy,'plugin-node-" + sink + "') and @data-type='batchsink']"));
  }

  public static WebElement macroButton(String pluginProperty) {
    return SeleniumDriver.getDriver().findElement(By.xpath("//div[@data-cy='" + pluginProperty + "']/button"));
  }

  public static WebElement macroInput(String pluginProperty) {
    return SeleniumDriver.getDriver().findElement(By.xpath("//input[@data-cy='" + pluginProperty + "']"));
  }

  public static WebElement locateDataPipelineTypeDropdownOption(String option) {
    String xpath = "//div[contains(@class, 'left-top-section')]//select//option[contains(@label, '" + option + "')]";
    return SeleniumDriver.getDriver().findElement(By.xpath(xpath));
  }

  public static WebElement locatePluginGroupExpanded(String pluginGroupName) {
    String xpath = "//span[normalize-space(text())='" + pluginGroupName + "']" +
      "/preceding-sibling::span[contains(@class, 'caret-down')]";
    return SeleniumDriver.getDriver().findElement(By.xpath(xpath));
  }

  public static By locatorOfPluginGroupCollapsed(String pluginGroupName) {
    String xpath = "//span[normalize-space(text())='" + pluginGroupName + "']" +
      "/preceding-sibling::span[contains(@class, 'caret-right')]";
    return By.xpath(xpath);
  }

  public static WebElement locatePluginGroupCollapsed(String pluginGroupName) {
    return SeleniumDriver.getDriver().findElement(locatorOfPluginGroupCollapsed(pluginGroupName));
  }

  public static WebElement locatePluginNameInList(String pluginName, String  pluginGroupName) {
    String xpath = "//div[@data-cy='plugin-" + pluginGroupName + "-group']" +
      "//div[contains(@class, 'plugin-name')][normalize-space(text()) = '" + pluginName + "']";
    return SeleniumDriver.getDriver().findElement(By.xpath(xpath));
  }

  public static WebElement locateSinkPluginNameInList(String pluginName) {
    String xpath = "//*[@data-cy='plugin-" + pluginName + "-batchsink']";
    return SeleniumDriver.getDriver().findElement(By.xpath(xpath));
  }

  public static WebElement locateRealtimeSourcePluginNameInList(String pluginName) {
    String xpath = "//*[@data-cy='plugin-" + pluginName + "-streamingsource']";
    return SeleniumDriver.getDriver().findElement(By.xpath(xpath));
  }

  public static WebElement locatePluginEndpointInCanvas(String pluginTitle) {
    return SeleniumDriver.getDriver().findElement(
      By.xpath("//div[@title='" + pluginTitle + "']/ancestor::div[contains(@data-cy,'plugin-node-')]" +
                 "//*[contains(@data-cy,'plugin-endpoint-')]"));
  }

  public static WebElement locatePluginNodeInCanvas(String pluginName) {
    String xpath = "//div[contains(@class, 'node-name')][@title= '" + pluginName + "']";
    return SeleniumDriver.getDriver().findElement(By.xpath(xpath));
  }

  public static WebElement locatePluginPropertiesButton(String pluginName) {
    String xpath = "//div[contains(@class, 'node-name')][@title= '" + pluginName + "']" +
      "/following-sibling::button[@data-cy='node-properties-btn']";
    return SeleniumDriver.getDriver().findElement(By.xpath(xpath));
  }

  public static WebElement locateSourcePluginPreviewDataLinkOnPluginNode(String pluginName) {
    return SeleniumDriver.getDriver().findElement(
      By.xpath("//*[@data-type='batchsource']//div[contains(@data-cy,'-preview-data-btn') " +
                 "and contains(@data-cy,'" + pluginName + "')]//a"));
  }

  public static WebElement locateSinkPluginPreviewDataLinkOnPluginNode(String pluginName) {
    return SeleniumDriver.getDriver().findElement(
      By.xpath("//*[@data-type='batchsink']//div[contains(@data-cy,'-preview-data-btn') " +
                 "and contains(@data-cy,'" + pluginName + "')]//a"));
  }

  /**
   * @deprecated Use {@link CdfPluginPropertiesLocators#validateButton}
   */
  @Deprecated
  @FindBy(how = How.XPATH, using = "//button[contains(@class, 'validate-btn')]")
  public static WebElement validateButton;

  /**
   * @deprecated Use {@link CdfPluginPropertiesLocators#locatorOfLoadingSpinnerOnValidateButton()}
   * @return
   */
  @Deprecated
  public static By locatorOfLoadingSpinnerOnValidateButton() {
    return By.xpath("//button[contains(@class, 'validate-btn')]//span[contains(@class, 'fa-spin')]");
  }

  /**
   * @deprecated Use {@link CdfPluginPropertiesLocators#loadingSpinnerOnValidateButton}
   */
  @Deprecated
  @FindBy(how = How.XPATH, using = "//button[contains(@class, 'validate-btn')]//span[contains(@class, 'fa-spin')]")
  public static WebElement loadingSpinnerOnValidateButton;

  /**
   * @deprecated Use {@link CdfPluginPropertiesLocators#pluginValidationErrorMsg}
   */
  @Deprecated
  @FindBy(how = How.XPATH, using = "//*[@data-cy='plugin-validation-error-msg']")
  public static WebElement pluginValidationErrorMsg;
}
