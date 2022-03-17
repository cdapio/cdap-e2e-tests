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
 *
 */
public class CdfPluginPropertiesLocators {

  @FindBy(how = How.XPATH, using = "//button[@data-cy='get-schema-btn'][./span[contains(text(), 'Get Schema')]]")
  public static WebElement getSchemaButton;

  public static By locatorOfLoadingSpinnerOnGetSchemaButton() {
    return By.xpath("//button[@data-cy='get-schema-btn']//span[contains(@class, 'fa-spin')]");
  }

  @FindBy(how = How.XPATH, using = "//button[@data-cy='get-schema-btn']//span[contains(@class, 'fa-spin')]")
  public static WebElement loadingSpinnerOnGetSchemaButton;

  @FindBy(how = How.XPATH, using = "//button[contains(@class, 'validate-btn')]")
  public static WebElement validateButton;

  public static By locatorOfLoadingSpinnerOnValidateButton() {
    return By.xpath("//button[contains(@class, 'validate-btn')]//span[contains(@class, 'fa-spin')]");
  }

  @FindBy(how = How.XPATH, using = "//button[contains(@class, 'validate-btn')]//span[contains(@class, 'fa-spin')]")
  public static WebElement loadingSpinnerOnValidateButton;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='plugin-validation-success-msg']")
  public static WebElement pluginValidationSuccessMsg;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='plugin-validation-error-msg']")
  public static WebElement pluginValidationErrorMsg;

  public static WebElement locatePluginPropertyInlineError(String propertyName) {
    return SeleniumDriver.getDriver().findElement(
      By.xpath("//*[@data-cy='" + propertyName + "']/following-sibling::div[@data-cy='property-row-error']"));
  }

  @FindBy(how = How.XPATH, using = "//h2[contains(text(), 'Errors')]" +
    "/following-sibling::div[contains(@class, 'text-danger')]//li")
  public static WebElement errorMessageOnHeader;
  
  @FindBy(how = How.XPATH,
    using = "(//h2[text()='Input Records']/parent::div//div[not(@data-cy='preview-data-row')]/div[text()!=''])")
  public static List<WebElement> previewInputRecordsTableColumnNames;

  @FindBy(how = How.XPATH,
    using = "(//h2[text()='Output Records']/parent::div//div[not(@data-cy='preview-data-row')]/div[text()!=''])")
  public static List<WebElement> previewOutputRecordsTableColumnNames;

  public static WebElement locateTab(String tabName) {
    return SeleniumDriver.getDriver().findElement(
      By.xpath("//*[@role='tablist']/li[normalize-space(text())='" + tabName + "']"));
  }

  public static By locateDropdownListItem(String option) {
    return By.xpath("//li[@data-value='" + option + "']");
  }
}
