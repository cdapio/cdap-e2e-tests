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
 * Represents CdfNameSpaceAdminLocators
 */
public class CdfNameSpaceAdminLocators {

  @FindBy(how = How.XPATH, using = "//div[contains(text(),'Namespace Admin')]")
  public static WebElement namespaceAdminMenu;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='navbar-hamburger-icon']")
  public static WebElement hamburgerMenu;

  public static WebElement nameSpaceModules(String module, String nameSpace) {
    String path = "//*[@href=\"/cdap/ns/" + nameSpace + "/details/" + module + "\"]";
    return SeleniumDriver.getDriver().findElement(By.xpath(path));
  }

  public static WebElement createProfile(String nameSpace) {
    String path = "//*[@href=\"/cdap/ns/" + nameSpace + "/profiles/create\"]";
    return SeleniumDriver.getDriver().findElement(By.xpath(path));
  }

  public static WebElement switchNameSpace(String nameSpace) {
    String path = "//*[@href=\"/cdap/ns/'" + nameSpace + "'\"]";
    return SeleniumDriver.getDriver().findElement(By.xpath(path));
  }

  @FindBy(how = How.XPATH, using = "//*[@class='namespace-dropdown']")
  public static WebElement namespaceDropdown;

  @FindBy(how = How.XPATH, using = "//div[contains(text(),'Add Namespace')]")
  public static WebElement addNamespace;

  @FindBy(how = How.XPATH, using = "//div[@data-testid='create-namespace-name']")
  public static WebElement namespaceName;

  @FindBy(how = How.XPATH, using = "//input[@placeholder='Namespace description' and @type='text']")
  public static WebElement namespaceDescription;

  @FindBy(how = How.XPATH, using = "//button[@data-cy='wizard-next-btn']")
  public static WebElement clickOnNextButton;

  @FindBy(how = How.XPATH, using = "//button[@data-cy='wizard-finish-btn']")
  public static WebElement clickOnFinishButton;

  public static WebElement locateKeyProperty(int row) {
    String xpath = "//*[@data-cy= 'key-value-pair-" + row + "']//input[@placeholder='key']";
    return SeleniumDriver.getDriver().findElement(By.xpath(xpath));
  }

  public static WebElement locateValueProperty(int row) {
    String xpath = "//*[@data-cy= 'key-value-pair-" + row + "']//input[@placeholder='value']";
    return SeleniumDriver.getDriver().findElement(By.xpath(xpath));
  }

  public static WebElement locateAddRowButtonProperty(int row) {
    String xpath = "//*[@data-cy= 'key-value-pair-" + row + "']//span//button[@type='submit' and " +
        "@class='btn add-row-btn btn-link']";
    return SeleniumDriver.getDriver().findElement(By.xpath(xpath));
  }

  @FindBy(how = How.XPATH, using = "//a[contains(text(),\"Switch to\")]")
  public static WebElement switchToCreatedNamespace;

  @FindBy(how = How.XPATH, using = "//a[contains(text(),'Go to homepage')]")
  public static WebElement goToHomepage;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='wizard-result-icon-close-btn']")
  public static WebElement closeAddNameSpaceWindow;

  @FindBy(how = How.XPATH, using = "//span[@id='setpreferences-fd']")
  public static WebElement setPreferencesfromMainMenu;

  @FindBy(how = How.XPATH, using = "//button[@data-testid='save-prefs-btn']")
  public static WebElement clickSaveClose;

  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Upload Driver')]")
  public static WebElement uploadDriverTab;

  @FindBy(how = How.XPATH, using = "//*[contains(@class,'close')]")
  public static WebElement closeUploadDriverWindow;

  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Link Repository')]")
  public static WebElement linkRepositoryTab;

  @FindBy(how = How.XPATH, using = "//button[@class=\"MuiButtonBase-root MuiIconButton-root\"]")
  public static WebElement closeExternalPage;

  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Add Connection')]")
  public static WebElement addConnectionTab;

  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Import')]")
  public static WebElement importConnectionTab;

  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Edit')]")
  public static WebElement editPreferencesButton;

  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Create Profile')]")
  public static WebElement createProfileInNamespaceAdmin;

}

