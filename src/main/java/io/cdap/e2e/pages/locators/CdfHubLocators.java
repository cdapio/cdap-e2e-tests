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
 * CDF hub related locators.
 */
public class CdfHubLocators {

  @FindBy(how = How.XPATH, using = "//input[@placeholder='Search by name']")
  public static WebElement searchTabHub;
  @FindBy(how = How.XPATH, using = "//*[@data-cy='hub-close-btn']")
  public static WebElement closeButton;
  @FindBy(how = How.XPATH, using = "//button[@data-cy='one_step_deploy_plugin-btn']")
  public static WebElement deployButton;
  @FindBy(how = How.XPATH, using = "//button[@data-cy='create_pipeline_draft-btn']")
  public static WebElement createButton;
  @FindBy(how = How.XPATH, using = "//button[@data-cy='wizard-finish-btn']")
  public static WebElement finishButton;
  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Hub')]")
  public static WebElement locateHub;
  @FindBy(how = How.XPATH, using = "//button[@data-cy='Delete']")
  public static WebElement deleteButton;
  @FindBy(how = How.XPATH, using = "//button[@id='navbar-hub']")
  public static WebElement hubPage;
  @FindBy(how = How.XPATH, using = "//*[contains(text(),'No entities found in namespace \"default\"')]")
  public static WebElement deletedElementStatus;
  @FindBy(how = How.XPATH, using = "//div[@class='entities-all-list-container']//button[@class='btn btn-link']" +
    "//*[@class='icon-svg icon-trash']")
  public static WebElement deleteIconControlCenter;

  public static WebElement locateArtifact(String option) {
    return SeleniumDriver.getDriver()
      .findElement(By.xpath("//*[contains(text(),'" + option + "')]"));
  }

  public static WebElement locateButton(String buttonName) {
    return SeleniumDriver.getDriver()
      .findElement(By.xpath("//*[contains(text(),'" + buttonName + "')]"));
  }

  public static WebElement locateOptions(String featureName) {
    return SeleniumDriver.getDriver().findElement(By.xpath("//span[@title='" + featureName + "']"));
  }

  public static WebElement locateHamburgerOption(String option) {
    return SeleniumDriver.getDriver().findElement(By.xpath("//*[@data-cy='" + option + "']"));
  }

  public static WebElement locateErrorMessage(String errorMessage) {
    return SeleniumDriver.getDriver().findElement(By.xpath("//*[contains(text(),'" + errorMessage + "')]"));
  }
}
