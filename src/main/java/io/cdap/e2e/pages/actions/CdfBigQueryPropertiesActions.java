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

import io.cdap.e2e.pages.locators.CdfBigQueryPropertiesLocators;
import io.cdap.e2e.pages.locators.CdfStudioLocators;
import io.cdap.e2e.utils.SeleniumHelper;

import java.io.IOException;

import static io.cdap.e2e.utils.ConstantsUtil.AUTOMATION_TEST;
import static io.cdap.e2e.utils.ConstantsUtil.DATASET;
import static io.cdap.e2e.utils.ConstantsUtil.ONE;
import static io.cdap.e2e.utils.ConstantsUtil.PROJECT_ID;

/**
 * Represents CdfBigQueryPropertiesActions
 */
public class CdfBigQueryPropertiesActions {
  public static CdfBigQueryPropertiesLocators cdfBigQueryPropertiesLocators;

  static {
    cdfBigQueryPropertiesLocators = SeleniumHelper.getPropertiesLocators(CdfBigQueryPropertiesLocators.class);
  }

  public static void enterBigQueryProperties(String tableProp) throws InterruptedException, IOException {
    CdfStudioLocators.bigQueryProperties.click();
    CdfBigQueryPropertiesLocators.bigQueryReferenceName.sendKeys(AUTOMATION_TEST);
    SeleniumHelper.replaceElementValue(CdfBigQueryPropertiesLocators.projectID,
                                       SeleniumHelper.readParameters(PROJECT_ID));
    CdfBigQueryPropertiesLocators.datasetProjectID.sendKeys(SeleniumHelper.readParameters(PROJECT_ID));
    CdfBigQueryPropertiesLocators.bigQueryDataSet.sendKeys(SeleniumHelper.readParameters(DATASET));
    CdfBigQueryPropertiesLocators.bigQueryTable.sendKeys(tableProp);
    CdfBigQueryPropertiesLocators.updateTable.click();
    CdfBigQueryPropertiesLocators.truncatableSwitch.click();
    CdfBigQueryPropertiesLocators.validateBttn.click();
    SeleniumHelper.waitElementIsVisible(CdfBigQueryPropertiesLocators.textSuccess, ONE);
  }

  public static void enterCmekProperty(String prop) throws IOException {
    CdfStudioLocators.bigQueryProperties.click();
    CdfBigQueryPropertiesLocators.cmekKey.sendKeys(SeleniumHelper.readParameters(prop));
  }

  public static void enterFilePath(String path) throws InterruptedException, IOException {
    SeleniumHelper.replaceElementValue(CdfBigQueryPropertiesLocators.serviceFilePath, path);
  }
}
