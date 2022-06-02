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
}
