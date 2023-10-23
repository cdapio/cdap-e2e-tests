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

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Represents CdfControlCenterLocators
 */

public class CdfControlCenterLocators {

  @FindBy(how = How.XPATH, using = "//*[@data-cy='navbar-hamburger-icon']")
  public static WebElement hamburgerMenu;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='navbar-control-center-link']")
  public static WebElement controlCenterMenu;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='feature-heading'][//div[contains(text(),'Control Center')]]")
  public static WebElement pageHeaderControlCenter;

  @FindBy(how = How.XPATH, using = "//*[@id='create-pipeline-link']")
  public static WebElement createButtonControlCenter;

  @FindBy(how = How.XPATH, using = "//span[@class='entity-type'][//span[contains(text(),'Data Pipeline')]]")
  public static WebElement dataPipelineControlCenter;

  @FindBy(how = How.XPATH, using = "//div[@class='just-added-entities-list']//button[@class='btn btn-link']" +
    "//*[@class='icon-svg icon-trash']")
  public static WebElement deleteIconControlCenter;

  public static By clickOnDeleteButton()  {
    return By.xpath("//button[@class='btn btn-primary'][//button[@data-cy='Delete']]");
  }

  @FindBy(how = How.XPATH, using = "//div[@class='empty-message-container']")
  public static WebElement pipelineDeletedMessage;

  @FindBy(how = How.XPATH, using = "//input[@class='search-input form-control'][@placeholder='Search']")
  public static WebElement searchTabControlCenter;
}
