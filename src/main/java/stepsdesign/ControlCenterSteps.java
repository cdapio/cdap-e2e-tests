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

package stepsdesign;

import io.cdap.e2e.pages.actions.CdfControlCenterActions;
import io.cdap.e2e.utils.CdfHelper;
import io.cucumber.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents CdfControlCenterSteps
 */
public class ControlCenterSteps implements CdfHelper {

  private static final Logger logger = LoggerFactory.getLogger(ControlCenterSteps.class);

  @Then("Click on the Hamburger bar on the left panel")
  public static void clickOnTheHamburgerIcon() {
    CdfControlCenterActions.clickOnHamburgerMenu();
  }

  @Then("Click on Control Center link from the hamburger menu")
  public void openControlCenterPage() {
    CdfControlCenterActions.clickOnControlCenterTab();
  }

  @Then("Verify that the user is navigated to control center page successfully")
  public void navigateControlCenterPage() {
    CdfControlCenterActions.navigateToControlCenter();
  }

  @Then("Verify user is able to click on the create button to create a pipeline successfully")
  public void createButtonControlCenterPage() {
    CdfControlCenterActions.clickOnCreateButtonControlCenterPage();
  }

  @Then("Verify the pipeline created successfully is present in control center page")
  public void pipelinePresentControlCenterPage() {
    CdfControlCenterActions.pipelinePresentControlCenterPage();
  }

  @Then("Click on the delete icon of the created pipeline and pipeline should get deleted successfully")
  public void deleteControlCenterPipeline() {
    CdfControlCenterActions.deletePipelineControlCenter();
  }

  @Then("Verify the deleted pipeline is not present in the control center page")
  public void pipelineDeletedIsNotPresent() {
    CdfControlCenterActions.pipelineDeletedIsNotPresent();
  }

  @Then("Verify the user is able to set the preferences for the created pipeline in the control center page")
  public void setPreferencesForPipeline() {
    CdfControlCenterActions.clickOnSetPreferencesIcon(); }

  @Then("Verify the user is able to enter the value in the key input field {string}")
  public void setPreferencesKeyInputValue(String value) {
    CdfControlCenterActions.enterKeyInputValue(value); }

  @Then("Verify the user is able to enter the value of the key in the value input field {string}")
  public void setPreferencesValueInput(String value) {
    CdfControlCenterActions.enterValueInput(value);
  }

  @Then("Verify user is able to click on save and close button of set preferences")
  public void clickSaveAndCloseButton() {
    CdfControlCenterActions.clickOnSaveAndCloseButton(); }

  @Then("Verify user is able to click on the data pipeline added in the control center page")
  public void clickOnDataPipelineTab() {
    CdfControlCenterActions.clickOnDataPipelineTab(); }

  @Then("Verify user is able to click on the delete icon of preferences to delete the added preferences successfully")
  public void clickOnDeleteIconSetPreferences() {
    CdfControlCenterActions.clickOnDeleteIconSetPreferences(); }

  @Then("Verify user is able to click on the dataset entity icon to see the details page of the dataset")
  public void clickOnDatasetEntityIcon() {
    CdfControlCenterActions.clickOnDatasetEntityIcon(); }

  @Then("Verify user is navigated to the details page of the dataset entity icon successfully")
  public void navigateDetailsPageDatasetEntity() {

    CdfControlCenterActions.navigateDetailsPageDatasetEntity();
  }

  @Then("Click on the delete icon of the dataset entity and the dataset should get deleted successfully")
  public void deleteDatasetEntity() {
    CdfControlCenterActions.clickOnDeleteDatasetEntity(); }

  @Then("Verify the deleted dataset {string} entity is not present in control center page")
  public void deletedDatasetIsNotPresent(String value) {
    CdfControlCenterActions.deletedDatasetNotPresent(value);
  }

  @Then("Click on the truncate icon of the dataset entity and the dataset should get truncated successfully")
  public void truncateDatasetEntity() {
    CdfControlCenterActions.clickOnTruncateDatasetEntity(); }

  @Then("Click on the view details tab of dataset entity page to see the details page of the dataset")
  public void clickOnViewDetailsPageDataset() {
    CdfControlCenterActions.clickOnViewDetailsPageDataset(); }

