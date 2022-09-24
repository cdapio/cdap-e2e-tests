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

import io.cdap.e2e.pages.locators.CdfConnectionLocators;
import io.cdap.e2e.pages.locators.CdfPluginPropertiesLocators;
import io.cdap.e2e.utils.AssertionHelper;
import io.cdap.e2e.utils.ConstantsUtil;
import io.cdap.e2e.utils.ElementHelper;
import io.cdap.e2e.utils.PageHelper;
import io.cdap.e2e.utils.PluginPropertyUtils;
import io.cdap.e2e.utils.SeleniumDriver;
import io.cdap.e2e.utils.SeleniumHelper;
import io.cdap.e2e.utils.WaitHelper;
import org.junit.Assert;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.io.IOException;

/**
 * Represents Cdf connection page and properties Actions
 */
public class CdfConnectionActions {

  static {
    SeleniumHelper.getPropertiesLocators(CdfPluginPropertiesLocators.class);
    SeleniumHelper.getPropertiesLocators(CdfConnectionLocators.class);
  }

  /**
   * Open Wrangler connections page
   */
  public static void openWranglerConnectionsPage() throws IOException {
    SeleniumDriver.openPage(SeleniumHelper.readParameters(ConstantsUtil.WRANGLER_CONNECTIONS_URL));
    PageHelper.acceptAlertIfPresent();
    WaitHelper.waitForPageToLoad();
  }

  /**
   * Click on the Browser connections button inside Plugin's properties page
   */
  public static void clickBrowseConnectionsButton() {
    ElementHelper.clickOnElement(CdfConnectionLocators.browseConnectionsButton);
  }

  /**
   * Select the connection
   *
   * @param connectionName If connectionName is present in {@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} as key
   *                       then actual connection to select is fetched from it
   *                       else connectionName is selected as it is.
   */
  public static void selectConnection(String connectionName) {
    WebElement connectionRowElement;

    String connectionNameFromPluginPropertiesFile = PluginPropertyUtils.pluginProp(connectionName);
    if (connectionNameFromPluginPropertiesFile == null) {
      connectionRowElement = CdfPluginPropertiesLocators.locateElementWithText(connectionName);
    } else {
      connectionRowElement = CdfPluginPropertiesLocators.locateElementWithText(connectionNameFromPluginPropertiesFile);
    }

    ElementHelper.clickOnElement(connectionRowElement);
    WaitHelper.waitForElementToBeDisplayed(connectionRowElement);
  }

  /**
   * Verify the test connection is successful
   */
  public static void verifyTheTestConnectionIsSuccessful() {
    WaitHelper.waitForElementToBePresent(CdfPluginPropertiesLocators
                                           .locatorOfElementWithText("Successfully connected."));
    AssertionHelper.verifyElementDisplayed(CdfPluginPropertiesLocators
                                             .locateElementWithText("Successfully connected."));
  }

  /**
   * Verify the connection is created
   *
   * @param connectionName If connectionName is present in {@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} as key
   *                       then actual connectionName is fetched from it
   *                       else connectionName param used as it is.
   */
  public static void verifyConnectionIsCreatedSuccessfully(String connectionName) {
    String connectionNameFromPluginPropertiesFile = PluginPropertyUtils.pluginProp(connectionName);
    if (connectionNameFromPluginPropertiesFile == null) {
      WaitHelper.waitForElementToBeDisplayed(CdfPluginPropertiesLocators.locateElementWithText(connectionName));
      return;
    }
    WaitHelper.waitForElementToBeDisplayed(CdfPluginPropertiesLocators
                                             .locateElementWithText(connectionNameFromPluginPropertiesFile));
  }

  /**
   * Wait till data loading for selected connection content completes.
   * Example : For BQ connection if dataset is selected then wait till all table names inside dataset gets loaded.
   *
   * @param timeoutInSeconds
   */
  public static void waitTillConnectionDataLoadingCompletes(long timeoutInSeconds) {
    WaitHelper.waitForElementToBeHidden(CdfConnectionLocators.locatorOfConnectionDataLoadingIndicator,
                                        timeoutInSeconds);
  }

