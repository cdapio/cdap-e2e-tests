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
 * Represents CdfBigQueryPropertiesLocators
 */
public class CdfBigQueryPropertiesLocators {

  @FindBy(how = How.XPATH, using = "//input[@data-cy='referenceName']")
  public static WebElement bigQueryReferenceName;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='dataset']")
  public static WebElement bigQueryDataSet;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='project']")
  public static WebElement projectID;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='table']")
  public static WebElement bigQueryTable;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='switch-truncateTable']")
  public static WebElement truncatableSwitch;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='switch-allowSchemaRelaxation']")
  public static WebElement updateTable;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='plugin-properties-validate-btn']")
  public static WebElement validateButton;

  @FindBy(how = How.XPATH, using = "//a[@data-testid='close-config-popover']")
  public static WebElement closeButton;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='plugin-validation-success-msg']")
  public static WebElement textSuccess;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='cmekKey']")
  public static WebElement cmekKey;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='datasetProject']")
  public static WebElement datasetProjectID;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='dataset']")
  public static WebElement dataset;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='serviceFilePath']")
  public static WebElement serviceFilePath;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='partitionFrom']")
  public static WebElement partitionStartDate;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='partitionTo']")
  public static WebElement partitionEndDate;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='filter']")
  public static WebElement filter;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='bucket']")
  public static WebElement temporaryBucketName;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='switch-enableQueryingViews']")
  public static WebElement enableQueryingViews;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='viewMaterializationProject']")
  public static WebElement viewMaterializationProject;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='viewMaterializationDataset']")
  public static WebElement viewMaterializationDataset;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='get-schema-btn']//span[text()='Get Schema']")
  public static WebElement getSchemaButton;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='gcsChunkSize']")
  public static WebElement chunkSize;

  public static WebElement partitioningType(String option) {
    return SeleniumDriver.getDriver()
      .findElement(By.xpath("//*[@data-cy='partitioningType']//*[@value='" + option + "' and @type='radio']"));
  }

  @FindBy(how = How.XPATH, using = "//input[@data-cy='partitionByField']")
  public static WebElement partitionField;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='rangeStart']")
  public static WebElement rangeStart;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='rangeEnd']")
  public static WebElement rangeEnd;

  @FindBy(how = How.XPATH, using = "//input[@data-cy='rangeInterval']")
  public static WebElement rangeInterval;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='switch-partitionFilterRequired']")
  public static WebElement requirePartitionFilter;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='clusteringOrder']//*[@data-cy='key']/input")
  public static WebElement clusteringOrder;
}