  @Then("Verify user is successfully navigated to details page of the dataset entity")
  public void navigatedToViewDetailsPageDataset() {
    CdfControlCenterActions.navigateToViewDetailsPageDataset(); }

  @Then("Click on the back link of the view details page of dataset entity")
  public void clickBackTabDatasetEntity() {
    CdfControlCenterActions.clickOnBackTabDatasetEntity(); }

  @Then("Click on the close link of the view details page of dataset entity to close the details page")
  public void clickCloseTabDatasetEntity() {
    CdfControlCenterActions.clickOnCloseTabDatasetEntity(); }

  @Then("Verify user is able to see the display message on the basis of filter selection in control center page")
  public void displayFilterDatasetApplicationMessage() {
    CdfControlCenterActions.clickOnDatasetApplicationMessage(); }

  @Then("Select dropdown : {string} with option value: {string} in control center")
  public void selectDropdownFilterOptionValue(String pluginProperty, String option) {
    CdfControlCenterActions.selectFilterDropdownOption(pluginProperty, option); }

  @Then("Verify user is able to see all entities displayed message on the basis of filter selection")
  public void displayFilterDatasetApplicationArtifactsMessage() {
    CdfControlCenterActions.clickOnDatasetApplicationArtifactsMessage(); }

  @Then("Click on the schema link of the dataset entity details page")
  public void clickOnSchemaTabLink() {
    CdfControlCenterActions.clickOnSchemaTabDataset(); }

  @Then("Verify user is navigated to the schema details page of the dataset entity page")
  public void navigateToSchemaTabLinkPage() {
    CdfControlCenterActions.navigateToSchemaTabPage(); }

  @Then("Click on the programs link of the dataset entity details page")
  public void clickOnProgramsTabLink() {
    CdfControlCenterActions.clickOnProgramsTabDataset(); }

  @Then("Verify user is navigated to the programs details page of the dataset entity page")
  public void navigateToProgramsTabLinkPage() {
    CdfControlCenterActions.navigateToProgramsTabPage(); }

  @Then("Select the sort by dropdown with option value: {string}")
  public void selectDropdownSortOptionValue(String option) {
    CdfControlCenterActions.selectSortDropdown();
    CdfControlCenterActions.selectSortDropdownOptionValue(option); }

  @Then("Verify user is able to see the entities sorted by the newest option")
  public void clickOnNewestOptionMessage() {
    CdfControlCenterActions.clickOnNewestOptionMessage(); }

  @Then("Verify user is able to see the entities sorted by the oldest option")
  public void clickOnOldestOptionMessage() {
    CdfControlCenterActions.clickOnOldestOptionMessage(); }

  @Then("Verify user is able to see the entities sorted by the Z to A option")
  public void clickOnZToAOptionMessage() {
    CdfControlCenterActions.clickOnZToAOptionMessage(); }

  @Then("Verify user is able to see the entities sorted by the A to Z option")
  public void clickOnAToZOptionMessage() {
    CdfControlCenterActions.clickOnAToZOptionMessage(); }

  @Then("Click on the plus add button of the dataset entity to add the tags successfully")
  public void clickOnAddTagIcon() {
    CdfControlCenterActions.clickOnAddTagIcon(); }

  @Then("Verify user is able to enter the values in tag input field: {string}")
  public void enterValueInTagInputField(String value) {
    CdfControlCenterActions.enterValueTagInputField(value);
  }

  @Then("Verify that the tag count of dataset entity increases when the user adds the tag")
  public void tagCountIncreasesDataset() {
    CdfControlCenterActions.tagCountIncreasesDataset(); }

  @Then("Click on the close icon of the added tag and verify that the tag count decreases")
  public void clickOnCloseIconAndTagCountDecreases() {
    CdfControlCenterActions.clickOnCloseIcon(); {
      CdfControlCenterActions.tagCountDecreasesDataset(); }
  }

  @Then("Enter the text in search tab {string} in control center")
  public static void openSearchControlCenter(String value) {
    CdfControlCenterActions.clickOnSearchTagControlCenter(value);
  }

  @Then("Verify the searched tag appears successfully on the control center page")
  public static void searchedTagDisplayedMessage() {
    CdfControlCenterActions.searchedTagDisplayedMessage(); }
   }

