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
 * Represents CdfGCSLocators
 */
public class CdfGCSLocators {

  @FindBy(how = How.XPATH, using = "//*[@placeholder='Name used to identify this source for lineage']")
  public static WebElement referenceName;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='project' and @class='MuiInputBase-input']")
  public static WebElement projectID;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='path' and @class='MuiInputBase-input']")
  public static WebElement gcsPath;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='sampleSize' and @class='MuiInputBase-input']")
  public static WebElement samplesize;

  @FindBy(how = How.XPATH, using = "//*[contains(text(),'Select one')]")
  public static WebElement format;

  @FindBy(how = How.XPATH, using = "//*[@class='fa fa-remove']")
  public static WebElement closeButton;

  @FindBy(how = How.XPATH, using = "//*[@title=\"GCS\"]//following-sibling::div")
  public static WebElement gcsProperties;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='switch-skipHeader']")
  public static WebElement skipHeader;

  @FindBy(how = How.XPATH, using = "//*[contains(text(),'Get Schema')]")
  public static WebElement getSchemaButton;


}
