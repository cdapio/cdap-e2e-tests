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
import io.cdap.e2e.utils.SeleniumDriver;
import io.cdap.e2e.utils.SeleniumHelper;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static io.cdap.e2e.utils.ConstantsUtil.COLOR;
import static io.cdap.e2e.utils.ConstantsUtil.JS_CLICK;
import static io.cdap.e2e.utils.ConstantsUtil.ONE;
import static io.cdap.e2e.utils.ConstantsUtil.WAIT_TIME;

/**
 * Represents CdfPipelineRunAction
 */
public class CdfPipelineRunAction {
  private static final Logger logger = LoggerFactory.getLogger(CdfPipelineRunAction.class);
  public static CdfPipelineRunLocators cdfPipelineRunLocators;

  static {
    cdfPipelineRunLocators = SeleniumHelper.getPropertiesLocators(CdfPipelineRunLocators.class);
  }

  public static void runClick() throws InterruptedException {
    SeleniumHelper.waitAndClick(cdfPipelineRunLocators.run, WAIT_TIME);
  }

  public static String runPipelineStatus() {
    return cdfPipelineRunLocators.runPipelineStatus.getAttribute(COLOR);
  }

  public static Boolean isRunning() {
    return provideState(cdfPipelineRunLocators.runningStatus);
  }

  public static void logsClick() {
    cdfPipelineRunLocators.logs.click();
  }

  public static Boolean isProvisioning() {
    return provideState(cdfPipelineRunLocators.provisioningStatus);
  }

  private static boolean provideState(WebElement element) {
    boolean bool = false;
    try {
      bool = element.isDisplayed();
    } catch (NoSuchElementException e) {
      logger.error("Element not found" + e);
    }
    return bool;
  }

  public static String captureRawLogs() {
    JavascriptExecutor js = (JavascriptExecutor) SeleniumDriver.getDriver();
    js.executeScript(JS_CLICK, cdfPipelineRunLocators.logsArrow);
    cdfPipelineRunLocators.viewRawLogs.click();
    String parent = SeleniumDriver.getDriver().getWindowHandle();
    ArrayList<String> tabs2 = new ArrayList<>(SeleniumDriver.getDriver().getWindowHandles());
    SeleniumDriver.getDriver().switchTo().window(tabs2.get(ONE));
    String logs = CdfPipelineRunLocators.logsTextbox.getText();
    Assert.assertNotNull(logs);
    SeleniumDriver.getDriver().close();
    SeleniumDriver.getDriver().switchTo().window(parent);
    return logs;
  }

  public static void schemaStatusValidation() {
    Assert.assertTrue(CdfPipelineRunLocators.getSchemaSuccessStatus.isDisplayed());
  }
}
