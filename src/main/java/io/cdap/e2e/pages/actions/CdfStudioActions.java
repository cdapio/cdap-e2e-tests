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
import io.cdap.e2e.utils.ConstantsUtil;
import io.cdap.e2e.utils.SeleniumDriver;
import io.cdap.e2e.utils.SeleniumHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static io.cdap.e2e.utils.ConstantsUtil.JS_CLICK;

/**
 * Represents CdfStudioActions
 */
public class CdfStudioActions {
  public static CdfStudioLocators cdfStudioLocators;

  static {
    cdfStudioLocators = SeleniumHelper.getPropertiesLocators(CdfStudioLocators.class);
  }

  public static void selectGCS() throws InterruptedException {
    WebElement element = CdfStudioLocators.gcsBucket;
    SeleniumHelper.waitAndClick(element);
  }

  public static void clickSource() {
    CdfStudioLocators.source.click();
  }

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
    JavascriptExecutor jse = (JavascriptExecutor) SeleniumDriver.getDriver();
    jse.executeScript(JS_CLICK, CdfStudioLocators.pipelineDeploy);
  }

  public static String bannerErrorMessage() {
    return CdfStudioLocators.bannerMssge.getText();
  }

  public static void clickSink() {
    CdfStudioLocators.sink.click();
  }

  public static void selectBQ() throws InterruptedException {
    SeleniumHelper.waitAndClick(CdfStudioLocators.sourceBigQuery);
  }

  public static void sinkGcs() {
    CdfStudioLocators.sink.click();
    CdfStudioLocators.gcs.click();
  }

  public static void runAndPreviewForData() throws InterruptedException {
    CdfStudioLocators.runButton.click();
    SeleniumHelper.waitElementIsVisible(CdfStudioLocators.previewSuccessBanner, 180);
  }

  public static void clickBigQueryProperties() {
    CdfStudioLocators.bigQueryProperties.click();
  }
}
