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

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Represents CDF locators related to connection pages and properties
 */
public class CdfConnectionLocators {

  @FindBy(how = How.XPATH, using = "//*[@data-cy='connection']//button/span[text()='Browse Connections']")
  public static WebElement browseConnectionsButton;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='loading-indicator']")
  public static WebElement connectionDataLoadingIndicator;

  public static By locatorOfConnectionDataLoadingIndicator = By.xpath("//*[@data-cy='loading-indicator']");

  @FindBy(how = How.XPATH, using = "//*[@data-cy='connection-browser-search']//input")
  public static WebElement searchDirectoryInput;

  public static By locatorOfDataTableTitle(String title) {
    return By.xpath("//div[@title='" + title + "' and text()='" + title + "']");
  }

  @FindBy(how = How.XPATH, using = "//table[@id='dataprep-table']")
  public static WebElement dataTable;

  @FindBy(how = How.XPATH, using = "//button[text()='Create a Pipeline']")
  public static WebElement createPipelineButton;

  public static By locatorOfPipelineTypeButton(String pipelineType) {
    return By.xpath("//a//*[text()='" + pipelineType + "']");
  }
}