  /**
   * Select data row for connection
   *
   * Based on the connection type - table with rows containing data (i.e.bucket, dataset, table, directory names etc.)
   * will be displayed.
   * To select data for the connection use this method with its name as parameter.
   * Example : For GCS pass actual bucket name as parameter.
   */
  private static void selectConnectionDataRow(String dataRow) {
    waitTillConnectionDataLoadingCompletes (ConstantsUtil.DEFAULT_TIMEOUT_SECONDS);
    ElementHelper.sendKeys(CdfConnectionLocators.searchDirectoryInput, dataRow);
    WaitHelper.waitForTextToBePresentInElementValue(CdfConnectionLocators.searchDirectoryInput, dataRow);
    int attempts = 0;
    while (attempts < 5) {
      try {
        ElementHelper.hoverOverElement(CdfPluginPropertiesLocators.locateElementWithText(dataRow));
        ElementHelper.clickOnElement(CdfPluginPropertiesLocators.locateElementWithText(dataRow));
        break;
      } catch (StaleElementReferenceException e) {
        if (attempts == 4) {
          throw e;
        }
      }
      attempts++;
    }
  }

  /**
   * Select data for connection using name
   *
   * Example :
   * For GCS, table with bucket names will be displayed.
   * To select bucket for the connection use this method with bucket name as parameter.
   *
   * Once bucket name is selected - in next table all directories inside bucket will be displayed.
   * To select directory use this method with directory name as parameter.
   *
   *
   * @param dataName If dataName is present in {@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} as a key
   *                 then actual dataName to select is fetched from it
   *                 else dataName used as it is.
   */
  public static void selectConnectionDataWithName(String dataName) {
    String dataToSelect = PluginPropertyUtils.pluginProp(dataName);
    if (dataToSelect == null) {
      dataToSelect = dataName;
    }
    selectConnectionDataRow(dataToSelect);
  }

  /**
   * Select data rows for connection traversing through directories using provided path
   *
   * Example GCS dataPath: "testdata/GCS_CSV_TEST.csv"
   * First "testdata" directory inside bucket will be selected
   * and then in next datatable, file "GCS_CSV_TEST.csv" will be selected.
   *
   * @param dataPath If dataPath is present in {@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} as a key
   *                 then actual dataPath to select is fetched from it
   *                 else dataPath used as it is.
   */
  public static void selectConnectionDataWithPath(String dataPath) {
    String dataPathToSelect = PluginPropertyUtils.pluginProp(dataPath);
    if (dataPathToSelect == null) {
      dataPathToSelect = dataPath;
    }
    String[] dataPathSplit = dataPathToSelect.split("/");
    for (String dataRow : dataPathSplit) {
      selectConnectionDataRow(dataRow);
    }
  }

  /**
   * Click SELECT button inside connection data row using name
   *
   * Example :
   * For BQ, table with dataset names will be displayed.
   * To click on SELECT button of dataset row use this method with dataset name as parameter.
   *
   * Once SELECT button is clicked, steps to select source/target BQ table will be skipped.
   *
   * @param dataName If dataName is present in {@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} as a key
   *                 then actual dataName to select is fetched from it
   *                 else dataName used as it is.
   */
  public static void clickSelectButtonOfConnectionDataRow(String dataName) {
    String dataToSelect = PluginPropertyUtils.pluginProp(dataName);
    if (dataToSelect == null) {
      dataToSelect = dataName;
    }
    waitTillConnectionDataLoadingCompletes(ConstantsUtil.DEFAULT_TIMEOUT_SECONDS);
    ElementHelper.sendKeys(CdfConnectionLocators.searchDirectoryInput, dataToSelect);
    int attempts = 0;
    while (attempts < 5) {
      try {
        ElementHelper.hoverOverElement(CdfConnectionLocators.locateSelectButtonOfConnectionDataRow(dataToSelect));
        ElementHelper.clickOnElement(CdfConnectionLocators.locateSelectButtonOfConnectionDataRow(dataToSelect));
        break;
      } catch (StaleElementReferenceException e) {
        if (attempts == 4) {
          throw e;
        }
      }
      attempts++;
    }
  }

