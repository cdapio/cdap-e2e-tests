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
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

/**
 * Represents CdfPipelineRunAction
 */
public class CdfPipelineRunAction {
  public static CdfPipelineRunLocators cdfPipelineRunLocators = null;

  static {
    cdfPipelineRunLocators = PageFactory.initElements(SeleniumDriver.getDriver(), CdfPipelineRunLocators.class);
  }

  public static void runClick() throws InterruptedException {
    WebElement element = cdfPipelineRunLocators.run;
    SeleniumHelper.waitAndClick(element, 60);
  }

  public static String runPipelineStatus() {
    return cdfPipelineRunLocators.runPipelineStatus.getAttribute("color");
  }

  public static Boolean isRunning() {
    boolean bool = false;
    try {

      bool = cdfPipelineRunLocators.runningStatus.isDisplayed();
    } catch (NoSuchElementException e) {
      System.out.println("");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bool;
  }

  public static void logsClick() {
    cdfPipelineRunLocators.logs.click();
  }

  public static Boolean isProvisioning() {
    boolean bool = false;
    try {
      bool = cdfPipelineRunLocators.provisioningStatus.isDisplayed();
    } catch (NoSuchElementException e) {
      System.out.println("");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bool;
  }

  public static String captureRawLogs() {
    JavascriptExecutor js = (JavascriptExecutor) SeleniumDriver.getDriver();
    js.executeScript("arguments[0].click()", cdfPipelineRunLocators.logsArrow);
    cdfPipelineRunLocators.viewRawLogs.click();
    String parent = SeleniumDriver.getDriver().getWindowHandle();
    ArrayList<String> tabs2 = new ArrayList<String>(SeleniumDriver.getDriver().getWindowHandles());
    SeleniumDriver.getDriver().switchTo().window(tabs2.get(1));
    String logs = SeleniumDriver.getDriver().findElement(By.xpath("/html/body/pre")).getText();
    Assert.assertNotNull(logs);
    SeleniumDriver.getDriver().close();
    SeleniumDriver.getDriver().switchTo().window(parent);
    return logs;
  }
}
