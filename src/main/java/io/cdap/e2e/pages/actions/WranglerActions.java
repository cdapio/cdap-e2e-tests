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

import io.cdap.e2e.pages.locators.WranglerLocators;
import io.cdap.e2e.utils.ElementHelper;
import io.cdap.e2e.utils.PluginPropertyUtils;
import io.cdap.e2e.utils.SeleniumDriver;
import io.cdap.e2e.utils.SeleniumHelper;
import io.cdap.e2e.utils.WaitHelper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CDF wrangler related actions.
 */
public class WranglerActions {
  private static final Logger logger = LoggerFactory.getLogger(WranglerActions.class);

  static {
    SeleniumHelper.getPropertiesLocators(WranglerLocators.class);
  }

  /**
   * Selects a directive for a specific column by clicking on the transformation button and the directive option.
   *
   * @param columnName The name of the column on which the directive will be applied.
   * @param directive  The directive to be selected for the column.
   */
  public static void selectDirective(String columnName, String directive) {
    ElementHelper.clickOnElement(WranglerLocators.locateTransformationButton(columnName));
    String pluginPropertyDirective = PluginPropertyUtils.getPluginPropertyElementTestId(directive);
    ElementHelper.clickOnElement(WranglerLocators.locateDirectivesTitle(pluginPropertyDirective));
  }

  /**
   * Selects a directive for a specific column with an additional option and performs the respective action.
   *
   * @param columnName The name of the column on which the directive will be applied.
   * @param directive  The directive to be selected for the column.
   * @param option     The additional option that modifies the directive.
   * @throws InterruptedException If interrupted while waiting.
   */
  public static void selectDirectiveAndOption(String columnName, String directive, String option)
    throws InterruptedException {
    ElementHelper.clickOnElement(WranglerLocators.locateTransformationButton(columnName));
    String pluginPropertyDirective = PluginPropertyUtils.getPluginPropertyElementTestId(directive);
    switch (directive) {

      case "CustomTransform":
        ElementHelper.clickOnElement(WranglerLocators.locateDirectivesTitle(pluginPropertyDirective));
        ElementHelper.sendKeysToTextarea(WranglerLocators.filterTextArea, option);
        ElementHelper.clickOnElement(WranglerLocators.applyButton);
        break;

      case "Filter":
      case "Sendtoerror":
        ElementHelper.clickOnElement(WranglerLocators.locateDirectivesTitle(pluginPropertyDirective));
        Select selectDropdown = new Select(WranglerLocators.filterSelect);
        ElementHelper.scrollToElementUsingJsExecutor(WranglerLocators.selectDirectiveOption(option));
        selectDropdown.selectByVisibleText(option);
        ElementHelper.clickOnElement(WranglerLocators.applyButton);
        break;

      case "FillNullOrEmptyCells":
      case "CopyColumn":
        ElementHelper.clickOnElement(WranglerLocators.locateDirectivesTitle(pluginPropertyDirective));
        ElementHelper.replaceElementValue(WranglerLocators.enterText, option);
        ElementHelper.clickOnElement(WranglerLocators.applyButton);
        break;

      case "Hash":
        ElementHelper.hoverOverElement(WranglerLocators.scrollButton);
        WaitHelper.waitForElementToBeDisplayed(WranglerLocators.scrollButtonDisabled);
        ElementHelper.clickOnElement(WranglerLocators.locateDirectivesTitle(pluginPropertyDirective));
        Select select = new Select(WranglerLocators.filterSelect);
        ElementHelper.scrollToElementUsingJsExecutor(WranglerLocators.selectDirectiveOption(option));
        select.selectByVisibleText(option);
        ElementHelper.clickOnElement(WranglerLocators.applyButton);
        break;

      case "Decode":
      case "Encode":
        ElementHelper.hoverOverElement(WranglerLocators.scrollButton);
        WaitHelper.waitForElementToBeDisplayed(WranglerLocators.scrollButtonDisabled);
        if (directive.equals("Encode")) {
          ElementHelper.clickOnElement(WranglerLocators.encodeDirective);
        } else {
          ElementHelper.clickOnElement(WranglerLocators.decodeDirective);
        }
        ElementHelper.clickOnElement(WranglerLocators.selectDirectiveOption(option));
        break;

      default:
        ElementHelper.clickOnElement(WranglerLocators.locateDirectivesTitle(pluginPropertyDirective));
        ElementHelper.clickOnElement(WranglerLocators.selectDirectiveOption(option));
    }

  }

