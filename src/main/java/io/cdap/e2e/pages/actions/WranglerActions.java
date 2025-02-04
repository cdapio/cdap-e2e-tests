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
import io.cdap.e2e.utils.ConstantsUtil;
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
    WaitHelper.waitForPageToLoad(ConstantsUtil.DEFAULT_TIMEOUT_SECONDS);
    WaitHelper.waitForElementToBeEnabled(WranglerLocators.locateTransformationButton(columnName));
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
    WaitHelper.waitForPageToLoad(ConstantsUtil.DEFAULT_TIMEOUT_SECONDS);
    WaitHelper.waitForElementToBeEnabled(WranglerLocators.locateTransformationButton(columnName));
    ElementHelper.clickOnElement(WranglerLocators.locateTransformationButton(columnName));
    String pluginPropertyDirective = PluginPropertyUtils.getPluginPropertyElementTestId(directive);
    String pluginPropertyDirectiveOption = PluginPropertyUtils.getPluginPropertyElementTestId(option);
    switch (directive) {

      case "CustomTransform":
        ElementHelper.clickOnElement(WranglerLocators.locateDirectivesTitle(pluginPropertyDirective));
        ElementHelper.sendKeysToTextarea(WranglerLocators.enterTextArea(pluginPropertyDirective), option);
        ElementHelper.clickOnElement(WranglerLocators.applyButton(pluginPropertyDirective));
        break;

      case "MaskData":
      case "Explode":
        ElementHelper.hoverOverElement(WranglerLocators.scrollButton);
        WaitHelper.waitForElementToBeDisplayed(WranglerLocators.locateDirectivesTitle(pluginPropertyDirective));
        ElementHelper.clickOnElement(WranglerLocators.locateDirectivesTitle(pluginPropertyDirective));
        ElementHelper.clickOnElement(WranglerLocators.selectDirectiveOptionsTag(pluginPropertyDirective,
                                                                                pluginPropertyDirectiveOption));
        break;

      case "Filter":
      case "SendToError":
        ElementHelper.clickOnElement(WranglerLocators.locateDirectivesTitle(pluginPropertyDirective));
        Select selectDropdown = new Select(WranglerLocators.selectDropdownOption(pluginPropertyDirective));
        ElementHelper.scrollToElementUsingJsExecutor(WranglerLocators.
                                                       selectFilterOptions(pluginPropertyDirective,
                                                                           pluginPropertyDirectiveOption));
        selectDropdown.selectByVisibleText(PluginPropertyUtils.pluginProp("filterEmptyProperty"));
        ElementHelper.clickOnElement(WranglerLocators.applyButton(pluginPropertyDirective));
        break;

      case "FillNullOrEmptyCells":
      case "CopyColumn":
        ElementHelper.clickOnElement(WranglerLocators.locateDirectivesTitle(pluginPropertyDirective));
        if (directive.equals("FillNullOrEmptyCells")) {
          ElementHelper.replaceElementValue(WranglerLocators.enterText(pluginPropertyDirective), option);
        } else {
          ElementHelper.replaceElementValue(WranglerLocators.enterText(pluginPropertyDirective), option);
        }
        ElementHelper.clickOnElement(WranglerLocators.applyButton(pluginPropertyDirective));
        break;

      case "Hash":
        ElementHelper.hoverOverElement(WranglerLocators.scrollButton);
        WaitHelper.waitForElementToBeDisplayed(WranglerLocators.locateDirectivesTitle(pluginPropertyDirective));
        ElementHelper.clickOnElement(WranglerLocators.locateDirectivesTitle(pluginPropertyDirective));
        Select select = new Select(WranglerLocators.selectDropdownOption(pluginPropertyDirective));
        ElementHelper.scrollToElementUsingJsExecutor(WranglerLocators.selectHashOption(option));
        select.selectByVisibleText(option);
        ElementHelper.clickOnElement(WranglerLocators.applyButton(pluginPropertyDirective));
        break;

      case "Decode":
      case "Encode":
        ElementHelper.hoverOverElement(WranglerLocators.scrollButton);
        WaitHelper.waitForElementToBeDisplayed(WranglerLocators.encodeDirective);
        if (directive.equals("Encode")) {
          ElementHelper.clickOnElement(WranglerLocators.encodeDirective);
        } else {
          ElementHelper.clickOnElement(WranglerLocators.decodeDirective);
        }
        ElementHelper.clickOnElement(WranglerLocators.encodeDecodeOptions(option));
        break;

      default:
        ElementHelper.clickOnElement(WranglerLocators.locateDirectivesTitle(pluginPropertyDirective));
        ElementHelper.clickOnElement(WranglerLocators.selectDirectiveOption(pluginPropertyDirective
          , pluginPropertyDirectiveOption));
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
    WaitHelper.waitForPageToLoad(ConstantsUtil.DEFAULT_TIMEOUT_SECONDS);
    WaitHelper.waitForElementToBeEnabled(WranglerLocators.locateTransformationButton(columnName));
    ElementHelper.clickOnElement(WranglerLocators.locateTransformationButton(columnName));
    String pluginPropertyDirective = PluginPropertyUtils.getPluginPropertyElementTestId(directive);
    ElementHelper.clickOnElement(WranglerLocators.locateDirectivesTitle(pluginPropertyDirective));

    if (directive.equals("FindAndReplace")) {
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
          Select select = new Select(WranglerLocators.selectDropdownOption(pluginPropertyDirective));
          select.selectByVisibleText(directiveType);
          if (directive.equals("Filter")) {
            ElementHelper.sendKeysToTextarea(WranglerLocators.enterText(pluginPropertyDirective), text);
          } else if (directive.equals("SendToError")) {
            ElementHelper.sendKeysToTextarea(WranglerLocators.enterText(pluginPropertyDirective), text);
          }
          ElementHelper.clickOnElement(WranglerLocators.applyButton(pluginPropertyDirective));
          break;

        case "Custom condition":
          if (directive.equals("Filter")) {
            Select selectCustom = new Select(WranglerLocators.selectDropdownOption(pluginPropertyDirective));
            selectCustom.selectByVisibleText(directiveType);
            ElementHelper.sendKeysToTextarea(WranglerLocators.enterTextArea(pluginPropertyDirective), text);
          } else if (directive.equals("SendToError")) {
            Select selectCustom = new Select(WranglerLocators.selectDropdownOption(pluginPropertyDirective));
            selectCustom.selectByVisibleText(directiveType);
            ElementHelper.sendKeysToTextarea(WranglerLocators.enterTextArea(pluginPropertyDirective), text);
          }
          break;
      }
    }
  }


  public static void selectDirectiveTypeWithThreeOptions(String columnName, String directive, String directiveType,
                                                         String option)
    throws InterruptedException {
    WaitHelper.waitForPageToLoad(ConstantsUtil.DEFAULT_TIMEOUT_SECONDS);
    WaitHelper.waitForElementToBeEnabled(WranglerLocators.locateTransformationButton(columnName));
    ElementHelper.clickOnElement(WranglerLocators.locateTransformationButton(columnName));
    String pluginPropertyDirective = PluginPropertyUtils.getPluginPropertyElementTestId(directive);
    String pluginPropertyDirectiveOption = PluginPropertyUtils.getPluginPropertyElementTestId(directiveType);
    String optionType = PluginPropertyUtils.getPluginPropertyElementTestId(option);
    ElementHelper.clickOnElement(WranglerLocators.locateDirectivesTitle(pluginPropertyDirective));

    switch (directiveType) {
      case "Decimal":
        ElementHelper.clickOnElement(WranglerLocators.selectDirectiveOption(pluginPropertyDirective,
                                                                            pluginPropertyDirectiveOption));
        ElementHelper.sendKeys(WranglerLocators.scaleText, optionType);
        ElementHelper.clickOnElement(WranglerLocators.applyButtonUppercase);
        break;

      case "CSV":
      case "LOG":
      case "SIMPLEDATE":
      case "DATETIME":
        ElementHelper.clickOnElement(WranglerLocators.selectDirectiveOption(pluginPropertyDirective, directiveType));
        ElementHelper.clickOnElement(WranglerLocators.parseModalOption(pluginPropertyDirective,
                                                                       pluginPropertyDirectiveOption, optionType));
        ElementHelper.clickOnElement(WranglerLocators.parseModalApplyButton(pluginPropertyDirectiveOption));
        break;

      case "Character_count":
        ElementHelper.clickOnElement(WranglerLocators.selectDirectiveOption(pluginPropertyDirective,
                                                                            pluginPropertyDirectiveOption));
        ElementHelper.replaceElementValue(WranglerLocators.enterText(pluginPropertyDirective), optionType);
        ElementHelper.clickOnElement(WranglerLocators.subMenuApplyButton(pluginPropertyDirective));
        break;

      case "FIXEDLENGTH":
        ElementHelper.clickOnElement(WranglerLocators.selectDirectiveOption(pluginPropertyDirective, directiveType));
        ElementHelper.sendKeys(WranglerLocators.columnWidths, optionType);
        ElementHelper.clickOnElement(WranglerLocators.parseModalApplyButton(pluginPropertyDirectiveOption));
        break;

      case "XMLTOJSON":
        ElementHelper.clickOnElement(WranglerLocators.selectDirectiveOption(pluginPropertyDirective, directiveType));
        ElementHelper.replaceElementValue(WranglerLocators.enterDepthXmlToJson, optionType);
        ElementHelper.clickOnElement(WranglerLocators.parseModalApplyButton(pluginPropertyDirectiveOption));
        break;

      case "JSON":
        ElementHelper.clickOnElement(WranglerLocators.selectDirectiveOption(pluginPropertyDirective, directiveType));
        ElementHelper.replaceElementValue(WranglerLocators.enterDepthJson, optionType);
        ElementHelper.clickOnElement(WranglerLocators.parseModalApplyButton(pluginPropertyDirectiveOption));
        break;

      case "EXCEL":
        ElementHelper.clickOnElement(WranglerLocators.selectDirectiveOption(pluginPropertyDirective, directiveType));
        ElementHelper.replaceElementValue(WranglerLocators.excelSheetNumber, optionType);
        ElementHelper.clickOnElement(WranglerLocators.parseModalApplyButton(pluginPropertyDirectiveOption));
        break;

      case "Using_patterns":
        ElementHelper.clickOnElement(WranglerLocators.selectDirectiveOptionsTag(pluginPropertyDirective,
                                                                                pluginPropertyDirectiveOption));
        ElementHelper.clickOnElement(WranglerLocators.selectPatternButton);
        ElementHelper.clickOnElementUsingJsExecutor(WranglerLocators.selectOptionForPatterns(optionType));
        ElementHelper.clickOnElement(WranglerLocators.extractFieldsApplyButton(pluginPropertyDirectiveOption));
        break;

      case "Using_delimiters":
        ElementHelper.clickOnElement(WranglerLocators.selectDirectiveOptionsTag(pluginPropertyDirective,
                                                                                pluginPropertyDirectiveOption));
        ElementHelper.clickOnElement(WranglerLocators.selectDelimiterOptions(option));
        ElementHelper.clickOnElement(WranglerLocators.extractFieldsApplyButton(pluginPropertyDirectiveOption));
        break;
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
    WaitHelper.waitForPageToLoad(ConstantsUtil.DEFAULT_TIMEOUT_SECONDS);
    WaitHelper.waitForElementToBeEnabled(WranglerLocators.locateTransformationButton(columnName));
    ElementHelper.clickOnElement(WranglerLocators.locateTransformationButton(columnName));
    String pluginPropertyDirective = PluginPropertyUtils.getPluginPropertyElementTestId(directive);
    String pluginPropertyDirectiveOption = PluginPropertyUtils.getPluginPropertyElementTestId(directiveType);
    String optionType = PluginPropertyUtils.getPluginPropertyElementTestId(option);
    ElementHelper.clickOnElement(WranglerLocators.locateDirectivesTitle(pluginPropertyDirective));
    switch (directiveType) {
      case "Concatenate":
        ElementHelper.clickOnElement(WranglerLocators.selectDirectiveOption(pluginPropertyDirective,
                                                                            pluginPropertyDirectiveOption));
        ElementHelper.sendKeys(WranglerLocators.enterText(pluginPropertyDirective), text);
        Select selectDropdown = new Select(WranglerLocators.selectDropdownOption(pluginPropertyDirective));
        selectDropdown.selectByVisibleText(option);
        ElementHelper.clickOnElement(WranglerLocators.subMenuApplyButton(pluginPropertyDirective));
        break;

      case "SIMPLEDATE":
      case "DATETIME":
        ElementHelper.clickOnElement(WranglerLocators.selectDirectiveOption(pluginPropertyDirective, directiveType));
        if (option.equals("Custom_Format")) {
          ElementHelper.clickOnElement(WranglerLocators.parseModalOption(pluginPropertyDirective,
                                                                         pluginPropertyDirectiveOption, optionType));
          ElementHelper.sendKeys(WranglerLocators.customDate, text);
          ElementHelper.clickOnElement(WranglerLocators.parseModalApplyButton(pluginPropertyDirectiveOption));
        }
        break;
      case "Always":
        Select select = new Select(WranglerLocators.selectDropdownOption(pluginPropertyDirective));
        select.selectByVisibleText(directiveType);
        ElementHelper.sendKeysToTextarea(WranglerLocators.enterCounterName, text);
        ElementHelper.replaceElementValue(WranglerLocators.incrementCount, option);
        ElementHelper.clickOnElement(WranglerLocators.applyButton(pluginPropertyDirective));
        break;
    }
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
    WaitHelper.waitForPageToLoad(ConstantsUtil.DEFAULT_TIMEOUT_SECONDS);
    WaitHelper.waitForElementToBeEnabled(WranglerLocators.locateTransformationButton(columnName));
    ElementHelper.clickOnElement(WranglerLocators.locateTransformationButton(columnName));
    String pluginPropertyDirective = PluginPropertyUtils.getPluginPropertyElementTestId(directive);
    ElementHelper.clickOnElement(WranglerLocators.locateDirectivesTitle(pluginPropertyDirective));
    switch (directive) {
      case "SetCounter":
        Select select = new Select(WranglerLocators.selectDropdownOption(pluginPropertyDirective));
        select.selectByVisibleText(option1);
        ElementHelper.sendKeysToTextarea(WranglerLocators.enterTextArea(pluginPropertyDirective), option2);
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
          ElementHelper.sendKeysToTextarea(WranglerLocators.enterTextArea(pluginPropertyDirective), option2);
        } else {
          ElementHelper.sendKeysToTextarea(WranglerLocators.variableValue, option2);
        }
        break;
    }
    ElementHelper.clickOnElement(WranglerLocators.applyButton(pluginPropertyDirective));
  }

  /**
   * Enters a directive command directly into the command line.
   *
   * @param directive The directive to be entered into the command line.
   * @throws InterruptedException If interrupted while waiting.
   */
  public static void enterDirectiveFromCommandLine(String directive) throws InterruptedException {
    WaitHelper.waitForPageToLoad(ConstantsUtil.DEFAULT_TIMEOUT_SECONDS);
    WaitHelper.waitForElementToBeEnabled(WranglerLocators.directiveCommandLine);
    WaitHelper.waitForElementToBeClickable(WranglerLocators.directiveCommandLine);
    ElementHelper.sendKeys(WranglerLocators.directiveCommandLine, directive);
    Actions act = new Actions(SeleniumDriver.getDriver());
    act.sendKeys(new CharSequence[]{Keys.ENTER}).perform();
  }

  /**
   * Selects checkboxes for two columns in the UI.
   *
   * @param column1 The first column's name to select its checkbox.
   * @param column2 The second column's name to select its checkbox.
   */
  public static void selectCheckboxOnTwoColumns(String column1, String column2) {
    WaitHelper.waitForPageToLoad(ConstantsUtil.DEFAULT_TIMEOUT_SECONDS);
    ElementHelper.clickOnElement(WranglerLocators.locateCheckboxOfColumn(column1));
    ElementHelper.clickOnElement(WranglerLocators.locateCheckboxOfColumn(column2));
  }
}
