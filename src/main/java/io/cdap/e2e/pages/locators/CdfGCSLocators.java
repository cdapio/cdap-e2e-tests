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
 * Represents CdfGCSLocators
 */
public class CdfGCSLocators {

  @FindBy(how = How.XPATH, using = "//*[@data-cy='referenceName' and @class='MuiInputBase-input']")
  public static WebElement referenceName;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='serviceFilePath' and @class='MuiInputBase-input']")
  public static WebElement filePath;

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

  @FindBy(how = How.XPATH, using = "//*[@data-cy`='delimiter' and @class='MuiInputBase-input']")
  public static WebElement delimiter;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='plugin-properties-validate-btn']")
  public static WebElement validateBtn;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='plugin-properties-validate-btn']")
  public static WebElement successMessage;

  @FindBy(how = How.XPATH, using = "//*[@placeholder='Field name']")
  public static List<WebElement> schemaSection;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='minSplitSize'and @class='MuiInputBase-input']")
  public static WebElement minSplitSize;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='maxSplitSize'and @class='MuiInputBase-input']")
  public static WebElement maxSplitSize;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='fileRegex'and @class='MuiInputBase-input']")
  public static WebElement regexPath;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='pathField'and @class='MuiInputBase-input']")
  public static WebElement pathField;

  @FindBy(how = How.XPATH, using = "//*[@class=\"MuiInputBase-root MuiInputBase-fullWidth\" " +
    "and @data-cy=\"select-fileEncoding\"]")
  public static WebElement fileEncoding;

  @FindBy(how = How.XPATH, using = "//*[@placeholder=\"Field Name\"]")
  public static WebElement override;

  @FindBy(how = How.XPATH, using = "//*[@data-cy=\"value\"]")
  public static WebElement overrideDataType;

  @FindBy(how = How.XPATH, using = "//*[@placeholder='Field name' and @value='offset']")
  public static WebElement schemaTable;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='get-schema-btn']//span[text()='Get Schema']")
  public static WebElement getSchemaLoadComplete;

  @FindBy(how = How.XPATH,
    using = "//*[@data-cy='GCSFile-preview-data-btn' and @class='node-preview-data-btn ng-scope']")
  public static WebElement gcsPreviewData;

  @FindBy(how = How.XPATH, using = "//*[@data-cy=\"delimiter\" and @class=\"MuiInputBase-input\"]")
  public static WebElement delimiterField;
}
