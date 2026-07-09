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
package io.cdap.e2e.pages.actions;

import io.cdap.e2e.pages.locators.CdfControlCenterLocators;
import io.cdap.e2e.pages.locators.CdfPluginPropertiesLocators;
import io.cdap.e2e.utils.AssertionHelper;
import io.cdap.e2e.utils.ElementHelper;
import io.cdap.e2e.utils.PluginPropertyUtils;
import io.cdap.e2e.utils.SeleniumDriver;
import io.cdap.e2e.utils.SeleniumHelper;
import io.cdap.e2e.utils.WaitHelper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stepsdesign.PipelineSteps;

/**
 * Represents CdfControlCenterActions
 */

public class CdfControlCenterActions {

  private static final Logger logger = LoggerFactory.getLogger(CdfControlCenterActions.class);
  public static CdfControlCenterLocators cdfControlCenterLocators =
    SeleniumHelper.getPropertiesLocators(CdfControlCenterLocators.class);

  /**
   * Click on the Hamburger menu icon.
   */
  public static void clickOnHamburgerMenu() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.hamburgerMenu);
  }

  /**
   * Click on the Control Center Tab.
   */
  public static void clickOnControlCenterTab() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.controlCenterMenu);
  }

  /**
   * Navigate to control center page.
   */
  public static void navigateToControlCenter() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.pageHeaderControlCenter);
  }

  /**
   * Click on the Create Button.
   */
  public static void clickOnCreateButtonControlCenterPage() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.createButtonControlCenter);
  }

  /**
   * Check for the pipeline present in control center page.
   */
  public static void pipelinePresentControlCenterPage() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.dataPipelineControlCenter);
  }

  /**
   * Click on the delete button confirmation box.
   */
  public static void clickOnDeleteButtonOnConfirmationBox() {
    ElementHelper.clickIfDisplayed(CdfControlCenterLocators.clickOnDeleteButton());
  }

  /**
   * Click on the delete icon.
   */
  public static void deletePipelineControlCenter() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.deleteIconControlCenter);
    clickOnDeleteButtonOnConfirmationBox();
  }

  /**
   * Check for the pipeline deleted is not present.
   */
  public static void pipelineDeletedIsNotPresent() {
    clickOnSearchTabControlCenter(PipelineSteps.pipelineName);
    ElementHelper.isElementDisplayed(CdfControlCenterLocators.pipelineDeletedMessage);
  }

  /**
   * Click on the SearchTab Control Center.
   */
  public static void clickOnSearchTabControlCenter(String message) {
    ElementHelper.sendKeys(CdfControlCenterLocators.searchTabControlCenter, message);
  }

  /**
   * Click on the Set Preferences Icon.
   */
  public static void clickOnSetPreferencesIcon() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.setPreferencesIcon);
  }

  /**
   * Enter the key in the key input field.
   */
  public static void enterKeyInputValue(String value) {
    WebElement pluginPropertyInput = CdfControlCenterLocators.keyInputField();
    String valueFromPluginPropertiesFile = PluginPropertyUtils.pluginProp(value);
    if (valueFromPluginPropertiesFile == null) {
      ElementHelper.sendKeys(pluginPropertyInput, value);
      return;
    }
    ElementHelper.sendKeys(pluginPropertyInput, valueFromPluginPropertiesFile);
  }

  /**
   * Enter the value in the value input field.
   */
  public static void enterValueInput(String value) {
    WebElement pluginPropertyInput = CdfControlCenterLocators.valueInputField();
    String valueFromPluginPropertiesFile = PluginPropertyUtils.pluginProp(value);
    if (valueFromPluginPropertiesFile == null) {
      ElementHelper.sendKeys(pluginPropertyInput, value);
      return;
    }
    ElementHelper.sendKeys(pluginPropertyInput, valueFromPluginPropertiesFile);
  }

  /**
   * Click on the Save And Close Button.
   */
  public static void clickOnSaveAndCloseButton() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.saveAndCloseButton);
  }

  /**
   * Click on the DataPipeline.
   */
  public static void clickOnDataPipelineTab() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.dataPipelineTab);
  }

  /**
   * Click on the ViewDetails Tab of Dataset.
   */
  public static void clickOnDeleteIconSetPreferences() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.deleteIconSetPreferences);
  }

  /**
   * Click on the Dataset Entity.
   */
  public static void clickOnDatasetEntityIcon() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.datasetEntityIcon);
  }

  /**
   * Navigate to Details Page of Dataset.
   */
  public static void navigateDetailsPageDatasetEntity() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.datasetEntityDetailsPage);
  }

  /**
   * Click on the Delete Icon of Dataset.
   */
  public static void clickOnDeleteDatasetEntity() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.datasetDeleteIcon);
    clickOnDeleteButtonOnConfirmationBox();
  }

  /**
   * Click on the Truncate Button Confirmation Box.
   */
  public static void clickOnTruncateButtonOnConfirmationBox() {
    ElementHelper.clickIfDisplayed(CdfControlCenterLocators.clickOnTruncateButton());
  }

  /**
   * Click on the ViewDetails Tab of Dataset.
   */
  public static void clickOnTruncateDatasetEntity() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.truncateDatasetEntity);
    clickOnTruncateButtonOnConfirmationBox();
  }

  /**
   * Check for the deleted dataset not present in control center page.
   */
  public static void deletedDatasetNotPresent(String message) {
    ElementHelper.sendKeys(CdfControlCenterLocators.searchTabControlCenter, message);
    ElementHelper.isElementDisplayed(CdfControlCenterLocators.pipelineDeletedMessage);
  }

  /**
   * Click on the ViewDetails Tab of Dataset.
   */
  public static void clickOnViewDetailsPageDataset() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.viewDetailsTabDatasetEntity);
  }

  /**
   * Navigate on the ViewDetails Tab of Dataset.
   */
  public static void navigateToViewDetailsPageDataset() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.navigateOnViewDetailsPageDatasetEntity);
  }

  /**
   * Click on the Back Tab.
   */
  public static void clickOnBackTabDatasetEntity() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.backTabDatasetEntity);
  }

  /**
   * Click on the Close Tab.
   */
  public static void clickOnCloseTabDatasetEntity() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.closeTabDatasetEntity);
  }

  /**
   * Click on the Dataset Artifact Message Displayed.
   */
  public static void clickOnDatasetApplicationArtifactsMessage(String allEntitiesDisplayedMessage) {
    String allEntitiesDisplayedExpectedMessage = PluginPropertyUtils.pluginProp(allEntitiesDisplayedMessage);
    WaitHelper.waitForElementToBeDisplayed(CdfControlCenterLocators.filterOptionMessage);
    AssertionHelper.verifyElementContainsText(CdfControlCenterLocators.filterOptionMessage,
                                              allEntitiesDisplayedExpectedMessage);
    logger.info("Verifying that the element: " + CdfControlCenterLocators.filterOptionMessage + " contains text: "
                  + allEntitiesDisplayedExpectedMessage);
  }

  /**
   * Select type from filter in control center .
   *
   * @param option Artifacts Applications Datasets
   */
  public static void selectFilterDropdownOption(String pluginProperty, String option) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(pluginProperty);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = pluginProperty;
    }
    String optionFromPluginPropertiesFile = PluginPropertyUtils.pluginProp(option);
    if (optionFromPluginPropertiesFile == null) {
      ElementHelper.selectDropdownOption(CdfPluginPropertiesLocators
                                           .locatePropertyElement(pluginPropertyDataCyAttribute),
                                         CdfControlCenterLocators.locateDropdownListItem(option));
    } else {
      ElementHelper.selectDropdownOption(CdfPluginPropertiesLocators.locatePropertyElement
                                           (pluginPropertyDataCyAttribute),
                                         CdfControlCenterLocators.locateDropdownListItem(
                                           optionFromPluginPropertiesFile));
    }
  }

  /**
   * Click on the Schema Tab.
   */
  public static void clickOnSchemaTabDataset() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.schemaTabDatasetEntity);
  }

  /**
   * Check for the displayed Schema page.
   */
  public static void navigateToSchemaTabPage() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.schemaTabDatasetEntityPage);
  }

  /**
   * Click on the Programs Tab.
   */
  public static void clickOnProgramsTabDataset() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.programsTabDatasetEntity);
  }

  /**
   * Check for the displayed programs page.
   */
  public static void navigateToProgramsTabPage() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.programsTabDatasetEntityPage);
  }

  /**
   * Check for the displayed message when sorted by Newest.
   */
  public static void clickOnNewestOptionMessage(String newFilterMessage) {
    String newestOptionExpectedMessage = PluginPropertyUtils.pluginProp(newFilterMessage);
    WaitHelper.waitForElementToBeDisplayed(CdfControlCenterLocators.filterOptionMessage);
    AssertionHelper.verifyElementContainsText(CdfControlCenterLocators.filterOptionMessage,
                                              newestOptionExpectedMessage);
    logger.info("Verifying that the element: " + CdfControlCenterLocators.filterOptionMessage + " contains text: "
                  + newestOptionExpectedMessage);
  }

  /**
   * Check for the displayed message when sorted by Oldest.
   */
  public static void clickOnOldestOptionMessage(String oldestFilterMessage) {
    String oldestOptionExpectedMessage = PluginPropertyUtils.pluginProp(oldestFilterMessage);
    WaitHelper.waitForElementToBeDisplayed(CdfControlCenterLocators.filterOptionMessage);
    AssertionHelper.verifyElementContainsText(CdfControlCenterLocators.filterOptionMessage,
                                              oldestOptionExpectedMessage);
    logger.info("Verifying that the element: " + CdfControlCenterLocators.filterOptionMessage + " contains text: "
                  + oldestOptionExpectedMessage);
  }

  /**
   * Check for the displayed message when sorted by Z To A.
   */
  public static void clickOnZToAOptionMessage(String zToAFilterMessage) {
    String zToAOptionExpectedMessage = PluginPropertyUtils.pluginProp(zToAFilterMessage);
    WaitHelper.waitForElementToBeDisplayed(CdfControlCenterLocators.filterOptionMessage);
    AssertionHelper.verifyElementContainsText(CdfControlCenterLocators.filterOptionMessage,
                                              zToAOptionExpectedMessage);
    logger.info("Verifying that the element: " + CdfControlCenterLocators.filterOptionMessage + " contains text: "
                  + zToAOptionExpectedMessage);
  }

  /**
   * Check for the displayed message when sorted by A To Z.
   */
  public static void clickOnAToZOptionMessage(String aToZFilterMessage) {
    String aToZOptionExpectedMessage = PluginPropertyUtils.pluginProp(aToZFilterMessage);
    WaitHelper.waitForElementToBeDisplayed(CdfControlCenterLocators.filterOptionMessage);
    AssertionHelper.verifyElementContainsText(CdfControlCenterLocators.filterOptionMessage,
                                              aToZOptionExpectedMessage);
    logger.info("Verifying that the element: " + CdfControlCenterLocators.filterOptionMessage + " contains text: "
                  + aToZOptionExpectedMessage);
  }

  /**
   * Click on the sort by dropdown.
   */
  public static void selectSortDropdown() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.sortDropdown);
  }

  /**
   * Select the dropdown with value.
   */
  public static void selectSortDropdownOptionValue(String option) {
    ElementHelper.clickOnElement(CdfControlCenterLocators.selectSortDropdownValue(option));
  }

  /**
   * Click on the plus add button to add a tag.
   */
  public static void clickOnAddTagIcon() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.addTagIconDatasetEntity);
  }

  /**
   * Enter the value of the tag.
   */
  public static void enterValueTagInputField(String value) {
    WebElement pluginPropertyInput = CdfControlCenterLocators.enterAddTagIconDatasetEntityValue();
    String valueFromPluginPropertiesFile = PluginPropertyUtils.pluginProp(value);
    if (valueFromPluginPropertiesFile == null) {
      ElementHelper.sendKeys(pluginPropertyInput, value);
      return;
    }
    ElementHelper.sendKeys(pluginPropertyInput, valueFromPluginPropertiesFile);
  }

  /**
   * Check for the count when user adds the tag.
   */
  public static void tagCountIncreasesDataset() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.tagCountIconIncreaseDatasetEntity);
  }

  /**
   * Click on close tab of tags in Dataset Entity Details Page.
   */
  public static void clickOnCloseIcon() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.closeTagIconDatasetEntity);
  }

  /**
   * Check for the count when user removes the tag.
   */
  public static void tagCountDecreasesDataset(String tagCountDecreaseMessage) {
    String tagCountDecreaseExpectedMessage = PluginPropertyUtils.pluginProp(tagCountDecreaseMessage);
    WaitHelper.waitForElementToBeDisplayed(CdfControlCenterLocators.tagCounDecreaseIconDatasetEntityMessage);
    AssertionHelper.verifyElementContainsText(CdfControlCenterLocators.tagCounDecreaseIconDatasetEntityMessage,
                                              tagCountDecreaseExpectedMessage);
    logger.info("Verifying that the element: " + CdfControlCenterLocators.tagCounDecreaseIconDatasetEntityMessage
                  + " contains text: " + tagCountDecreaseExpectedMessage);
  }

  /**
   * Click on Search tab in control center.
   */
  public static void enterTextInSearchBarInControlCenter(String searchedText) {
    ElementHelper.sendKeys(CdfControlCenterLocators.searchTabControlCenter, searchedText);
    ElementHelper.clickOnElement(CdfControlCenterLocators.searchTabControlCenter);
  }

  /**
   * Search for the added tag in control center.
   */
  public static void searchedTagDisplayedMessage(String searchedTagDisplayedMessage) {
    String searchedTagDisplayedExpectedMessage = PluginPropertyUtils.pluginProp(searchedTagDisplayedMessage);
    WaitHelper.waitForElementToBeDisplayed(CdfControlCenterLocators.filterOptionMessage);
    AssertionHelper.verifyElementContainsText(CdfControlCenterLocators.filterOptionMessage,
                                              searchedTagDisplayedExpectedMessage);
    logger.info("Verifying that the element: " + CdfControlCenterLocators.filterOptionMessage + " contains text: "
                  + searchedTagDisplayedExpectedMessage);
  }

  /**
   * Press Enter Key.
   */
  public static void pressEnterKey() {
    logger.info("Press Enter Key");
    Actions act = new Actions(SeleniumDriver.getDriver());
    act.sendKeys(Keys.ENTER).perform();
  }
}
