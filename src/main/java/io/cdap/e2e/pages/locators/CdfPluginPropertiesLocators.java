/*
 * Copyright © 2022 Cask Data, Inc.
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

import java.util.List;

/**
 * Represents CDF Plugin Properties page locators
 */
public class CdfPluginPropertiesLocators {

  @FindBy(how = How.XPATH, using = "//*[contains(@class, 'modal-title')]//span")
  public static WebElement pluginPropertiesPageHeader;

  @FindBy(how = How.XPATH, using = "//button[@data-cy='get-schema-btn'][./span[contains(text(), 'Get Schema')]]")
  public static WebElement getSchemaButton;

  @FindBy(how = How.XPATH, using = "//button[@data-cy='get-schema-btn']//span[contains(@class, 'fa-spin')]")
  public static WebElement loadingSpinnerOnGetSchemaButton;

  @FindBy(how = How.XPATH, using = "//button[contains(@class, 'validate-btn')]")
  public static WebElement validateButton;

  @FindBy(how = How.XPATH, using = "//button[contains(@class, 'validate-btn')]//span[contains(@class, 'fa-spin')]")
  public static WebElement loadingSpinnerOnValidateButton;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='plugin-validation-success-msg']")
  public static WebElement pluginValidationSuccessMsg;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='plugin-validation-error-msg']")
  public static WebElement pluginValidationErrorMsg;

  @FindBy(how = How.XPATH, using = "//h2[contains(text(), 'Errors')]" +
    "/following-sibling::div[contains(@class, 'text-danger')]//li")
  public static WebElement errorMessageOnHeader;

  @FindBy(how = How.XPATH,
    using = "(//h2[text()='Input Records']/parent::div//div[not(@data-cy='preview-data-row')]/div[text()!=''])")
  public static List<WebElement> previewInputRecordsTableColumnNames;

  @FindBy(how = How.XPATH,
    using = "(//h2[text()='Output Records']/parent::div//div[not(@data-cy='preview-data-row')]/div[text()!=''])")
  public static List<WebElement> previewOutputRecordsTableColumnNames;

  @FindBy(how = How.XPATH, using = "//*[contains(@data-cy, 'schema-row')]")
  public static List<WebElement> outputSchemaRows;

  public static WebElement locateMacroButtonOfProperty(String pluginProperty) {
    return SeleniumDriver.getDriver().findElement(By.xpath("//div[@data-cy='" + pluginProperty + "']/button"));
  }

  public static WebElement locateMacroInputOfProperty(String pluginProperty) {
    return SeleniumDriver.getDriver().findElement(By.xpath("//input[@data-cy='" + pluginProperty + "']"));
  }

  public static WebElement locateMacroTextareaOfProperty(String pluginProperty) {
    return SeleniumDriver.getDriver().findElement(By.xpath("//textarea[@data-cy='" + pluginProperty + "']"));
  }

  public static By locatorOfLoadingSpinnerOnGetSchemaButton() {
    return By.xpath("//button[@data-cy='get-schema-btn']//span[contains(@class, 'fa-spin')]");
  }

  public static By locatorOfLoadingSpinnerOnValidateButton() {
    return By.xpath("//button[contains(@class, 'validate-btn')]//span[contains(@class, 'fa-spin')]");
  }

  public static WebElement locatePluginPropertyInlineError(String propertyName) {
    return SeleniumDriver.getDriver().findElement(
      By.xpath("//*[@data-cy='" + propertyName + "']/following-sibling::div[@data-cy='property-row-error']"));
  }

  public static WebElement locateTab(String tabName) {
    return SeleniumDriver.getDriver().findElement(
      By.xpath("//*[@role='tablist']/li[normalize-space(text())='" + tabName + "']"));
  }

  public static WebElement locatePropertyElement(String pluginProperty) {
    return SeleniumDriver.getDriver().findElement(By.xpath("//*[@data-cy='" + pluginProperty + "']"));
  }

  public static By locateDropdownListItem(String option) {
    return By.xpath("//li[@data-value='" + option + "']");
  }

  public static WebElement locatePropertyInput(String pluginProperty) {
    return SeleniumDriver.getDriver().findElement(By.xpath("//input[@data-cy='" + pluginProperty + "']"));
  }

  public static WebElement locatePropertyTextArea(String pluginProperty) {
    return SeleniumDriver.getDriver().findElement(By.xpath("//textarea[@data-cy='" + pluginProperty + "']"));
  }

  public static WebElement locatePropertyButton(String pluginProperty) {
    return SeleniumDriver.getDriver().findElement(By.xpath("//button[@data-cy='" + pluginProperty + "']"));
  }

  public static WebElement locatePropertyRadioButton(String property, String value) {
    return SeleniumDriver.getDriver()
      .findElement(By.xpath("//*[@data-cy='" + property + "']//input[@type='radio' and @value='" + value + "']"));
  }

  public static WebElement locatePropertyToggle(String pluginProperty) {
    return SeleniumDriver.getDriver()
      .findElement(By.xpath("//*[@data-cy='" + pluginProperty + "']//*[@data-cy]"));
  }

  public static WebElement locatePropertyDropdownSelectedItem(String pluginProperty) {
    return SeleniumDriver.getDriver().findElement(By.xpath("//*[@data-cy='" + pluginProperty + "']/input"));
  }

  public static By locatorOfSelectedPropertyRadioButton(String property, String value) {
    return By.xpath("//*[@data-cy='" + property + "']" +
      "//input[@type='radio' and @value='" + value + "' and @checked]//ancestor::label");
  }

  public static By locateOutputSchemaFieldTypeEntry(String fieldName, String fieldType) {
    return By.xpath("//input[@value='" + fieldName + "']/following-sibling::div//select[@title='" + fieldType + "']");
  }

  public static By locatorOfPropertyElement(String pluginProperty) {
    return By.xpath("//*[@data-cy='" + pluginProperty + "']");
  }

  public static By locatorOfPropertyElementText(String pluginProperty) {
    return By.xpath("//*[@data-cy='" + pluginProperty + "' and text()]");
  }

  public static By locatorOfElementContainingText(String text) {
    return By.xpath("//*[contains(text(),'" + text + "')]");
  }

  public static By locatorOfElementWithText(String text) {
    return By.xpath("//*[text()='" + text + "']");
  }

  public static WebElement locateElementContainingText(String text) {
    return SeleniumDriver.getDriver().findElement(locatorOfElementContainingText(text));
  }

  public static WebElement locateElementWithText(String text) {
    return SeleniumDriver.getDriver().findElement(locatorOfElementWithText(text));
  }

  public static By locatorOfOutputSchemaFieldTypeEntry(String fieldName, String fieldType) {
    return By.xpath("//input[@value='" + fieldName + "']/following-sibling::div//select[@title='" + fieldType + "']");
  }

  public static WebElement locateOutputSchemaRow(int rowIndex) {
    return SeleniumDriver.getDriver().findElement(By.xpath("//*[contains(@data-cy, '" + rowIndex + "')]"));
  }

  public static WebElement locateExpandButtonOfSchemaRow(String fieldName) {
    String xpath = "//input[@value='" + fieldName + "']" +
      "//ancestor::div[contains(@data-cy, 'schema-row')]/*[@data-cy='expand-button']";
    return SeleniumDriver.getDriver().findElement(By.xpath(xpath));
  }

  public static By locatorOfExpandButtonOfRecordFieldInsideSchemaRow(String fieldName) {
    String xpath = "//input[@value='" + fieldName + "']" +
      "/ancestor::div[contains(@data-cy,'schema-row')]/following-sibling::div[1]//*[@data-cy='expand-button']";
    return By.xpath(xpath);
  }

  public static WebElement locateExpandButtonOfRecordFieldInsideSchemaRow(String fieldName) {
    String xpath = "//input[@value='" + fieldName + "']" +
      "/ancestor::div[contains(@data-cy,'schema-row')]/following-sibling::div[1]//*[@data-cy='expand-button']";
    return SeleniumDriver.getDriver().findElement(By.xpath(xpath));
  }

  @FindBy(how = How.XPATH, using = "//div[@data-cy='plugin-undefined']//button")
  public static WebElement browseButton;

  public static WebElement locatePropertyKey(String pluginProperty, int row) {
    String xpath = "//*[@data-cy='" + pluginProperty + "']//*[@data-cy= '" + row + "']//*[@data-cy='key']/input";
    return SeleniumDriver.getDriver().findElement(By.xpath(xpath));
  }

  public static WebElement locatePropertyValue(String pluginProperty, int row) {
    String xpath = "//*[@data-cy='" + pluginProperty + "']//*[@data-cy= '" + row + "']//*[@data-cy='value']/input";
    return SeleniumDriver.getDriver().findElement(By.xpath(xpath));
  }

  public static WebElement locatePropertyAddRowButton(String pluginProperty, int row) {
    String xpath = "//*[@data-cy='" + pluginProperty + "']//*[@data-cy='" + row + "']//button[@data-cy='add-row']";
    return SeleniumDriver.getDriver().findElement(By.xpath(xpath));
  }

  public static WebElement locateButton(String button) {
    String xpath = "//*[contains(text(),'" + button + "')]";
    return SeleniumDriver.getDriver().findElement(By.xpath(xpath));
  }

  public static WebElement locateSourcePluginNameInList(String pluginName) {
    String xpath = "//div[contains(text(),'" + pluginName + "')]";
    return SeleniumDriver.getDriver().findElement(By.xpath(xpath));
  }

  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Deploy Replication Job')]")
  public static WebElement deployReplicationPipeline;

  @FindBy(how = How.XPATH, using = "//*[contains(text(), 'Running')]")
  public static WebElement running;

  public static By start = By.xpath("//*[contains(@class, 'icon-play ')]");

  @FindBy(how = How.XPATH, using = "//*[contains(text(), 'Logs')]")
  public static WebElement logs;

  @FindBy(how = How.XPATH, using = "//button[@data-testid='view-advanced-logs']")
  public static WebElement advancedLogs;

  @FindBy(how = How.XPATH, using = "//*[contains(@class, 'icon-stop')]")
  public static WebElement stop;

  @FindBy(how = How.XPATH, using = "//h5[. = 'Error']/following-sibling::*")
  public static WebElement rowError;

  public static By reviewAssessment() {
    return By.xpath("//h4[contains(text(), 'Review assessment')]");
  }

  public static By configureProperties() {
    return By.xpath("//h3[contains(text(), 'Configure advanced properties')]");
  }

  public static WebElement locateValueAddButton(String pluginProperty, int index) {
    List<WebElement> valueList = SeleniumDriver.getDriver().findElements(By.xpath
      ("//*[@data-cy='" + pluginProperty + "']//input"));
    return valueList.get(index);
  }

  public static WebElement locateKeyAddButton(String pluginProperty) {
    return SeleniumDriver.getDriver().findElement
      (By.xpath("//*[@data-cy='" + pluginProperty + "']//button[@data-cy='add-row']"));
  }
  public static WebElement locateRemoveKeyButton(String pluginProperty) {
    return SeleniumDriver.getDriver().findElement
      (By.xpath("//*[@data-cy='" + pluginProperty + "']//button[@data-cy='remove-row']"));
  }

  public static WebElement selectReplicationTable(String tableName) {
    String path = "//div[contains(text(),'" + tableName + "')]" + "/preceding-sibling::div/span";
    return SeleniumDriver.getDriver().findElement(By.xpath(path));
  }
}
