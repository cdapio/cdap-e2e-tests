/*
 * Copyright © 2022 Cask Data, Inc.
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

import io.cdap.e2e.pages.locators.CdfPluginPropertiesLocators;
import io.cdap.e2e.pages.locators.CdfSchemaLocators;
import io.cdap.e2e.pages.locators.CdfStudioLocators;
import io.cdap.e2e.utils.AssertionHelper;
import io.cdap.e2e.utils.ConstantsUtil;
import io.cdap.e2e.utils.ElementHelper;
import io.cdap.e2e.utils.JsonUtils;
import io.cdap.e2e.utils.PluginPropertyUtils;
import io.cdap.e2e.utils.SeleniumDriver;
import io.cdap.e2e.utils.SeleniumHelper;
import io.cdap.e2e.utils.WaitHelper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents Cdf Plugin's Properties page Actions
 */
public class CdfPluginPropertiesActions {
  private static final Logger logger = LoggerFactory.getLogger(CdfPluginPropertiesActions.class);

  static {
    SeleniumHelper.getPropertiesLocators(CdfPluginPropertiesLocators.class);
    SeleniumHelper.getPropertiesLocators(CdfSchemaLocators.class);
  }

  /**
   * Click Macro (M) button of Plugin Property
   *
   * @param pluginProperty @data-cy attribute value of Plugin Property.
   *                       If pluginProperty is present in {@link ConstantsUtil#DEFAULT_DATACY_ATTRIBUTES_FILE}
   *                       then its data-cy is fetched from it
   *                       else pluginProperty is used as it is.
   */
  public static void clickMacroButtonOfProperty(String pluginProperty) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(pluginProperty);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = pluginProperty;
    }
    CdfPluginPropertiesLocators.locateMacroButtonOfProperty(pluginPropertyDataCyAttribute).click();
  }

  /**
   * Fill value in the Macro enabled Plugin Property (input)
   *
   * @param pluginProperty @data-cy attribute value of Plugin Property.
   *                       If pluginProperty is present in {@link ConstantsUtil#DEFAULT_DATACY_ATTRIBUTES_FILE}
   *                       then its data-cy is fetched from it
   *                       else pluginProperty is used as it is.
   * @param argument
   */
  public static void fillValueInMacroEnabledInputProperty(String pluginProperty, String argument) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(pluginProperty);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = pluginProperty;
    }
    ElementHelper.replaceElementValue(
      CdfPluginPropertiesLocators.locateMacroInputOfProperty(pluginPropertyDataCyAttribute), "${" + argument + "}");
  }

  /**
   * Fill value in the Macro enabled Plugin Property (textarea)
   *
   * @param pluginProperty @data-cy attribute value of Plugin Property.
   *                       If pluginProperty is present in {@link ConstantsUtil#DEFAULT_DATACY_ATTRIBUTES_FILE}
   *                       then its data-cy is fetched from it
   *                       else pluginProperty is used as it is.
   * @param argument
   */
  public static void fillValueInMacroEnabledTextareaProperty(String pluginProperty, String argument) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(pluginProperty);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = pluginProperty;
    }
    ElementHelper.selectAllTextAndClear(CdfPluginPropertiesLocators
      .locateMacroTextareaOfProperty(pluginPropertyDataCyAttribute));
    ElementHelper.sendKeysToTextarea(
      CdfPluginPropertiesLocators.locateMacroTextareaOfProperty(pluginPropertyDataCyAttribute),
      "${" + argument + "}");
  }

  /**
   * Select "Macro" as action from Actions dropdown of Output Schema Plugin Property
   */
  public static void selectMacroActionOfOutputSchemaProperty() {
    ElementHelper.selectDropdownOption(CdfSchemaLocators.schemaActions,
                                       CdfPluginPropertiesLocators.locateDropdownListItem("macro"));
    WaitHelper.waitForElementToBeHidden(CdfSchemaLocators.schemaActionType("macro"));
  }

  /**
   * Click on the Get Schema button inside Plugin's properties page
   */
  public static void clickGetSchemaButton() {
    ElementHelper.clickOnElement(CdfPluginPropertiesLocators.getSchemaButton);
    WaitHelper.waitForElementToBeOptionallyDisplayed(
      CdfPluginPropertiesLocators.locatorOfLoadingSpinnerOnGetSchemaButton(), ConstantsUtil.SMALL_TIMEOUT_SECONDS);
    WaitHelper.waitForElementToBeHidden(
      CdfPluginPropertiesLocators.locatorOfLoadingSpinnerOnGetSchemaButton(), ConstantsUtil.DEFAULT_TIMEOUT_SECONDS);
    WaitHelper.waitForElementToBeDisplayed(CdfPluginPropertiesLocators.getSchemaButton);
  }

  /**
   * Click on the Validate button inside Plugin's properties page
   */
  public static void clickValidateButton() {
    ElementHelper.clickOnElement(CdfPluginPropertiesLocators.validateButton);
    WaitHelper.waitForElementToBeOptionallyDisplayed(
      CdfPluginPropertiesLocators.locatorOfLoadingSpinnerOnValidateButton(), ConstantsUtil.SMALL_TIMEOUT_SECONDS);
    WaitHelper.waitForElementToBeHidden(
      CdfPluginPropertiesLocators.locatorOfLoadingSpinnerOnValidateButton(), ConstantsUtil.DEFAULT_TIMEOUT_SECONDS);
    WaitHelper.waitForElementToBeDisplayed(CdfPluginPropertiesLocators.validateButton);
  }

  /**
   * Verify if the Plugin is validated successfully
   */
  public static void verifyIfPluginIsValidatedSuccessfully() {
    WaitHelper.waitForElementToBeDisplayed(CdfPluginPropertiesLocators.pluginValidationSuccessMsg);
    String expectedMessage = PluginPropertyUtils.errorProp(ConstantsUtil.VALIDATION_SUCCESS_MESSAGE);
    AssertionHelper.verifyElementContainsText(
      CdfPluginPropertiesLocators.pluginValidationSuccessMsg, expectedMessage);
  }

  /**
   * Get Plugin Property's in-line error message
   *
   * @param propertyName Plugin Property name
   * @return Error message
   */
  public static String getPluginPropertyInlineErrorMessage(String propertyName) {
    return ElementHelper.getElementText(CdfPluginPropertiesLocators.locatePluginPropertyInlineError(propertyName));
  }

  /**
   * Verify Plugin Property's in-line Error message
   *
   * @param propertyName               Plugin Property name
   * @param expectedInlineErrorMessage Expected Error message
   */
  public static void verifyPluginPropertyInlineErrorMessage(String propertyName, String expectedInlineErrorMessage) {
    WaitHelper.waitForElementToBeDisplayed(CdfPluginPropertiesLocators.locatePluginPropertyInlineError(propertyName));
    AssertionHelper.verifyElementDisplayed(CdfPluginPropertiesLocators.locatePluginPropertyInlineError(propertyName));
    AssertionHelper.verifyElementContainsText(
      CdfPluginPropertiesLocators.locatePluginPropertyInlineError(propertyName), expectedInlineErrorMessage
    );
  }

  /**
   * Verify Plugin Property's in-line Error message using the Error message location in the .properties file
   * {@link ConstantsUtil#DEFAULT_ERROR_PROPERTIES_FILE}
   *
   * @param propertyName         Property name
   * @param errorMessageLocation Error message location
   */
  public static void verifyPluginPropertyInlineError(String propertyName, String errorMessageLocation) {
    String expectedErrorMessage = PluginPropertyUtils.errorProp(errorMessageLocation);
    verifyPluginPropertyInlineErrorMessage(propertyName, expectedErrorMessage);
  }

  /**
   * Verify if Plugin Property shows: "Required property 'propertyName' has no value." in-line error message
   *
   * @param propertyName Property name
   */
  public static void verifyRequiredPropertyHasNoValueErrorMessage(String propertyName) {
    String expectedErrorMessage = ConstantsUtil.ERROR_MSG_MANDATORY.replaceAll("PROPERTY", propertyName);
    verifyPluginPropertyInlineErrorMessage(propertyName, expectedErrorMessage);
  }

  /**
   * Verify if Plugin's Credentials/Authentication section Property shows error message for invalid credentials
   *
   * @param propertyName Property name
   */
  public static void verifyInvalidCredentialsErrorMessage(String propertyName) {
    String expectedErrorMessage = PluginPropertyUtils.errorProp(ConstantsUtil.INVALID_CREDENTIALS_ERROR_MESSAGE);
    verifyPluginPropertyInlineErrorMessage(propertyName, expectedErrorMessage);
  }

  /**
   * Verify the Color of the Plugin Property's in-line error message
   *
   * @param propertyName Property name
   */
  public static void verifyPluginPropertyInlineErrorMessageColor(String propertyName) {
    logger.info("Verify if plugin property: " + propertyName + "'s inline error message is shown in the expected " +
      "color: " + ConstantsUtil.ERROR_MSG_COLOR);
    String actualColor = ElementHelper.getElementColorCssProperty(
      CdfPluginPropertiesLocators.locatePluginPropertyInlineError(propertyName));
    String expectedColor = ConstantsUtil.ERROR_MSG_COLOR;
    Assert.assertEquals(expectedColor, actualColor);
  }

  /**
   * Verify Plugin Properties validation fails with Error message: "x error(s) found"
   *
   * @param errorCount Expected Error count
   */
  public static void verifyPluginPropertiesValidationFailsWithErrorMessage(int errorCount) {
    String errorText = "error";

    if (errorCount > 1) {
      errorText = "errors";
    }

    String expectedErrorMessage = PluginPropertyUtils.errorProp(ConstantsUtil.VALIDATION_ERROR_MESSAGE)
      .replace("COUNT", String.valueOf(errorCount)).replace("ERROR", errorText);

    AssertionHelper.verifyElementDisplayed(CdfPluginPropertiesLocators.pluginValidationErrorMsg);
    AssertionHelper.verifyElementContainsText(
      CdfPluginPropertiesLocators.pluginValidationErrorMsg, expectedErrorMessage);
  }

  /**
   * Verify Error message displayed on the header/at the top of the Plugin Properties page using the Error message
   * location in the .properties file {@link ConstantsUtil#DEFAULT_ERROR_PROPERTIES_FILE}
   *
   * @param errorMessageLocation Expected error message location
   */
  public static void verifyErrorMessageOnHeader(String errorMessageLocation) {
    String expectedErrorMessage = PluginPropertyUtils.errorProp(errorMessageLocation);
    AssertionHelper.verifyElementContainsText(CdfPluginPropertiesLocators.errorMessageOnHeader, expectedErrorMessage);
  }

  /**
   * Click on the Close button in the Plugin's properties page
   */
  public static void clickCloseButton() {
    ElementHelper.clickOnElement(CdfStudioLocators.closeButton);
  }

  /**
   * Get List of Fields from the Output Schema table of the Source plugin
   *
   * @return List of Fields
   */
  public static List<String> getListOfFieldsFromOutputSchema() {
    WaitHelper.waitForElementToBeDisplayed(CdfSchemaLocators.outputSchemaColumnNames.get(0));
    List<String> propertiesSchemaColumnList = new ArrayList<>();

    for (WebElement element : CdfSchemaLocators.outputSchemaColumnNames) {
      propertiesSchemaColumnList.add(element.getAttribute("value"));
    }

    return propertiesSchemaColumnList;
  }

  /**
   * Get the generated Output Schema of the Source plugin
   *
   * @return Output Schema
   */
  public static Map<String, String> getOutputSchema() {
    WaitHelper.waitForElementToBeDisplayed(CdfSchemaLocators.outputSchemaColumnNames.get(0));
    Map<String, String> actualOutputSchema = new HashMap<>();
    int index = 0;

    for (WebElement element : CdfSchemaLocators.outputSchemaColumnNames) {
      actualOutputSchema.put(
        ElementHelper.getElementAttribute(element, "value"),
        ElementHelper.getElementAttribute(CdfSchemaLocators.outputSchemaDataTypes.get(index), "title"));
      index++;
    }

    return actualOutputSchema;
  }

  /**
   * Scroll down until Field-Type entry is found in the Output Schema table
   *
   * @param fieldName
   * @param fieldType
   * @return
   */
  public static Boolean scrollDownInOutputSchemaUntilFieldEntry(String fieldName, String fieldType) {
    By locator = CdfPluginPropertiesLocators.locatorOfOutputSchemaFieldTypeEntry(fieldName, fieldType);
    boolean isFieldTypeEntryPresent = WaitHelper.waitForElementToBeOptionallyPresent(locator, 1);
    int totalSchemaRowsInit = CdfPluginPropertiesLocators.outputSchemaRows.size();
    int totalSchemaRowsUpdated;

    // Output Schema table only populates ~20 fields by default. Rest of the fields are not even present in the DOM.
    // They are only populated once the user starts scrolling down.
    // In the 'while' loop written below, we are scrolling down if the expected field is not present until all the
    // Output Schema fields are populated.
    while (!isFieldTypeEntryPresent) {
      ElementHelper.scrollToElementUsingJsExecutor(
        CdfPluginPropertiesLocators.locateOutputSchemaRow(totalSchemaRowsInit - 1));

      isFieldTypeEntryPresent = WaitHelper.waitForElementToBeOptionallyPresent(locator, 1);
      totalSchemaRowsUpdated = CdfPluginPropertiesLocators.outputSchemaRows.size();

      if (totalSchemaRowsUpdated == totalSchemaRowsInit) {
        break;
      } else {
        totalSchemaRowsInit = totalSchemaRowsUpdated;
      }
    }

    return isFieldTypeEntryPresent;
  }

  /**
   * Verify if the generated Output Schema matches the expected Schema
   *
   * @param schemaJsonArray Property key in the Plugin Properties file whose value is the Expected Schema
   *                        ({@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE})
   */
  public static void verifyOutputSchemaMatchesExpectedSchema(String schemaJsonArray) {
    Map<String, String> expectedOutputSchema =
      JsonUtils.convertKeyValueJsonArrayToMap(PluginPropertyUtils.pluginProp(schemaJsonArray));

    expectedOutputSchema.forEach((key, value) -> {
      boolean isFieldTypeEntryPresent = scrollDownInOutputSchemaUntilFieldEntry(key, value);

      if (!isFieldTypeEntryPresent) {
        logger.info("Unable to find <Field name: Field type>: " + key + ": " + value);
      }
    });

    Assert.assertTrue("Schema displayed on UI should match with the expected Schema",
      getOutputSchema().equals(expectedOutputSchema));
  }

  /**
   * Verify Output Schema for the Expected Schema of the Hierarchical Field
   *
   * @param fieldName       Hierarchical Field
   * @param schemaJsonArray Property key in the Plugin Properties file whose value is the Expected Schema
   *                        ({@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE})
   */
  public static void verifyOutputSchemaForHierarchicalField(String fieldName, String schemaJsonArray) {
    CdfPluginPropertiesLocators.locateExpandButtonOfSchemaRow(fieldName).click();

    boolean isFieldOfArrayType = WaitHelper.waitForElementToBeOptionallyPresent(
      CdfPluginPropertiesLocators.locatorOfExpandButtonOfRecordFieldInsideSchemaRow(fieldName), 2);
    if (isFieldOfArrayType) {
      CdfPluginPropertiesLocators.locateExpandButtonOfRecordFieldInsideSchemaRow(fieldName).click();
    }

    verifyIfExpectedSchemaFieldsArePresent(schemaJsonArray);
  }

  /**
   * Verify if the Expected Schema is present in the generated Output Schema
   *
   * @param schemaJsonArray Property key in the Plugin Properties file whose value is the Expected Schema
   *                        ({@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE})
   */
  public static void verifyIfExpectedSchemaFieldsArePresent(String schemaJsonArray) {
    Map<String, String> expectedOutputSchema =
      JsonUtils.convertKeyValueJsonArrayToMap(PluginPropertyUtils.pluginProp(schemaJsonArray));
    Boolean isFieldTypeEntryPresent = false;

    for (Map.Entry<String, String> entry : expectedOutputSchema.entrySet()) {
      isFieldTypeEntryPresent = scrollDownInOutputSchemaUntilFieldEntry(entry.getKey(), entry.getValue());
      Assert.assertTrue("Unable to find <Field name: Field type>: " +
        entry.getKey() + ": " + entry.getValue(), isFieldTypeEntryPresent);
    }
  }

  /**
   * Verify the Input Schema matches the Output Schema
   *
   * @param outputSchema Output Schema Field-Data type map
   */
  public static void verifyInputSchemaMatchesOutputSchema(Map<String, String> outputSchema) {
    Map<String, String> actualInputSchema = new HashMap<>();
    int index = 0;
    for (WebElement element : CdfSchemaLocators.inputSchemaColumnNames) {
      actualInputSchema.put(element.getAttribute("value"),
        CdfSchemaLocators.inputSchemaDataTypes.get(index).getAttribute("title"));
      index++;
    }

    Assert.assertTrue("Schema should match", actualInputSchema.equals(outputSchema));
  }

  /**
   * Click on the Plugin's Properties page tab
   *
   * @param tabName Properties, Preview or Documentation
   */
  public static void clickOnTab(String tabName) {
    ElementHelper.clickOnElement(CdfPluginPropertiesLocators.locateTab(tabName));
  }

  /**
   * Verify 'Output Records' table columns in the Preview tab to check if it matches the Output Schema fields
   *
   * @param listOfFieldsInOutputSchema List of Output Schema fields
   */
  public static void verifyOutputRecordsTableColumnsUnderPreviewTabMatchesOutputSchemaFields
  (List<String> listOfFieldsInOutputSchema) {
    List<String> previewTableColumnList = new ArrayList<>();

    for (WebElement element : CdfPluginPropertiesLocators.previewOutputRecordsTableColumnNames) {
      previewTableColumnList.add(ElementHelper.getElementAttribute(element, "title"));
    }

    Assert.assertTrue("Schema column list should match the preview column list",
      previewTableColumnList.equals(listOfFieldsInOutputSchema));
  }

  /**
   * Verify 'Input Records' table columns in the Preview tab to check if it matches the Input Schema fields
   *
   * @param listOfFieldsInInputSchema List of Input Schema fields
   */
  public static void verifyInputRecordsTableColumnsUnderPreviewTabMatchesInputSchemaFields
  (List<String> listOfFieldsInInputSchema) {
    List<String> previewTableColumnList = new ArrayList<>();

    for (WebElement element : CdfPluginPropertiesLocators.previewInputRecordsTableColumnNames) {
      previewTableColumnList.add(ElementHelper.getElementAttribute(element, "title"));
    }

    Assert.assertTrue("Schema column list should match the preview column list",
      previewTableColumnList.equals(listOfFieldsInInputSchema));
  }

  /**
   * Enter value in the Plugin Property (input)
   *
   * @param pluginProperty @data-cy attribute value of Plugin Property.
   *                       If pluginProperty is present in {@link ConstantsUtil#DEFAULT_DATACY_ATTRIBUTES_FILE}
   *                       then its data-cy is fetched from it
   *                       else pluginProperty is used as it is.
   * @param value          If value is present in {@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} as a key
   *                       then its value is fetched from it
   *                       else value is entered in the input as it is.
   */
  public static void enterValueInInputProperty(String pluginProperty, String value) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(pluginProperty);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = pluginProperty;
    }

    WebElement pluginPropertyInput = CdfPluginPropertiesLocators.locatePropertyInput(pluginPropertyDataCyAttribute);

    String valueFromPluginPropertiesFile = PluginPropertyUtils.pluginProp(value);
    if (valueFromPluginPropertiesFile == null) {
      ElementHelper.sendKeys(pluginPropertyInput, value);
      return;
    }

    ElementHelper.sendKeys(pluginPropertyInput, valueFromPluginPropertiesFile);
  }

  /**
   * Replace value in the Plugin Property (input)
   *
   * @param pluginProperty @data-cy attribute value of Plugin Property.
   *                       If pluginProperty is present in {@link ConstantsUtil#DEFAULT_DATACY_ATTRIBUTES_FILE}
   *                       then its data-cy is fetched from it
   *                       else pluginProperty is used as it is.
   * @param value          If value is present in {@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} as key
   *                       then value to replace is fetched from it
   *                       else input is replaced with the value as it is.
   */
  public static void replaceValueInInputProperty(String pluginProperty, String value) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(pluginProperty);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = pluginProperty;
    }

    WebElement pluginPropertyInput = CdfPluginPropertiesLocators.locatePropertyInput(pluginPropertyDataCyAttribute);

    String valueFromPluginPropertiesFile = PluginPropertyUtils.pluginProp(value);
    if (valueFromPluginPropertiesFile == null) {
      ElementHelper.replaceElementValue(pluginPropertyInput, value);
      return;
    }

    ElementHelper.replaceElementValue(pluginPropertyInput, valueFromPluginPropertiesFile);
  }

  /**
   * Enter value in the Plugin Property (textarea)
   *
   * @param pluginProperty @data-cy attribute value of Plugin Property.
   *                       If pluginProperty is present in {@link ConstantsUtil#DEFAULT_DATACY_ATTRIBUTES_FILE}
   *                       then its data-cy is fetched from it
   *                       else pluginProperty is used as it is.
   * @param value          If value is present in {@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} as key
   *                       then value to enter is fetched from it
   *                       else value is entered in textarea as it is.
   */
  public static void enterValueInTextareaProperty(String pluginProperty, String value) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(pluginProperty);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = pluginProperty;
    }

    WebElement pluginPropertyTextarea = CdfPluginPropertiesLocators
      .locatePropertyTextArea(pluginPropertyDataCyAttribute);

    String valueFromPluginPropertiesFile = PluginPropertyUtils.pluginProp(value);
    if (valueFromPluginPropertiesFile == null) {
      ElementHelper.sendKeysToTextarea(pluginPropertyTextarea, value);
      return;
    }

    ElementHelper.sendKeysToTextarea(pluginPropertyTextarea, valueFromPluginPropertiesFile);
  }

  /**
   * Replace value in the Plugin Property (textarea)
   *
   * @param pluginProperty @data-cy attribute value of Plugin Property.
   *                       If pluginProperty is present in {@link ConstantsUtil#DEFAULT_DATACY_ATTRIBUTES_FILE}
   *                       then its data-cy is fetched from it
   *                       else pluginProperty is used as it is.
   * @param value          If value is present in {@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} as key
   *                       then value to replace is fetched from it
   *                       else textarea is replaced with value as it is.
   */
  public static void replaceValueInTextareaProperty(String pluginProperty, String value) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(pluginProperty);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = pluginProperty;
    }

    WebElement pluginPropertyTextarea = CdfPluginPropertiesLocators
      .locatePropertyTextArea(pluginPropertyDataCyAttribute);
    ElementHelper.selectAllTextAndClear(pluginPropertyTextarea);

    String valueFromPluginPropertiesFile = PluginPropertyUtils.pluginProp(value);
    if (valueFromPluginPropertiesFile == null) {
      ElementHelper.sendKeysToTextarea(pluginPropertyTextarea, value);
      return;
    }

    ElementHelper.sendKeysToTextarea(pluginPropertyTextarea, valueFromPluginPropertiesFile);
  }

  /**
   * Click plugin property button
   *
   * @param pluginProperty @data-cy attribute value of Plugin Property.
   *                       If pluginProperty is present in {@link ConstantsUtil#DEFAULT_DATACY_ATTRIBUTES_FILE}
   *                       then its data-cy is fetched from it
   *                       else pluginProperty is used as it is.
   */
  public static void clickPluginPropertyButton(String pluginProperty) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(pluginProperty);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = pluginProperty;
    }

    ElementHelper.clickOnElement(CdfPluginPropertiesLocators.locatePropertyButton(pluginPropertyDataCyAttribute));
  }

  /**
   * Click plugin property element
   *
   * @param pluginProperty @data-cy attribute value of Plugin Property.
   *                       If pluginProperty is present in {@link ConstantsUtil#DEFAULT_DATACY_ATTRIBUTES_FILE}
   *                       then its data-cy is fetched from it
   *                       else pluginProperty is used as it is.
   */
  public static void clickPluginPropertyElement(String pluginProperty) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(pluginProperty);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = pluginProperty;
    }

    ElementHelper.clickOnElement(CdfPluginPropertiesLocators.locatePropertyElement(pluginPropertyDataCyAttribute));
  }

  /**
   * Select Plugin property radio button with passed value
   *
   * @param pluginProperty @data-cy attribute value of Plugin Property.
   *                       If pluginProperty is present in {@link ConstantsUtil#DEFAULT_DATACY_ATTRIBUTES_FILE}
   *                       then its data-cy is fetched from it
   *                       else pluginProperty is used as it is.
   * @param value          If value is present in {@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} as key
   *                       then value to select is fetched from it
   *                       else radio button with value passed as param is selected.
   */
  public static void selectPluginPropertyRadioButton(String pluginProperty, String value) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(pluginProperty);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = pluginProperty;
    }

    String valueFromPluginPropertiesFile = PluginPropertyUtils.pluginProp(value);
    if (valueFromPluginPropertiesFile == null) {
      ElementHelper.selectRadioButton(CdfPluginPropertiesLocators
        .locatePropertyRadioButton(pluginPropertyDataCyAttribute, value));
      return;
    }

    ElementHelper.selectRadioButton(CdfPluginPropertiesLocators.locatePropertyRadioButton(pluginPropertyDataCyAttribute,
      valueFromPluginPropertiesFile));
  }

  /**
   * Select Plugin property dropdown option
   *
   * @param pluginProperty @data-cy attribute value of Plugin Property.
   *                       If pluginProperty is present in {@link ConstantsUtil#DEFAULT_DATACY_ATTRIBUTES_FILE}
   *                       then its data-cy is fetched from it
   *                       else pluginProperty is used as it is.
   * @param option         If option is present in {@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} as key
   *                       then option to select is fetched from it
   *                       else dropdown item is selected with option passed as param.
   */
  public static void selectPluginPropertyDropdownOption(String pluginProperty, String option) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(pluginProperty);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = pluginProperty;
    }

    String optionFromPluginPropertiesFile = PluginPropertyUtils.pluginProp(option);
    if (optionFromPluginPropertiesFile == null) {
      ElementHelper.selectDropdownOption(CdfPluginPropertiesLocators
          .locatePropertyElement(pluginPropertyDataCyAttribute),
        CdfPluginPropertiesLocators.locateDropdownListItem(option));
      return;
    }

    ElementHelper.selectDropdownOption(CdfPluginPropertiesLocators.locatePropertyElement(pluginPropertyDataCyAttribute),
      CdfPluginPropertiesLocators.locateDropdownListItem(
        optionFromPluginPropertiesFile));
  }

  /**
   * Override default value of auto-detect with service account file path or json set in environment variable.
   *
   * Set values in below environment variable to override service account details
   * SERVICE_ACCOUNT_TYPE = FilePath or JSON
   * SERVICE_ACCOUNT_FILE_PATH = file path of json file
   * SERVICE_ACCOUNT_JSON = service account json
   */
  public static void overrideServiceAccountDetailsIfProvided() {
    String serviceAccountType = System.getenv("SERVICE_ACCOUNT_TYPE");
    logger.debug("ServiceAccount type set in environment variable 'SERVICE_ACCOUNT_TYPE' with value: "
                   + serviceAccountType);
    if (serviceAccountType != null) {
      if (serviceAccountType.equalsIgnoreCase("FilePath")) {
        String serviceAccountFilePath = System.getenv("SERVICE_ACCOUNT_FILE_PATH");
        if (serviceAccountFilePath == null) {
          logger.error("ServiceAccount override failed - " +
                         "Environment variable SERVICE_ACCOUNT_FILE_PATH is not set with filepath");
          return;
        }
        if (!serviceAccountFilePath.equalsIgnoreCase("auto-detect")) {
          CdfPluginPropertiesActions.replaceValueInInputProperty("serviceFilePath", serviceAccountFilePath);
          logger.info("ServiceAccount FilePath entered from environment variable with value: "
                        + serviceAccountFilePath);
        }
        return;
      }
      if (serviceAccountType.equalsIgnoreCase("JSON")) {
        String serviceAccountJSON = System.getenv("SERVICE_ACCOUNT_JSON");
        if (serviceAccountJSON == null) {
          logger.error("ServiceAccount override failed - " +
                         "Environment variable SERVICE_ACCOUNT_JSON is not set with JSON");
          return;
        }
        CdfPluginPropertiesActions.selectPluginPropertyRadioButton("serviceAccountType", "JSON");
        CdfPluginPropertiesActions.enterValueInInputProperty("serviceAccountJSON", serviceAccountJSON);
        logger.info("ServiceAccount JSON entered from environment variable : SERVICE_ACCOUNT_JSON");
        return;
      }
      logger.error("ServiceAccount override failed - ServiceAccount type set in environment variable " +
                     "'SERVICE_ACCOUNT_TYPE' with invalid value: " + serviceAccountType + ". " +
                     "Value should be either 'FilePath' or 'JSON'");
    }
  }

  /**
   * Verify plugin property contains text
   *
   * @param pluginProperty @data-cy attribute value of Plugin Property.
   *                       If pluginProperty is present in {@link ConstantsUtil#DEFAULT_DATACY_ATTRIBUTES_FILE}
   *                       then its data-cy is fetched from it
   *                       else pluginProperty is used as it is.
   * @param text           If text is present in {@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} as key
   *                       then text to VERIFY is fetched from it
   *                       else text param is used as it is.
   */
  public static void verifyPluginPropertyContainsText(String pluginProperty, String text) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(pluginProperty);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = pluginProperty;
    }

    String textFromPluginPropertiesFile = PluginPropertyUtils.pluginProp(text);
    if (textFromPluginPropertiesFile == null) {
      AssertionHelper.verifyElementContainsText(SeleniumDriver.getDriver().findElement(
        CdfPluginPropertiesLocators.locatorOfPropertyElementText(pluginPropertyDataCyAttribute)), text);
      return;
    }

    AssertionHelper.verifyElementContainsText(SeleniumDriver.getDriver().findElement(
      CdfPluginPropertiesLocators.locatorOfPropertyElementText(pluginPropertyDataCyAttribute)),
                                              textFromPluginPropertiesFile);
  }

  /**
   * Verify input plugin property contains value
   *
   * @param pluginProperty @data-cy attribute value of Plugin Property.
   *                       If pluginProperty is present in {@link ConstantsUtil#DEFAULT_DATACY_ATTRIBUTES_FILE}
   *                       then its data-cy is fetched from it
   *                       else pluginProperty is used as it is.
   * @param expectedValue  If expectedValue is present in {@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} as key
   *                       then expectedValue to verify is fetched from it
   *                       else expectedValue param is used as it is.
   */
  public static void verifyInputPluginPropertyContainsValue(String pluginProperty, String expectedValue) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(pluginProperty);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = pluginProperty;
    }

    String actualValue = ElementHelper
      .getElementAttribute(CdfPluginPropertiesLocators.locatePropertyInput(pluginPropertyDataCyAttribute), "value");

    String expectedValueFromPluginPropertiesFile = PluginPropertyUtils.pluginProp(expectedValue);
    if (expectedValueFromPluginPropertiesFile == null) {
      Assert.assertEquals("Verify value displayed in Input plugin property "
                            + pluginPropertyDataCyAttribute, expectedValue, actualValue);
      return;
    }

    Assert.assertEquals("Verify value displayed in Input plugin property "
                          + pluginPropertyDataCyAttribute, expectedValueFromPluginPropertiesFile, actualValue);
  }

  /**
   * Verify dropdown plugin property is selected with list option
   *
   * @param pluginProperty @data-cy attribute value of Plugin Property.
   *                       If pluginProperty is present in {@link ConstantsUtil#DEFAULT_DATACY_ATTRIBUTES_FILE}
   *                       then its data-cy is fetched from it
   *                       else pluginProperty is used as it is.
   * @param expectedOption If expectedOption is present in {@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} as key
   *                       then expectedOption to verify is fetched from it
   *                       else expectedOption param is used as it is.
   */
  public static void verifyDropdownPluginPropertySelectedWithOption(String pluginProperty, String expectedOption) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(pluginProperty);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = pluginProperty;
    }

    String actualOption = ElementHelper.getElementAttribute(
      CdfPluginPropertiesLocators.locatePropertyDropdownSelectedItem(pluginPropertyDataCyAttribute), "value");

    String expectedOptionFromPluginPropertiesFile = PluginPropertyUtils.pluginProp(expectedOption);
    if (expectedOptionFromPluginPropertiesFile == null) {
      Assert.assertEquals("Verify Option selected in dropdown plugin property "
                            + pluginPropertyDataCyAttribute, expectedOption, actualOption);
      return;
    }

    Assert.assertEquals("Verify Option selected in dropdown plugin property "
                          + pluginPropertyDataCyAttribute, expectedOptionFromPluginPropertiesFile, actualOption);
  }

  /**
   * Verify toggle plugin property's current toggled state
   *
   * @param pluginProperty       @data-cy attribute value of Plugin Property.
   *                             If pluginProperty is present in {@link ConstantsUtil#DEFAULT_DATACY_ATTRIBUTES_FILE}
   *                             then its data-cy is fetched from it
   *                             else pluginProperty is used as it is.
   * @param expectedToggledState If expectedToggledState is present in
   *                             {@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} as key
   *                             then expectedToggledState to verify is fetched from it
   *                             else expectedToggledState param is used as it is.
   */
  public static void verifyPluginPropertyToggleState(String pluginProperty, String expectedToggledState) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(pluginProperty);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = pluginProperty;
    }

    String expectedToggledStateFromPluginPropertiesFile = PluginPropertyUtils.pluginProp(expectedToggledState);
    if (expectedToggledStateFromPluginPropertiesFile == null) {
      AssertionHelper.verifyElementContainsText
        (CdfPluginPropertiesLocators.locatePropertyToggle(pluginPropertyDataCyAttribute), expectedToggledState);
      return;
    }

    AssertionHelper.verifyElementContainsText
      (CdfPluginPropertiesLocators.locatePropertyToggle(pluginPropertyDataCyAttribute),
       expectedToggledStateFromPluginPropertiesFile);
  }

  /**
   * Verify radio button plugin property is selected with expected value
   *
   * @param pluginProperty @data-cy attribute value of Plugin Property.
   *                       If pluginProperty is present in {@link ConstantsUtil#DEFAULT_DATACY_ATTRIBUTES_FILE}
   *                       then its data-cy is fetched from it
   *                       else pluginProperty is used as it is.
   * @param expectedValue  If expectedValue is present in {@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} as key
   *                       then expectedValue to verify is fetched from it
   *                       else expectedValue param is used as it is.
   */
  public static void verifyRadioButtonPluginPropertySelectedValue(String pluginProperty, String expectedValue) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(pluginProperty);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = pluginProperty;
    }

    String expectedValueFromPluginPropertiesFile = PluginPropertyUtils.pluginProp(expectedValue);
    if (expectedValueFromPluginPropertiesFile == null) {
      expectedValueFromPluginPropertiesFile = expectedValue;
    }

    boolean isElementDisplayed = ElementHelper.isElementDisplayed(
      CdfPluginPropertiesLocators.locatorOfSelectedPropertyRadioButton(pluginPropertyDataCyAttribute
        , expectedValueFromPluginPropertiesFile), ConstantsUtil.DEFAULT_TIMEOUT_SECONDS);
    Assert.assertTrue("Verify radio button " + pluginPropertyDataCyAttribute + " is selected with "
                        + expectedValueFromPluginPropertiesFile, isElementDisplayed);
  }
}
