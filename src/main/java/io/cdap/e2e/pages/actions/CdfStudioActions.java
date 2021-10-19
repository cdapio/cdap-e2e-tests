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

import io.cdap.e2e.utils.SeleniumDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Represents CdfStudioActions
 */
public class CdfStudioActions {
  public static io.cdap.e2e.pages.locators.CdfStudioLocators cdfStudioLocators = null;

  static {
    cdfStudioLocators =
      PageFactory.initElements(SeleniumDriver.getDriver(), io.cdap.e2e.pages.locators.CdfStudioLocators.class);
  }

  public static void selectGCS() throws InterruptedException {
    Thread.sleep(3000);
    io.cdap.e2e.pages.locators.CdfStudioLocators.gcsBucket.click();
  }


  public static void clickSource() {
    io.cdap.e2e.pages.locators.CdfStudioLocators.source.click();
  }


  public static void sinkBigQuery() {
    io.cdap.e2e.pages.locators.CdfStudioLocators.sink.click();
    io.cdap.e2e.pages.locators.CdfStudioLocators.bigQueryObject.click();
  }


  public static void runAndPreviewData() throws InterruptedException {
    io.cdap.e2e.pages.locators.CdfStudioLocators.previewButton.click();
    io.cdap.e2e.pages.locators.CdfStudioLocators.runButton.click();
  }

  public static void previewSelect() {
    io.cdap.e2e.pages.locators.CdfStudioLocators.preview.click();
  }

  public static void pipelineName() {
    io.cdap.e2e.pages.locators.CdfStudioLocators.pipelineName.click();
  }

  public static void pipelineNameIp(String pipelinName) {
    io.cdap.e2e.pages.locators.CdfStudioLocators.pipelineNameIp.sendKeys(pipelinName);
  }

  public static void pipelineSave() {
    io.cdap.e2e.pages.locators.CdfStudioLocators.pipelineSave.click();
  }

  public static void pipelineDeploy() {
    io.cdap.e2e.pages.locators.CdfStudioLocators.pipelineDeploy.click();
  }

  public static String bannerErrorMessage() throws InterruptedException {
    return io.cdap.e2e.pages.locators.CdfStudioLocators.bannerMssge.getText();

  }

}
