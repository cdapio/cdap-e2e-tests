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
import io.cdap.e2e.utils.SeleniumHelper;
import io.cdap.e2e.utils.WaitHelper;
import org.junit.Assert;
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
   * Verify if the generated Output Schema matches the expected Schema
   *
   * @param schemaJsonArray Property key in the Plugin Properties file whose value is the Expected Schema
   *                        ({@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE})
   */
  public static void verifyOutputSchemaMatchesExpectedSchema(String schemaJsonArray) {
    Map<String, String> expectedOutputSchema =
      JsonUtils.convertKeyValueJsonArrayToMap(PluginPropertyUtils.pluginProp(schemaJsonArray));

    Assert.assertTrue("Schema displayed on UI should match with the expected Schema",
      getOutputSchema().equals(expectedOutputSchema));
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
}
