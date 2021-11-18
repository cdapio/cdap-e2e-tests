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

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Represents CdfBigQueryPropertiesLocators
 */
public class CdfBigQueryPropertiesLocators {

  @FindBy(how = How.XPATH, using = "//*[@data-cy='referenceName' and @class='MuiInputBase-input']")
  public static WebElement bigQueryReferenceName;

  @FindBy(how = How.XPATH, using = "//*[@placeholder='Dataset the table belongs to']")
  public static WebElement bigQueryDataSet;

  @FindBy(how = How.XPATH, using = "//*[@data-cy=\"datasetProject\" and @class='MuiInputBase-input']")
  public static WebElement projectID;

  @FindBy(how = How.XPATH, using = "//*[@placeholder='Table to write to']")
  public static WebElement bigQueryTable;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='switch-truncateTable']")
  public static WebElement truncatableSwitch;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='switch-allowSchemaRelaxation']")
  public static WebElement updateTable;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='plugin-properties-validate-btn']")
  public static WebElement validateBttn;

  @FindBy(how = How.XPATH, using = "//*[@class='fa fa-remove']")
  public static WebElement closeButton;

  @FindBy(how = How.XPATH, using = "//*[@role='button' and contains(text(),'Actions')]")
  public static WebElement actionButton;

  @FindBy(how = How.XPATH, using = "//*[@role='option' and contains(text(),'Export')]")
  public static WebElement exportButton;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='plugin-validation-success-msg']")
  public static WebElement textSuccess;

  @FindBy(how = How.XPATH, using = "//*[@data-cy=\"cmekKey\" and @class='MuiInputBase-input']")
  public static WebElement cmekKey;
}
