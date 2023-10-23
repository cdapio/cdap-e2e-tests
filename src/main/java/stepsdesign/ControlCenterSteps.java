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
}
