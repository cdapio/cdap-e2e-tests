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

  public static By clickOnDeleteButton() {
    return By.xpath("//button[@class='btn btn-primary'][//button[@data-cy='Delete']]");
  }

  public static By clickOnTruncateButton() {
    return By.xpath("//button[@class='btn btn-primary'][//button[@data-cy='Truncate']]");
  }

  @FindBy(how = How.XPATH, using = "//div[@class='empty-message-container']")
  public static WebElement pipelineDeletedMessage;

  @FindBy(how = How.XPATH, using = "//input[@class='search-input form-control'][@placeholder='Search']")
  public static WebElement searchTabControlCenter;

  @FindBy(how = How.XPATH, using = "//div[@class='just-added-entities-list']//button[@class='btn btn-link']" +
    "//*['icon-svg icon-wrench']")
  public static WebElement setPreferencesIcon;

  public static WebElement keyInputField() {
    return SeleniumDriver.getDriver().findElement(By.xpath("//input[@placeholder='key']"));
  }

  public static WebElement valueInputField() {
    return SeleniumDriver.getDriver().findElement(By.xpath("//input[@placeholder='value']"));
  }

  @FindBy(how = How.XPATH, using = "//button[@data-cy='save-prefs-btn']")
  public static WebElement clickSaveAndCloseButton;

  @FindBy(how = How.XPATH, using = "//div[@class='just-added-entities-list']" +
    "//*[@class='icon-svg icon-ETLBatch entity-icon']")
  public static WebElement clickOnDataPipelineTab;

  @FindBy(how = How.XPATH, using = "//i[@class='fa fa-trash']")
  public static WebElement clickOnDeleteIconSetPreferences;

  @FindBy(how = How.XPATH, using = "//div[@class='entity-card-header dataset']" +
    "//*[@class='icon-svg icon-datasets entity-icon']")
  public static WebElement clickOnDatasetEntityIcon;

  @FindBy(how = How.XPATH, using = "//div[@class='header']//*[@class='icon-svg icon-datasets']")
  public static WebElement clickOnDatasetEntityDetailsPage;

  @FindBy(how = How.XPATH, using = "(//*[@class='icon-svg icon-trash'])[1]")
  public static WebElement clickOnDatasetDeleteIcon;

  @FindBy(how = How.XPATH, using = "(//*[@class='icon-svg icon-cut'])[1]")
  public static WebElement clickOnTruncateDatasetEntity;

  @FindBy(how = How.XPATH, using = "//a[@class='link-to-detail']")
  public static WebElement clickOnViewDetailsTabDatasetEntity;

  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Dataset')]")
  public static WebElement navigateOnViewDetailsPageDatasetEntity;

  @FindBy(how = How.XPATH, using = "//a[contains(text(),'Back')]")
  public static WebElement clickOnBackTabDatasetEntity;

  @FindBy(how = How.XPATH, using = "//*[@class='icon-svg icon-close']")
  public static WebElement clickOnCloseTabDatasetEntity;

  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Displaying Datasets, Applications, sorted by Newest')]")
  public static WebElement clickOnDatasetApplicationsMessage;

  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Displaying all entities, sorted by Newest')]")
  public static WebElement clickOnDatasetApplicationsArtifactsMessage;

  public static By locateDropdownListItem(String option) {
    return By.xpath("//input[@data-cy='" + option + "']");
  }

  @FindBy(how = How.XPATH, using = "//a[contains(text(),'Schema')]")
  public static WebElement clickOnSchemaTabDatasetEntity;

  @FindBy(how = How.XPATH, using = "//a[contains(text(),'Programs')]")
  public static WebElement clickOnProgramsTabDatasetEntity;

  @FindBy(how = How.XPATH, using = "//div[@class='schema-tab']")
  public static WebElement clickOnSchemaTabDatasetEntityPage;

  @FindBy(how = How.XPATH, using = "//div[@class='program-tab clearfix']")
  public static WebElement clickOnProgramsTabDatasetEntityPage;

  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Displaying all entities, sorted by Newest')]")
  public static WebElement clickOnNewestOptionMessage;

  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Displaying all entities, sorted by Oldest')]")
  public static WebElement clickOnOldestOptionMessage;

  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Displaying all entities, sorted by Z-A')]")
  public static WebElement clickOnZToAOptionMessage;

  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Displaying all entities, sorted by A-Z')]")
  public static WebElement clickOnAToZOptionMessage;

  @FindBy(how = How.XPATH, using = "//div[@id='filter-tooltip-target-id']")
  public static WebElement clickOnSortDropdown;

  public static WebElement selectSortDropdownValue(String option) {
    return SeleniumDriver.getDriver().findElement(By.xpath("//*[contains(text(),'" + option + "')]"));
  }

  @FindBy(how = How.XPATH, using = "//span[@data-testid='tag-plus-button']")
  public static WebElement clickOnAddTagIconDatasetEntity;

  public static WebElement enterAddTagIconDatasetEntityValue() {
    return SeleniumDriver.getDriver().findElement(By.xpath("//input[@data-testid='tag-input']"));
  }

  @FindBy(how = How.XPATH, using = "//*[contains(text(),'Tags (1)')]")
  public static WebElement tagCountIconIncreaseDatasetEntity;

  @FindBy(how = How.XPATH, using = "//span[@class='tag-content']//*[@class='icon-svg icon-close']")
  public static WebElement clickOnCloseTagIconDatasetEntity;

  @FindBy(how = How.XPATH, using = "//i[normalize-space()='No tags found. Click to add a new business tag.']")
  public static WebElement tagCounDecreasetIconDatasetEntity;

  @FindBy(how = How.XPATH, using = "//*[contains(text(),'Search results for \"testingTag\", " +
    "filtered by Datasets, Applications')]")
  public static WebElement searchedTagDisplayedMessage;
}
