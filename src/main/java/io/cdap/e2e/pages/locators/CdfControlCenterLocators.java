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
    return By.xpath("//div//button[@data-cy='Truncate']");
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
  public static WebElement saveAndCloseButton;

  @FindBy(how = How.XPATH, using = "//div[@class='just-added-entities-list']" +
    "//*[@class='icon-svg icon-ETLBatch entity-icon']")
  public static WebElement dataPipelineTab;

  @FindBy(how = How.XPATH, using = "//i[@class='fa fa-trash']")
  public static WebElement deleteIconSetPreferences;

  @FindBy(how = How.XPATH, using = "//div[@class='entity-card-header dataset']" +
    "//*[@class='icon-svg icon-datasets entity-icon']")
  public static WebElement datasetEntityIcon;

  @FindBy(how = How.XPATH, using = "//div[@class='header']//*[@class='icon-svg icon-datasets']")
  public static WebElement datasetEntityDetailsPage;

  @FindBy(how = How.XPATH, using = "(//*[@class='icon-svg icon-trash'])[1]")
  public static WebElement datasetDeleteIcon;

  @FindBy(how = How.XPATH, using = "(//*[@class='icon-svg icon-cut'])[1]")
  public static WebElement truncateDatasetEntity;

  @FindBy(how = How.XPATH, using = "//a[@class='link-to-detail']")
  public static WebElement viewDetailsTabDatasetEntity;

  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Dataset')]")
  public static WebElement navigateOnViewDetailsPageDatasetEntity;

  @FindBy(how = How.XPATH, using = "//a[contains(text(),'Back')]")
  public static WebElement backTabDatasetEntity;

  @FindBy(how = How.XPATH, using = "//*[@class='icon-svg icon-close']")
  public static WebElement closeTabDatasetEntity;

  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Displaying all entities, sorted by Newest')]")
  public static WebElement datasetApplicationsArtifactsMessage;

  public static By locateDropdownListItem(String option) {
    return By.xpath("//input[@data-cy='" + option + "']");
  }

  @FindBy(how = How.XPATH, using = "//a[contains(text(),'Schema')]")
  public static WebElement schemaTabDatasetEntity;

  @FindBy(how = How.XPATH, using = "//a[contains(text(),'Programs')]")
  public static WebElement programsTabDatasetEntity;

  @FindBy(how = How.XPATH, using = "//div[@class='schema-tab']")
  public static WebElement schemaTabDatasetEntityPage;

  @FindBy(how = How.XPATH, using = "//div[@class='program-tab clearfix']")
  public static WebElement programsTabDatasetEntityPage;

  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Displaying Applications, Datasets, sorted by Newest')]")
  public static WebElement newestOptionMessage;

  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Displaying Applications, Datasets, sorted by Oldest')]")
  public static WebElement oldestOptionMessage;

  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Displaying Applications, Datasets, sorted by Z - A')]")
  public static WebElement zToAOptionMessage;

  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Displaying Applications, Datasets, sorted by A - Z')]")
  public static WebElement aToZOptionMessage;

  @FindBy(how = How.XPATH, using = "//div[@id='filter-tooltip-target-id']")
  public static WebElement sortDropdown;

  public static WebElement selectSortDropdownValue(String option) {
    return SeleniumDriver.getDriver().findElement(By.xpath("//*[contains(text(),'" + option + "')]"));
  }

  @FindBy(how = How.XPATH, using = "//span[@data-testid='tag-plus-button']")
  public static WebElement addTagIconDatasetEntity;

  public static WebElement enterAddTagIconDatasetEntityValue() {
    return SeleniumDriver.getDriver().findElement(By.xpath("//input[@data-testid='tag-input']"));
  }

  @FindBy(how = How.XPATH, using = "//*[contains(text(),'Tags (1)')]")
  public static WebElement tagCountIconIncreaseDatasetEntity;

  @FindBy(how = How.XPATH, using = "//span[@class='tag-content']//*[@class='icon-svg icon-close']")
  public static WebElement closeTagIconDatasetEntity;

  @FindBy(how = How.XPATH, using = "//i[normalize-space()='No tags found. Click to add a new business tag.']")
  public static WebElement tagCounDecreaseIconDatasetEntityMessage;

  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Search results for \"testingtag\", filtered by Appli')]")
  public static WebElement searchedTagShowsDisplayedMessage;
}
