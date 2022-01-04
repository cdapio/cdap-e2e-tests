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

import io.cdap.e2e.pages.locators.CdfHomeLocators;
import io.cdap.e2e.utils.SeleniumDriver;
import io.cdap.e2e.utils.SeleniumHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.cdap.e2e.utils.ConstantsUtil.JS_CLICK;
import static io.cdap.e2e.utils.ConstantsUtil.PASS_CASE;

/**
 * Represents CdfHomeActions
 */
public class CdfHomeActions {
  private static final Logger logger = LoggerFactory.getLogger(CdfHomeActions.class);
  public static CdfHomeLocators cdfHomeLocators;

  static {
    cdfHomeLocators = SeleniumHelper.getPropertiesLocators(CdfHomeLocators.class);
  }

  public static void clickStudio() {
    JavascriptExecutor js = (JavascriptExecutor) SeleniumDriver.getDriver();
    WebElement element = CdfHomeLocators.studio;
    js.executeScript(JS_CLICK, element);
    logger.info(PASS_CASE);
  }
}
