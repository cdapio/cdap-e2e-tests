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
import io.cdap.e2e.utils.ElementHelper;
import io.cdap.e2e.utils.PluginPropertyUtils;
import io.cdap.e2e.utils.SeleniumHelper;
import io.cdap.e2e.utils.WaitHelper;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stepsdesign.PipelineSteps;

/**
 * Represents CdfControlCenterActions
 */

public class CdfControlCenterActions {

  private static final Logger logger = LoggerFactory.getLogger(CdfControlCenterActions.class);
  public static CdfControlCenterLocators cdfNameSpaceAdminLocators;

  static {
    cdfNameSpaceAdminLocators = SeleniumHelper.getPropertiesLocators(
      CdfControlCenterLocators.class);
  }

  public static void clickOnHamburgerMenu() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.hamburgerMenu);
  }

  public static void clickOnControlCenterTab() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.controlCenterMenu);
  }

  public static void navigateToControlCenter() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.pageHeaderControlCenter);
  }

  public static void clickOnCreateButtonControlCenterPage() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.createButtonControlCenter);
  }

  public static void pipelinePresentControlCenterPage() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.dataPipelineControlCenter);
  }

  public static void clickOnDeleteButtonOnConfirmationBox() {
    ElementHelper.clickIfDisplayed(CdfControlCenterLocators.clickOnDeleteButton());
  }

  public static void deletePipelineControlCenter() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.deleteIconControlCenter);
    clickOnDeleteButtonOnConfirmationBox();
  }

  public static void pipelineDeletedIsNotPresent() {
    clickOnSearchTabControlCenter(PipelineSteps.pipelineName);
    ElementHelper.isElementDisplayed(CdfControlCenterLocators.pipelineDeletedMessage);
  }

  public static void clickOnSearchTabControlCenter(String message) {
    ElementHelper.sendKeys(CdfControlCenterLocators.searchTabControlCenter, message);
  }

  public static void clickOnSetPreferencesIcon() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.setPreferencesIcon);
  }

  public static void enterKeyInputValue(String value) {
    WebElement pluginPropertyInput = CdfControlCenterLocators.keyInputField();
    String valueFromPluginPropertiesFile = PluginPropertyUtils.pluginProp(value);
    if (valueFromPluginPropertiesFile == null) {
      ElementHelper.sendKeys(pluginPropertyInput, value);
      return;
    }
    ElementHelper.sendKeys(pluginPropertyInput, valueFromPluginPropertiesFile);
  }

  public static void enterValueInput(String value) {
    WebElement pluginPropertyInput = CdfControlCenterLocators.valueInputField();
    String valueFromPluginPropertiesFile = PluginPropertyUtils.pluginProp(value);
    if (valueFromPluginPropertiesFile == null) {
      ElementHelper.sendKeys(pluginPropertyInput, value);
      return;
    }
    ElementHelper.sendKeys(pluginPropertyInput, valueFromPluginPropertiesFile);
  }

  public static void clickOnSaveAndCloseButton() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.clickSaveAndCloseButton);
  }

  public static void clickOnDataPipelineTab() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.clickOnDataPipelineTab);
  }

  public static void clickOnDeleteIconSetPreferences() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.clickOnDeleteIconSetPreferences);
  }

  public static void clickOnDatasetEntityIcon() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.clickOnDatasetEntityIcon);
  }

  public static void navigateDetailsPageDatasetEntity() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.clickOnDatasetEntityDetailsPage);
  }

  public static void clickOnDeleteDatasetEntity() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.clickOnDatasetDeleteIcon);
    clickOnDeleteButtonOnConfirmationBox();
  }

  public static void clickOnTruncateButtonOnConfirmationBox() {
    ElementHelper.clickIfDisplayed(CdfControlCenterLocators.clickOnTruncateButton());
  }

  public static void clickOnTruncateDatasetEntity() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.clickOnTruncateDatasetEntity);
    clickOnTruncateButtonOnConfirmationBox();
  }

  public static void deletedDatasetNotPresent(String message) {
    ElementHelper.sendKeys(CdfControlCenterLocators.searchTabControlCenter, message);
    ElementHelper.isElementDisplayed(CdfControlCenterLocators.pipelineDeletedMessage);
  }

  public static void clickOnViewDetailsPageDataset() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.clickOnViewDetailsTabDatasetEntity);
  }

  public static void navigateToViewDetailsPageDataset() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.navigateOnViewDetailsPageDatasetEntity);
  }

  public static void clickOnBackTabDatasetEntity() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.clickOnBackTabDatasetEntity);
  }

  public static void clickOnCloseTabDatasetEntity() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.clickOnCloseTabDatasetEntity);
  }

  public static void clickOnDatasetApplicationMessage() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.clickOnDatasetApplicationsMessage);
  }

  public static void clickOnDatasetApplicationArtifactsMessage() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.clickOnDatasetApplicationsArtifactsMessage);
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
    }
    ElementHelper.selectDropdownOption(CdfPluginPropertiesLocators.locatePropertyElement(pluginPropertyDataCyAttribute),
                                       CdfControlCenterLocators.locateDropdownListItem(
                                         optionFromPluginPropertiesFile));
  }

  /**
   * Clicks on the Schema Tab.
   */
  public static void clickOnSchemaTabDataset() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.clickOnSchemaTabDatasetEntity);
  }

  /**
   * Checks for the displayed Schema page.
   */
  public static void navigateToSchemaTabPage() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.clickOnSchemaTabDatasetEntityPage);
  }

  /**
   * Clicks on the Programs Tab.
   */
  public static void clickOnProgramsTabDataset() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.clickOnProgramsTabDatasetEntity);
  }

  /**
   * Checks for the displayed programs page.
   */
  public static void navigateToProgramsTabPage() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.clickOnProgramsTabDatasetEntityPage);
  }

  /**
   * Checks for the displayed message when sorted by Newest.
   */
  public static void clickOnNewestOptionMessage() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.clickOnNewestOptionMessage);
  }

  /**
   * Checks for the displayed message when sorted by Oldest.
   */
  public static void clickOnOldestOptionMessage() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.clickOnOldestOptionMessage);
  }

  /**
   * Checks for the displayed message when sorted by Z To A.
   */
  public static void clickOnZToAOptionMessage() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.clickOnZToAOptionMessage);
  }

  /**
   * Checks for the displayed message when sorted by A To Z.
   */
  public static void clickOnAToZOptionMessage() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.clickOnAToZOptionMessage);
  }

  /**
   * Click on the sort by dropdown.
   */
  public static void selectSortDropdown() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.clickOnSortDropdown);
  }

  /**
   * Select the dropdown with value.
   */
  public static void selectSortDropdownOptionValue(String option) {
    ElementHelper.clickOnElement(CdfControlCenterLocators.selectSortDropdownValue(option));
  }

  /**
   * Clicks on the plus add button to add a tag.
   */
  public static void clickOnAddTagIcon() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.clickOnAddTagIconDatasetEntity);
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
    ElementHelper.clickOnElement(CdfControlCenterLocators.enterAddTagIconDatasetEntityValue());
  }

  /**
   * Checks for the count when user adds the tag.
   */
  public static void tagCountIncreasesDataset() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.tagCountIconIncreaseDatasetEntity);
  }

  /**
   * Click on close tab of tags in Dataset Entity Details Page.
   */
  public static void clickOnCloseIcon() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.clickOnCloseTagIconDatasetEntity);
  }

  /**
   * Checks for the count when user removes the tag.
   */
  public static void tagCountDecreasesDataset() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.tagCounDecreasetIconDatasetEntity);
  }

  /**
   * Click on Search tab in control center.
   */
  public static void clickOnSearchTagControlCenter(String searchedText) {
    ElementHelper.sendKeys(CdfControlCenterLocators.searchTabControlCenter, searchedText);
  }

  /**
   * Searches for the added tag in control center.
   */
  public static void searchedTagDisplayedMessage() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.searchedTagDisplayedMessage); }
  }