  /**
   * Selects a directive with three options and performs the action depending on the directive type.
   *
   * @param columnName    The name of the column on which the directive will be applied.
   * @param directive     The directive to be selected for the column.
   * @param directiveType The specific type of directive.
   *  @param text          The text to be entered for the directive.
   * @throws InterruptedException If interrupted while waiting.
   */
  public static void selectDirectiveTypeWithDropdownAndText(String columnName, String directive, String directiveType,
                                                            String text) throws
    InterruptedException {
    ElementHelper.clickOnElement(WranglerLocators.locateTransformationButton(columnName));
    String pluginPropertyDirective = PluginPropertyUtils.getPluginPropertyElementTestId(directive);
    ElementHelper.clickOnElement(WranglerLocators.locateDirectivesTitle(pluginPropertyDirective));

    if (pluginPropertyDirective.equals("findAndReplace")) {
      ElementHelper.sendKeys(WranglerLocators.enterOldValue, directiveType);
      ElementHelper.sendKeys(WranglerLocators.enterNewValue, text);
      ElementHelper.clickOnElement(WranglerLocators.replaceAllButton);
    } else {

      switch (directiveType) {

        case "value contains":
        case "value is":
        case "value starts with":
        case "value ends with":
        case "value matches regex":
          Select select = new Select(WranglerLocators.filterSelect);
          select.selectByVisibleText(directiveType);
          ElementHelper.sendKeysToTextarea(WranglerLocators.enterText, text);
          break;

        case "Custom condition":
          Select selectCustom = new Select(WranglerLocators.filterSelect);
          selectCustom.selectByVisibleText(directiveType);
          ElementHelper.sendKeysToTextarea(WranglerLocators.filterTextArea, text);
          break;
      }
    }
  }


  public static void selectDirectiveTypeWithThreeOptions(String columnName, String directive, String directiveType,
                                                         String option)
    throws InterruptedException {
    ElementHelper.clickOnElement(WranglerLocators.locateTransformationButton(columnName));
    String pluginPropertyDirective = PluginPropertyUtils.getPluginPropertyElementTestId(directive);
    ElementHelper.clickOnElement(WranglerLocators.locateDirectivesTitle(pluginPropertyDirective));
    ElementHelper.clickOnElement(WranglerLocators.selectDirectiveOption(directiveType));

    switch (directiveType) {
      case "Decimal":
        ElementHelper.sendKeys(WranglerLocators.scaleText, option);
        ElementHelper.clickOnElement(WranglerLocators.applyButtonUppercase);
        break;

      case "Character count":
        ElementHelper.replaceElementValue(WranglerLocators.enterText, option);
        ElementHelper.clickOnElement(WranglerLocators.applyButton);
        break;

      case "Fixed length":
        ElementHelper.sendKeys(WranglerLocators.columnWidths, option);
        ElementHelper.clickOnElement(WranglerLocators.applyButton);
        break;

      case "XML to JSON":
      case "JSON":
        ElementHelper.replaceElementValue(WranglerLocators.enterDepth, option);
        ElementHelper.clickOnElement(WranglerLocators.applyButton);
        break;

      case "Excel":
        ElementHelper.replaceElementValue(WranglerLocators.excelSheetNumber, option);
        ElementHelper.clickOnElement(WranglerLocators.applyButton);
        break;

      case "Using patterns":
        ElementHelper.clickOnElement(WranglerLocators.selectPattern);
        ElementHelper.clickOnElement(WranglerLocators.selectOptionForPatterns(option));
        ElementHelper.clickOnElement(WranglerLocators.extractButton);
        break;

      default:
        ElementHelper.clickOnElement(WranglerLocators.selectRadioButton(option));
        ElementHelper.clickOnElement(WranglerLocators.applyButton);
    }
  }

