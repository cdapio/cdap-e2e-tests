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

import io.cdap.e2e.pages.locators.CdfSysAdminLocators;
import io.cdap.e2e.utils.SeleniumHelper;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents CdfSysAdminActions
 */
public class CdfSysAdminActions {
  private static final Logger logger = LoggerFactory.getLogger(CdfSysAdminActions.class);
  public static CdfSysAdminLocators cdfSysAdminLocators;

  static {
    cdfSysAdminLocators = SeleniumHelper.getPropertiesLocators(CdfSysAdminLocators.class);
  }

  public static void selectMacroAPIService(String service) {
    Select drpAPIRequest = new Select(CdfSysAdminLocators.apiCallType);
    drpAPIRequest.selectByVisibleText(service);
  }

  public static void enterURI(String service) {
    CdfSysAdminLocators.apiInputURI.sendKeys(service);
  }

  public static void enterRequestBody(String requestBody) {
    CdfSysAdminLocators.requestBody.sendKeys(requestBody);
  }

  public static void clearRequest() {
    CdfSysAdminLocators.clearButton.click();
  }

  public static void clearAllRequest() {
    CdfSysAdminLocators.clearAll.click();
  }

  public static void sendRequest() {
    CdfSysAdminLocators.sendButton.click();
  }

  public static void verifySuccess() {
    boolean checkParam = false;
    String verify = CdfSysAdminLocators.success200.getText();
    if (verify.contains("200")) {
      checkParam = true;
      logger.info("Success");
    }
    Assert.assertTrue(checkParam);
  }
}
