/*
 * Copyright © 2023 Cask Data, Inc.
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
 * CDF wrangler related locators.
 */
public class WranglerLocators {
  @FindBy(how = How.XPATH, using = "//div[@data-testid='common-components-scrollableList-scrollDownButton']")
  public static WebElement scrollButton;

  @FindBy(how = How.XPATH, using = "//div[@class='scroll-down-container text-center disabled']" +
    "[@data-testid='common-components-scrollableList-scrollDownButton']")
  public static WebElement scrollButtonDisabled;

  @FindBy(how = How.XPATH, using = "//div[@data-testid='features-dataprep-directives-changeDataType-submenu-decimal" +
    "-scaleInput']/input")
  public static WebElement scaleText;

  @FindBy(how = How.XPATH, using = "//button[contains(text(),'Extract')]")
  public static WebElement extractButton;

  @FindBy(how = How.XPATH, using = "//button[@data-testid='features-dataprep-directives-findAndReplace-applyButton']")
  public static WebElement replaceAllButton;

  @FindBy(how = How.XPATH, using = "//input[@data-testid='features-dataprep-workspace-cli-input']")
  public static WebElement directiveCommandLine;

  @FindBy(how = How.XPATH, using = "//input[@data-testid='features-dataprep-directives-defineVariable" +
    "-variableNameInput']")
  public static WebElement variableName;

  @FindBy(how = How.XPATH, using = "//button[@data-testid='features-dataprep-directives-changeDataType-submenu" +
    "-decimal-applyButton']")
  public static WebElement applyButtonUppercase;

  @FindBy(how = How.XPATH, using = "//button[contains(text(), 'Apply')]")
  public static WebElement applyButton;

  @FindBy(how = How.XPATH, using = "//input[@data-testid='features-dataprep-directives-defineVariable-" +
    "conditionTextInput']")
  public static WebElement variableValue;

  @FindBy(how = How.XPATH, using = "//input[@data-testid='features-dataprep-directives-parse-modal-excel-" +
    "sheetNumberInput']")
  public static WebElement excelSheetNumber;

  @FindBy(how = How.XPATH, using = "//button[@data-testid='features-dataprep-directives-extractFields-modal-" +
    "patterns-patternsSelector']")
  public static WebElement selectPattern;

  @FindBy(how = How.XPATH, using = "//div[contains(@class, 'level-popover')]//input")
  public static WebElement enterText;

  @FindBy(how = How.XPATH, using = "//input[@data-testid='features-dataprep-directives-findAndReplace-oldValueInput']")
  public static WebElement enterOldValue;

  @FindBy(how = How.XPATH, using = "//div[@data-testid='features-dataprep-directives-encodeDecode-title-Encode']")
  public static WebElement encodeDirective;

  @FindBy(how = How.XPATH, using = "//div[@data-testid='features-dataprep-directives-encodeDecode-title-Decode']")
  public static WebElement decodeDirective;

  @FindBy(how = How.XPATH, using = "//input[@data-testid='features-dataprep-directives-findAndReplace-newValueInput']")
  public static WebElement enterNewValue;

  @FindBy(how = How.XPATH, using = "//div[contains(@class, 'level-popover')]//textarea")
  public static WebElement filterTextArea;

  @FindBy(how = How.XPATH, using = "//div[contains(@class, 'level-popover')]//select")
  public static WebElement filterSelect;

  @FindBy(how = How.XPATH, using = "//input[@data-testid='features-dataprep-directives-parse-modal-singleField-" +
    "mainFieldInput']")
  public static WebElement columnWidths;

  @FindBy(how = How.XPATH, using = "//select[@data-testid='features-dataprep-directives-defineVariable-" +
    "columnSelector']")
  public static WebElement selectColumn;

  @FindBy(how = How.XPATH, using = "//select[@data-testid='features-dataprep-directives-defineVariable-" +
    "conditionSelector']")
  public static WebElement selectRow;

  @FindBy(how = How.XPATH, using = "//input[@placeholder='Enter depth']")
  public static WebElement enterDepth;

  @FindBy(how = How.XPATH, using = "//input[@data-testid='features-dataprep-directives-parse-modal-dateFormats-" +
    "customFormatInput']")
  public static WebElement customDate;

  @FindBy(how = How.XPATH, using = "//input[@data-testid='features-dataprep-directives-setCounter-counterNameInput']")
  public static WebElement enterCounterName;

  @FindBy(how = How.XPATH, using = "//input[@data-testid='features-dataprep-directives-setCounter-" +
    "counterIncrementInput']")
  public static WebElement incrementCount;

  public static WebElement locateTransformationButton(String columnName) {
    return SeleniumDriver.getDriver()
      .findElement(By.xpath("//th[@id='column-" + columnName + "']//button[@data-testid='features-dataprep-workspace" +
                              "-dataTable-head-directivesDropdown-toggleButton']"));
  }

  public static WebElement locateDirectivesTitle(String directiveName) {
    return SeleniumDriver.getDriver()
      .findElement(By.xpath("//div[@data-testid='features-dataprep-" +
                              "directives-" + directiveName + "-title']"));
  }

  public static WebElement locateCheckboxOfColumn(String columnName) {
    return SeleniumDriver.getDriver()
      .findElement(By.xpath("//th[@id='column-" + columnName + "']/descendant::span[@data-testid='" +
                              "features-dataprep-workspace-dataTable-head-columnSelectToggle']"));
  }

  public static WebElement selectDirectiveOption(String option) {
    return SeleniumDriver.getDriver().findElement(By.xpath("//*[text()='" + option + "']"));
  }

  public static WebElement selectRadioButton(String optionType) {
    return SeleniumDriver.getDriver().findElement(By.xpath("//span[contains(text(),'" + optionType + "')]"));
  }

  public static WebElement selectOptionForPatterns(String optionType) {
    return SeleniumDriver.getDriver().findElement(By.xpath("//button[@data-testid='features-dataprep-" +
                                                             "directives-extractFields-modal-patterns-" +
                                                             "patternsOption-" + optionType + "']"));
  }
}