  /**
   * Selects a directive with four options and performs the necessary action based on the directive type and input.
   *
   * @param columnName    The name of the column on which the directive will be applied.
   * @param directive     The directive to be selected for the column.
   * @param directiveType The type of directive being applied.
   * @param option        The additional option to modify the directive.
   * @param text          The text to be input for the directive.
   * @throws InterruptedException If interrupted while waiting.
   */
  public static void selectDirectiveTypeWithFourOption(String columnName, String directive, String directiveType,
                                                       String option, String text)
    throws InterruptedException {
    ElementHelper.clickOnElement(WranglerLocators.locateTransformationButton(columnName));
    String pluginPropertyDirective = PluginPropertyUtils.getPluginPropertyElementTestId(directive);
    ElementHelper.clickOnElement(WranglerLocators.locateDirectivesTitle(pluginPropertyDirective));
    switch (directiveType) {
      case "Concatenate":
        ElementHelper.clickOnElement(WranglerLocators.selectDirectiveOption(directiveType));
        ElementHelper.sendKeys(WranglerLocators.enterText, text);
        Select selectDropdown = new Select(WranglerLocators.filterSelect);
        selectDropdown.selectByVisibleText(option);
        ElementHelper.clickOnElementUsingJsExecutor(WranglerLocators.selectDirectiveOption(option));
        break;

      case "Datetime":
      case "Simple date":
        if (option.equals("Custom format")) {
          ElementHelper.clickOnElement(WranglerLocators.selectDirectiveOption(directiveType));
          ElementHelper.clickOnElement(WranglerLocators.selectRadioButton(option));
          ElementHelper.sendKeys(WranglerLocators.customDate, text);
        }
        break;
      case "Always":
        Select select = new Select(WranglerLocators.filterSelect);
        select.selectByVisibleText(directiveType);
        ElementHelper.sendKeysToTextarea(WranglerLocators.enterCounterName, text);
        ElementHelper.replaceElementValue(WranglerLocators.incrementCount, option);
        break;
    }
    ElementHelper.clickOnElement(WranglerLocators.applyButton);
  }

  /**
   * Selects a directive with five options and performs the action based on the directive and the provided options.
   *
   * @param columnName The name of the column on which the directive will be applied.
   * @param directive  The directive to be selected for the column.
   * @param option1    The first option to modify the directive.
   * @param option2    The second option to modify the directive.
   * @param option3    The third option to modify the directive.
   * @param option4    The fourth option to modify the directive.
   * @throws InterruptedException If interrupted while waiting.
   */
  public static void selectDirectiveTypeWithFiveOption(String columnName, String directive, String option1,
                                                       String option2, String option3, String option4)
    throws InterruptedException {
    ElementHelper.clickOnElement(WranglerLocators.locateTransformationButton(columnName));
    String pluginPropertyDirective = PluginPropertyUtils.getPluginPropertyElementTestId(directive);
    ElementHelper.clickOnElement(WranglerLocators.locateDirectivesTitle(pluginPropertyDirective));
    switch (directive) {
      case "SetCounter":
        Select select = new Select(WranglerLocators.filterSelect);
        select.selectByVisibleText(option1);
        ElementHelper.sendKeysToTextarea(WranglerLocators.filterTextArea, option2);
        ElementHelper.sendKeysToTextarea(WranglerLocators.enterCounterName, option4);
        ElementHelper.replaceElementValue(WranglerLocators.incrementCount, option3);
        break;

      case "DefineVariable":
        ElementHelper.sendKeysToTextarea(WranglerLocators.variableName, option4);
        Select selectColumnName = new Select(WranglerLocators.selectColumn);
        selectColumnName.selectByVisibleText(option3);
        Select selectVar = new Select(WranglerLocators.selectRow);
        selectVar.selectByVisibleText(option1);
        if (option1.equals("Custom condition")) {
          ElementHelper.sendKeysToTextarea(WranglerLocators.filterTextArea, option2);
        } else {
          ElementHelper.sendKeysToTextarea(WranglerLocators.variableValue, option2);
        }
        break;
    }
    ElementHelper.clickOnElement(WranglerLocators.applyButton);
  }

  /**
   * Enters a directive command directly into the command line.
   *
   * @param directive The directive to be entered into the command line.
   * @throws InterruptedException If interrupted while waiting.
   */
  public static void enterDirectiveFromCommandLine(String directive) throws InterruptedException {
    ElementHelper.sendKeys(WranglerLocators.directiveCommandLine, directive);
    Actions act = new Actions(SeleniumDriver.getDriver());
    act.sendKeys(new CharSequence[]{Keys.ENTER}).perform();
    WaitHelper.waitForElementToBeClickable(WranglerLocators.directiveCommandLine);
  }

  /**
   * Selects checkboxes for two columns in the UI.
   *
   * @param column1 The first column's name to select its checkbox.
   * @param column2 The second column's name to select its checkbox.
   */
  public static void selectCheckboxOnTwoColumns(String column1, String column2) {
    ElementHelper.clickOnElement(WranglerLocators.locateCheckboxOfColumn(column1));
    ElementHelper.clickOnElement(WranglerLocators.locateCheckboxOfColumn(column2));
  }
}
