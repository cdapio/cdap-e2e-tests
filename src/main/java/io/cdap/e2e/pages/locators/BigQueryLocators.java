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

import java.util.List;

/**
 * Represents BigQueryLocators
 */
public class BigQueryLocators {

  @FindBy(how = How.XPATH, using = "//*[@aria-label='Compose a new query']")
  public static WebElement composeNewQuery;

  @FindBy(how = How.XPATH, using = "//*[@class='inputarea']")
  public static List<WebElement> writeQueryBox;

  @FindBy(how = How.XPATH, using = "//*[contains(text(),' Run ')]")
  public static List<WebElement> runQuery;


  @FindBy(how = How.XPATH, using = "//*[@class='p6n-bq-number-cell']")
  public static WebElement countTable;
}
