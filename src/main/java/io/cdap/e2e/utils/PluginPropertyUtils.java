/*
 * Copyright © 2021 Cask Data, Inc.
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
package io.cdap.e2e.utils;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * PluginPropertyUtils contains the e2e test helper functions.
 */
public class PluginPropertyUtils {

  private static final Properties errorProperties = new Properties();
  private static final Logger logger = LoggerFactory.getLogger(PluginPropertyUtils.class);
  protected static Properties pluginProperties = new Properties();
  private static final Properties pluginPropertyDataCyAttributes = new Properties();

  static {
    try {
      pluginProperties.load(PluginPropertyUtils.class
        .getResourceAsStream("/" + ConstantsUtil.DEFAULT_PLUGIN_PROPERTIES_FILE));
      errorProperties.load(PluginPropertyUtils.class
        .getResourceAsStream("/" + ConstantsUtil.DEFAULT_ERROR_PROPERTIES_FILE));
      pluginPropertyDataCyAttributes.load(PluginPropertyUtils.class
                             .getResourceAsStream("/" + ConstantsUtil.DEFAULT_DATACY_ATTRIBUTES_FILE));
    } catch (Exception e) {
      logger.error("Error while reading properties file" + e);
    }
  }

  public static String pluginProp(String property) {
    return pluginProperties.getProperty(property);
  }

  public static String errorProp(String property) {
    return errorProperties.getProperty(property);
  }

  public static void addPluginProp(String property, String value) {
    pluginProperties.put(property, value);
  }

  public static void removePluginProp(String property) {
    pluginProperties.remove(property);
  }

  public static String getPluginPropertyDataCyAttribute(String property) {
    return pluginPropertyDataCyAttributes.getProperty(property);
  }

  /**
   * @param property
   * @deprecated Use
   * {@link io.cdap.e2e.pages.actions.CdfPluginPropertiesActions#verifyRequiredPropertyHasNoValueErrorMessage(String)}
   * and
   * {@link io.cdap.e2e.pages.actions.CdfPluginPropertiesActions#verifyPluginPropertyInlineErrorMessageColor(String)}
   */
  @Deprecated
  public static void validateMandatoryPropertyError(String property) {
    String expectedErrorMessage = ConstantsUtil.ERROR_MSG_MANDATORY.replaceAll("PROPERTY", property);
    String actualErrorMessage = findPropertyErrorElement(property).getText();
    Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    String actualColor = PluginPropertyUtils.getErrorColor(PluginPropertyUtils.findPropertyErrorElement(property));
    String expectedColor = ConstantsUtil.ERROR_MSG_COLOR;
    Assert.assertEquals(expectedColor, actualColor);
  }

  /**
   * @param property
   * @return
   * @deprecated Use
   * {@link io.cdap.e2e.pages.locators.CdfPluginPropertiesLocators#locatePluginPropertyInlineError(String)}
   */
  @Deprecated
  public static WebElement findPropertyErrorElement(String property) {
    return SeleniumDriver.getDriver().findElement(
      By.xpath("//*[@data-cy='" + property + "']/following-sibling::div[@data-cy='property-row-error']"));
  }

  /**
   * @param element
   * @return
   * @deprecated Use {@link ElementHelper#getElementColorCssProperty(WebElement)}
   */
  @Deprecated
  public static String getErrorColor(WebElement element) {
    String color = element.getCssValue(ConstantsUtil.COLOR);
    String[] hexValue = color.replace("rgba(", "").
      replace(")", "").split(",");
    int hexValue1 = Integer.parseInt(hexValue[0]);
    hexValue[1] = hexValue[1].trim();
    int hexValue2 = Integer.parseInt(hexValue[1]);
    hexValue[2] = hexValue[2].trim();
    int hexValue3 = Integer.parseInt(hexValue[2]);
    return String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
  }
}
