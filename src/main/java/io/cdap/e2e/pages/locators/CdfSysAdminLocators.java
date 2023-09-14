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

import io.cdap.e2e.utils.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Represents CdfSysAdminLocators
 */
public class CdfSysAdminLocators {

  @FindBy(how = How.XPATH, using = "//a[@data-cy='System Admin']")
  public static WebElement systemAdminMenu;

  @FindBy(how = How.XPATH, using = "//a[@href='/cdap/administration/configuration']")
  public static WebElement configurationMenu;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='request-method-selector']")
  public static WebElement apiCallType;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='request-path-input']")
  public static WebElement apiInputURI;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='request-body']")
  public static WebElement requestBody;

  @FindBy(how = How.XPATH, using = "//*[contains(text(),'Clear')]")
  public static WebElement clearButton;

  @FindBy(how = How.XPATH, using = "//*[contains(text(),'Send')]")
  public static WebElement sendButton;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='request-path']/parent::*//child::div[2]")
  public static WebElement success200;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='Clear All']")
  public static WebElement clearAll;
  @FindBy(how = How.XPATH, using = "//span//button[contains(@class, 'btn btn-secondary')]")
  public static WebElement reloadSystemArtifacts;
  @FindBy(how = How.XPATH, using = "//a[@href='/cdap/httpexecutor']")
  public static WebElement makeHttpCall;
  @FindBy(how = How.XPATH, using = "//button[@data-testid='create-namespace-btn']")
  public static WebElement createNewNamespace;
  @FindBy(how = How.XPATH, using = "//button[@data-testid='edit-system-prefs-btn']")
  public static WebElement editSystemPreference;
  @FindBy(how = How.XPATH, using = "//a[@href='/cdap/ns/system/profiles/create']")
  public static WebElement createComputeProfile;
  @FindBy(how = How.XPATH, using = "//div//label[@for='import-profile']")
  public static WebElement clickImport;
  @FindBy(how = How.XPATH, using = "//button[@data-testid='save-prefs-btn']")
  public static WebElement clickSaveClose;
  @FindBy(how = How.XPATH, using = "//div[@data-testid='create-namespace-name']//input")
  public static WebElement namespaceName;
  @FindBy(how = How.XPATH, using = "//input[@placeholder='Namespace description' and @type='text']")
  public static WebElement namespaceDescription;
  @FindBy(how = How.XPATH, using = "//div[@data-testid='namespaces-accordion']")
  public static WebElement openNamespaceTab;
  @FindBy(how = How.XPATH, using = "//div[contains(@class,'system-profiles')]")
  public static WebElement openSystemComputeProfile;
  @FindBy(how = How.XPATH, using = "//span[contains(@class,'reset')]")
  public static WebElement clickReset;
  @FindBy(how = How.XPATH, using = "//span[contains(@class,'success reset-success')]")
  public static WebElement resetSuccessMsg;
  @FindBy(how = How.XPATH, using = "//span//button[@type='submit' and @class='btn remove-row-btn btn-link']")
  public static WebElement clickDelete;
  @FindBy(how = How.XPATH, using = "//button[@data-testid='Reload']")
  public static WebElement clickReload;
  @FindBy(how = How.XPATH, using = "//*[contains(text(),'Close')]")
  public static WebElement closeButton;
  @FindBy(how = How.XPATH, using = "//div[contains(@class, 'error-section text-danger')]")
  public static WebElement errorMessageOnFooter;
  @FindBy(how = How.XPATH, using = "//div[contains(@class, 'card-action-feedback DANGER')]")
  public static WebElement failMessage;

  public static WebElement locateMenu(String featureName) {
    return SeleniumDriver.getDriver().findElement(By.xpath("//*[@data-cy='" + featureName + "']"));
  }

  public static WebElement clickPreference(String preferences) {
    String path = "//div[@data-cy='" + preferences + "']";
    return SeleniumDriver.getDriver().findElement(By.xpath(path));
  }

  public static WebElement locateKeyProperty(String pluginProperty, int row) {
    String xpath = "//*[@data-cy='" + pluginProperty + "" + row + "']//input[@placeholder='key']";
    return SeleniumDriver.getDriver().findElement(By.xpath(xpath));
  }

  public static WebElement locateValueProperty(String pluginProperty, int row) {
    String xpath = "//*[@data-cy='" + pluginProperty + "" + row + "']//input[@placeholder='value']";
    return SeleniumDriver.getDriver().findElement(By.xpath(xpath));
  }

  public static WebElement locateAddRowButtonProperty(String pluginProperty, int row) {
    String xpath =
        "//*[@data-cy='" + pluginProperty + "" + row + "']//span"
            + "//button[@type='submit' and @class='btn add-row-btn btn-link']";
    return SeleniumDriver.getDriver().findElement(By.xpath(xpath));
  }

  public static WebElement locateDeleteRowButtonProperty(String pluginProperty, int row) {
    String xpath =
        "//*[@data-cy='" + pluginProperty + "" + row + "']//span"
            + "//button[@type='submit' and @class='btn remove-row-btn btn-link']";
    return SeleniumDriver.getDriver().findElement(By.xpath(xpath));
  }

  public static By locatorOfLoadingSpinnerOnReloadButton() {
    return By.xpath("//*[contains(@class, 'fa-spin')]");
  }

  public static WebElement locateProvisionerInList(String provisionerName) {
    return SeleniumDriver.getDriver()
        .findElement(By.xpath("//div[@data-cy='" + provisionerName + "']"));
  }

  public static WebElement locateButtonType(String buttonType) {
    return SeleniumDriver.getDriver()
        .findElement(By.xpath("//button[@data-cy='" + buttonType + "']"));
  }

  public static By profilePropertiesPage() {
    return By.xpath("//h5[contains(text(), 'Create a profile')]");
  }

  public static WebElement locateAddedProfile(String profileName) {
    String xpath = "//div[@data-cy='profile-list-" + profileName + "']";
    return SeleniumDriver.getDriver().findElement(By.xpath(xpath));
  }

  public static WebElement locateProfileTitle(String profileName) {
    String xpath = "//div[@title='" + profileName + "']";
    return SeleniumDriver.getDriver().findElement(By.xpath(xpath));
  }
}
