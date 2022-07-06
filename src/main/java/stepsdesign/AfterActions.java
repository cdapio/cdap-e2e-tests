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

package stepsdesign;


import io.cdap.e2e.utils.ConstantsUtil;
import io.cdap.e2e.utils.SeleniumDriver;
import io.cdap.e2e.utils.SeleniumHelper;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.AfterStep;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Represents after action
 */
public class AfterActions {

  @AfterStep
  public static void takeScreenshot(Scenario scenario) throws IOException {
    if (SeleniumHelper.readParameters(ConstantsUtil.SCREENSHOT_FOR_ALL_STEPS).equalsIgnoreCase("true")
      || scenario.isFailed()) {
      try {
        WebDriver driver = SeleniumDriver.getDriver();
        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshotBytes, "image/png");
      } catch (Exception e) {
        scenario.write("Exception in capturing screenshot : " + e.getMessage() + " : " + e);
      }
    }
  }
}
