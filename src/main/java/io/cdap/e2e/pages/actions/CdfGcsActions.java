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

package io.cdap.e2e.pages.actions;

import io.cdap.e2e.pages.locators.CdfGCSLocators;
import io.cdap.e2e.pages.locators.CdfPluginPropertiesLocators;
import io.cdap.e2e.utils.ConstantsUtil;
import io.cdap.e2e.utils.ElementHelper;
import io.cdap.e2e.utils.PluginPropertyUtils;
import io.cdap.e2e.utils.SeleniumHelper;

import java.io.IOException;
import java.util.UUID;

/**
 * Represents CdfGcsActions
 */
public class CdfGcsActions {

  static {
    SeleniumHelper.getPropertiesLocators(CdfGCSLocators.class);
  }

  public static void enterReferenceName() {
    ElementHelper.sendKeys(CdfGCSLocators.referenceName, UUID.randomUUID().toString());
  }

  public static void enterProjectId() throws IOException {
    ElementHelper.replaceElementValue(CdfGCSLocators.projectID,
                                      PluginPropertyUtils.pluginProp(ConstantsUtil.PROJECT_ID));
  }

  public static void getGcsBucket(String bucket) {
    ElementHelper.sendKeys(CdfGCSLocators.gcsPath, bucket);
  }

  public static void closeButton() {
    ElementHelper.clickOnElement(CdfGCSLocators.closeButton);
  }

  public static void skipHeader() {
    ElementHelper.clickOnElement(CdfGCSLocators.skipHeader);
  }

  public static void getSchema() {
    ElementHelper.clickOnElement(CdfGCSLocators.getSchemaButton);
  }

  public static void selectFormat(String formatType) throws InterruptedException {
    ElementHelper.selectDropdownOption(CdfGCSLocators.format,
                                       CdfPluginPropertiesLocators.locateDropdownListItem(formatType));
  }

  public static void clickValidateButton() {
    ElementHelper.clickOnElement(CdfGCSLocators.validateBtn);
  }

  public static void selectFileEncoding(String encoding) {
    ElementHelper.selectDropdownOption(CdfGCSLocators.fileEncoding,
                                       CdfPluginPropertiesLocators.locateDropdownListItem(encoding));
  }

  public static void enterMaxSplitSize(String maxSplitSize) {
    ElementHelper.sendKeys(CdfGCSLocators.maxSplitSize, maxSplitSize);
  }

  public static void enterMinSplitSize(String minSplitSize) {
    ElementHelper.sendKeys(CdfGCSLocators.minSplitSize, minSplitSize);
  }

  public static void enterRegexPath(String regexPath) {
    ElementHelper.sendKeys(CdfGCSLocators.regexPath, regexPath);
  }

  public static void enterPathField(String pathField) {
    ElementHelper.sendKeys(CdfGCSLocators.pathField, pathField);
  }

  public static void enterOverride(String override) {
    ElementHelper.sendKeys(CdfGCSLocators.override, override);
  }

  public static void clickOverrideDataType(String dataType) {
    ElementHelper.selectDropdownOption(CdfGCSLocators.overrideDataType,
                                       CdfPluginPropertiesLocators.locateDropdownListItem(dataType));
  }

  public static void enterDelimiterField(String delimiter) {
    ElementHelper.sendKeys(CdfGCSLocators.delimiterField, delimiter);
  }

  public static void enterSampleSize(String sampleSize) {
    ElementHelper.replaceElementValue(CdfGCSLocators.samplesize, sampleSize);
  }

  public static void enterPathSuffix(String pathSuffix) {
    ElementHelper.replaceElementValue(CdfGCSLocators.pathSuffix, pathSuffix);
  }

  public static void toggleWriteHeader() {
    ElementHelper.clickOnElement(CdfGCSLocators.writeHeaderSwitch);
  }

  public static void enterLocation(String location) {
    ElementHelper.replaceElementValue(CdfGCSLocators.location, location);
  }

  public static void selectContentType(String contentType) {
    ElementHelper.selectDropdownOption(CdfGCSLocators.contentType,
                                       CdfPluginPropertiesLocators.locateDropdownListItem(contentType));
  }

  public static void enterEncryptionKeyName(String cmek) {
    ElementHelper.sendKeys(CdfGCSLocators.cmekKey, cmek);
  }

  public static void enterOutputFilePrefix(String filePrefix) {
    ElementHelper.sendKeys(CdfGCSLocators.outputFilePrefix, filePrefix);
  }

  public static void enterFileSystemProperties(String fileSystemProperties) {
    // ElementHelper.sendKeys is avoided as FileSystemProperties textarea has style=opacity: 0
    // Selenium waiting for visibility throws exception.
    CdfGCSLocators.fileSystemProperties.sendKeys(fileSystemProperties);
  }

  public static void selectRecursive(String value) {
    ElementHelper.selectRadioButton(CdfGCSLocators.recursive(value));
  }

  public static void selectPathFilenameOnly(String value) {
    ElementHelper.selectRadioButton(CdfGCSLocators.pathFilenameOnly(value));
  }

  public static void clickFileSystemPropertiesTidyButton() {
    ElementHelper.clickOnElement(CdfGCSLocators.fileSystemPropertiesTidyButton);
  }
}