  /**
   * Verify the connection input datatable is displayed with title
   *
   * @param dataPath If dataPath is present in {@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} as a key
   *                 then actual dataPath is fetched from it
   *                 else dataPath used as it is.
   */
  public static void verifyTheConnectionDataTableDisplayed(String dataPath) {
    String actualDataPath = PluginPropertyUtils.pluginProp(dataPath);
    if (actualDataPath == null) {
      actualDataPath = dataPath;
    }
    String[] dataPathSplit = actualDataPath.split("/");
    String title = dataPathSplit[dataPathSplit.length - 1];
    WaitHelper.waitForElementToBePresent(CdfConnectionLocators.locatorOfDataTableTitle(title));
    WaitHelper.waitForElementToBeDisplayed(CdfConnectionLocators.dataTable);
  }

  /**
   * Click on Create Pipeline button in Wrangler Workspace page
   */
  public static void clickCreatePipelineButton() {
    ElementHelper.clickOnElement(CdfConnectionLocators.createPipelineButton);
  }

  /**
   * Select the type of pipeline to create
   *
   * @param pipelineType If pipelineType is present in {@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} as key
   *                     then actual pipelineType is fetched from it
   *                     else pipelineType used as it is.
   */
  public static void selectPipelineType(String pipelineType) {
    String pipelineTypeToSelect = PluginPropertyUtils.pluginProp(pipelineType);
    if (pipelineTypeToSelect == null) {
      pipelineTypeToSelect = pipelineType;
    }
    ElementHelper.clickOnElement(SeleniumDriver.getDriver().findElement(
      CdfConnectionLocators.locatorOfPipelineTypeButton(pipelineTypeToSelect)));
  }

  /**
   * Expand the connections for provided connection type
   *
   * @param connectionType i.e. BigQuery / GCS / File
   */
  public static void expandConnections(String connectionType) {
    String isExpanded = ElementHelper.getElementAttribute(CdfConnectionLocators.locateConnectionType(connectionType),
                                                          "aria-expanded");
    if (isExpanded.equalsIgnoreCase("false")) {
      ElementHelper.clickOnElement(CdfConnectionLocators.locateConnectionType(connectionType));
    }
  }

  /**
   * Open the connection's action menu
   *
   * @param connectionType i.e. BigQuery / GCS / File
   * @param connectionName If connectionName is present in {@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} as key
   *                       then actual connectionName is fetched from it
   *                       else connectionName param used as it is.
   */
  public static void openConnectionActionMenu(String connectionType, String connectionName) {
    String actualConnectionName = PluginPropertyUtils.pluginProp(connectionName);
    if (actualConnectionName == null) {
      actualConnectionName = connectionName;
    }
    ElementHelper.clickOnElement(
      CdfConnectionLocators.locateConnectionActionMenu(connectionType, actualConnectionName));
  }

  /**
   * Select the action for connection
   *
   * @param connectionType i.e. BigQuery / GCS / File
   * @param connectionName If connectionName is present in {@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} as key
   *                       then actual connectionName is fetched from it
   *                       else connectionName param used as it is.
   * @param action         (Edit/Export/Duplicate/Delete)
   */
  public static void selectConnectionAction(String connectionType, String connectionName, String action) {
    String actualConnectionName = PluginPropertyUtils.pluginProp(connectionName);
    if (actualConnectionName == null) {
      actualConnectionName = connectionName;
    }
    ElementHelper.clickOnElement(
      CdfConnectionLocators.locateConnectionAction(connectionType, actualConnectionName, action));
  }

  /**
   * Verify connection is not present
   *
   * @param connectionType i.e. BigQuery / GCS / File
   * @param connectionName If connectionName is present in {@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} as key
   *                       then actual connectionName is fetched from it
   *                       else connectionName param used as it is.
   */
  public static void verifyConnectionIsNotPresent(String connectionType, String connectionName) {
    String actualConnectionName = PluginPropertyUtils.pluginProp(connectionName);
    if (actualConnectionName == null) {
      actualConnectionName = connectionName;
    }
    WaitHelper.waitForElementToBeHidden(
      CdfConnectionLocators.locatorOfConnection(connectionType, actualConnectionName),
      ConstantsUtil.DEFAULT_TIMEOUT_SECONDS);
    Assert.assertTrue("Connection " + connectionName + " should not be present"
      , !ElementHelper.isElementDisplayed(CdfConnectionLocators.locatorOfConnection(connectionType,
                                                                                    actualConnectionName), 5));
  }
}
