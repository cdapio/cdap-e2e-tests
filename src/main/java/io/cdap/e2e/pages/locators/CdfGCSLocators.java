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
 * Represents CdfGCSLocators
 */
public class CdfGCSLocators {

  @FindBy(how = How.XPATH, using = "//input[@data-cy='referenceName']")
  public static WebElement referenceName;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='serviceFilePath']")
  public static WebElement filePath;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='project']")
  public static WebElement projectID;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='path']")
  public static WebElement gcsPath;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='sampleSize']")
  public static WebElement samplesize;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='select-format']")
  public static WebElement format;

  @FindBy(how = How.XPATH, using = "//a[@data-testid='close-config-popover']")
  public static WebElement closeButton;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='switch-skipHeader']")
  public static WebElement skipHeader;

  @FindBy(how = How.XPATH, using = "//button[@data-cy='get-schema-btn']/span[text()='Get Schema']")
  public static WebElement getSchemaButton;

  @FindBy(how = How.XPATH, using = "//input[@data-cy`='delimiter']")
  public static WebElement delimiter;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='plugin-properties-validate-btn']")
  public static WebElement validateBtn;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='minSplitSize']")
  public static WebElement minSplitSize;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='maxSplitSize']")
  public static WebElement maxSplitSize;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='fileRegex']")
  public static WebElement regexPath;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='pathField']")
  public static WebElement pathField;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='select-fileEncoding']")
  public static WebElement fileEncoding;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='override']//*[@data-cy='key']/input")
  public static WebElement override;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='override']//*[@data-cy='value']")
  public static WebElement overrideDataType;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='get-schema-btn']//span[text()='Get Schema']")
  public static WebElement getSchemaLoadComplete;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='delimiter']")
  public static WebElement delimiterField;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='suffix']")
  public static WebElement pathSuffix;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='switch-writeHeader']")
  public static WebElement writeHeaderSwitch;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='location']")
  public static WebElement location;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='select-contentType']")
  public static WebElement contentType;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='cmekKey']")
  public static WebElement cmekKey;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='outputFileNameBase']")
  public static WebElement outputFilePrefix;

  @FindBy(how = How.XPATH, using = "//textarea[@data-cy='fileSystemProperties']")
  public static WebElement fileSystemProperties;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='fileSystemProperties']//button/span[text()='Tidy']")
  public static WebElement fileSystemPropertiesTidyButton;

  public static WebElement pathFilenameOnly(String value) {
    return SeleniumDriver.getDriver().findElement(By.xpath("//input[@name='filenameOnly' and @value='" + value + "']"));
  }

  public static WebElement recursive(String value) {
    return SeleniumDriver.getDriver().findElement(By.xpath("//input[@name='recursive' and @value='" + value + "']"));
  }

  //TODO: Move to CdfPluginPropertiesLocators.java once PR#76 merged
  public static WebElement getDropdownListItem(String option) {
    return SeleniumDriver.getDriver().findElement(By.xpath("//li[@data-value='" + option + "']"));
  }
}
