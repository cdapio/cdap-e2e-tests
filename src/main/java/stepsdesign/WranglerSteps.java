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

import io.cdap.e2e.pages.actions.WranglerActions;
import io.cdap.e2e.pages.locators.WranglerLocators;
import io.cdap.e2e.utils.CdfHelper;
import io.cdap.e2e.utils.SeleniumHelper;
import io.cucumber.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CDF wrangler related steps.
 */
public class WranglerSteps implements CdfHelper {
  private static final Logger logger = LoggerFactory.getLogger(WranglerSteps.class);

  static {
    SeleniumHelper.getPropertiesLocators(WranglerLocators.class);
  }

  @Then("Expand dropdown column: {string} and apply directive: {string}")
  public static void selectDirective(String columnName, String directive) {
    WranglerActions.selectDirective(columnName, directive);
  }

  @Then("Expand dropdown column: {string} and apply directive: {string} as {string}")
  public static void selectDirectiveAndEnterOption(String columnName, String directive, String text)
    throws InterruptedException {
    WranglerActions.selectDirectiveAndOption(columnName, directive, text);
  }

  @Then("Enter directive from CLI {string}")
  public static void enterDirectiveFromUserInput(String directive) throws InterruptedException {
    WranglerActions.enterDirectiveFromCommandLine(directive);
  }

  @Then("Expand dropdown column: {string} and apply directive: {string} as {string} with: {string} option")
  public static void selectDirectiveWithThreeOption(String columnName, String directive, String option,
                                                    String optionType)
    throws InterruptedException {
    WranglerActions.selectDirectiveTypeWithThreeOptions(columnName, directive, option, optionType);
  }

  @Then("Select checkbox on two columns: {string} and {string}")
  public static void selectDirectiveOnTwoColumns(String column1, String column2)
    throws InterruptedException {
    WranglerActions.selectCheckboxOnTwoColumns(column1, column2);
  }

  @Then("Expand dropdown column: {string} and apply directive: {string} and select: {string} and enter: {string}")
  public static void selectDirectiveDropdownAndText(String columnName, String directive, String option,
                                                    String optionType) throws InterruptedException {
    WranglerActions.selectDirectiveTypeWithDropdownAndText(columnName, directive, option, optionType);
  }

  @Then("Expand dropdown column: {string} and apply directive: {string} with directive type: {string} and select: " +
    "{string} and enter: {string}")
  public static void selectFourOptions(String columnName, String directive, String option, String text,
                                           String dropdown)
    throws InterruptedException {
    WranglerActions.selectDirectiveTypeWithFourOption(columnName, directive, option, text, dropdown);
  }

  @Then("Expand dropdown column: {string} and apply directive:{string} and select {string} with condition: {string} " +
    "with option: {string} and name: {string}")
  public static void selectFiveOptions(String columnName, String directive, String option1, String option2,
                                       String option3, String option4)
    throws InterruptedException {
    WranglerActions.selectDirectiveTypeWithFiveOption(columnName, directive, option1, option2, option3, option4);
  }
}
