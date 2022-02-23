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

import io.cdap.e2e.pages.locators.CdfBigQueryPropertiesLocators;
import io.cdap.e2e.pages.locators.CdfStudioLocators;
import io.cdap.e2e.utils.ElementHelper;
import io.cdap.e2e.utils.PluginPropertyUtils;
import io.cdap.e2e.utils.SeleniumHelper;

import java.io.IOException;

import static io.cdap.e2e.utils.ConstantsUtil.AUTOMATION_TEST;
import static io.cdap.e2e.utils.ConstantsUtil.DATASET;
import static io.cdap.e2e.utils.ConstantsUtil.ONE;
import static io.cdap.e2e.utils.ConstantsUtil.PROJECT_ID;

/**
 * Represents CdfBigQueryPropertiesActions
 */
public class CdfBigQueryPropertiesActions {
  public static CdfBigQueryPropertiesLocators cdfBigQueryPropertiesLocators;

  static {
    cdfBigQueryPropertiesLocators = SeleniumHelper.getPropertiesLocators(CdfBigQueryPropertiesLocators.class);
  }

  /**
   * @deprecated Call individual actions as per test scenario in step design file.
   */
  @Deprecated
  public static void enterBigQueryProperties(String tableProp) throws InterruptedException, IOException {
    CdfStudioLocators.bigQueryProperties.click();
    CdfBigQueryPropertiesLocators.bigQueryReferenceName.sendKeys(AUTOMATION_TEST);
    SeleniumHelper.replaceElementValue(CdfBigQueryPropertiesLocators.projectID,
      PluginPropertyUtils.pluginProp(PROJECT_ID));
    CdfBigQueryPropertiesLocators.datasetProjectID.sendKeys(PluginPropertyUtils.pluginProp(PROJECT_ID));
    CdfBigQueryPropertiesLocators.bigQueryDataSet.sendKeys(PluginPropertyUtils.pluginProp(DATASET));
    CdfBigQueryPropertiesLocators.bigQueryTable.sendKeys(tableProp);
    CdfBigQueryPropertiesLocators.updateTable.click();
    CdfBigQueryPropertiesLocators.truncatableSwitch.click();
    CdfBigQueryPropertiesLocators.validateBttn.click();
    SeleniumHelper.waitElementIsVisible(CdfBigQueryPropertiesLocators.textSuccess, ONE);
  }

  public static void enterCmekProperty(String cmek) throws IOException {
    CdfBigQueryPropertiesLocators.cmekKey.sendKeys(cmek);
  }

  public static void enterFilePath(String path) throws InterruptedException, IOException {
    ElementHelper.replaceElementValue(CdfBigQueryPropertiesLocators.serviceFilePath, path);
  }

  public static void enterBigQueryReferenceName(String referenceName) {
    CdfBigQueryPropertiesLocators.bigQueryReferenceName.sendKeys(referenceName);
  }

  public static void enterProjectId(String projectId) throws IOException {
    ElementHelper.replaceElementValue(CdfBigQueryPropertiesLocators.projectID, projectId);
  }

  public static void enterDatasetProjectId(String projectId) throws IOException {
    ElementHelper.replaceElementValue(CdfBigQueryPropertiesLocators.datasetProjectID, projectId);
  }

  public static void enterBigQueryDataset(String dataset) {
    CdfBigQueryPropertiesLocators.bigQueryDataSet.sendKeys(dataset);
  }

  public static void enterBigQueryTable(String tableName) {
    CdfBigQueryPropertiesLocators.bigQueryTable.sendKeys(tableName);
  }

  public static void clickUpdateTable() {
    CdfBigQueryPropertiesLocators.updateTable.click();
  }

  public static void clickTruncatableSwitch() {
    CdfBigQueryPropertiesLocators.truncatableSwitch.click();
  }

  public static void enterPartitionStartDate(String partitionStartDate) throws IOException {
    ElementHelper.replaceElementValue(CdfBigQueryPropertiesLocators.partitionStartDate, partitionStartDate);
  }

  public static void enterPartitionEndDate(String partitionEndDate) throws IOException {
    ElementHelper.replaceElementValue(CdfBigQueryPropertiesLocators.partitionEndDate, partitionEndDate);
  }

  public static void enterFilter(String filter) throws IOException {
    ElementHelper.replaceElementValue(CdfBigQueryPropertiesLocators.filter, filter);
  }

  public static void getSchema() {
    CdfBigQueryPropertiesLocators.getSchemaButton.click();
  }

  public static void enableQueryingViews() {
    CdfBigQueryPropertiesLocators.enableQueryingViews.click();
  }

  public static void viewMaterializationProject(String projectId) throws IOException {
    ElementHelper.replaceElementValue(CdfBigQueryPropertiesLocators.viewMaterializationProject, projectId);
  }

  public static void viewMaterializationDataset(String dataset) throws IOException {
    ElementHelper.replaceElementValue(CdfBigQueryPropertiesLocators.viewMaterializationDataset, dataset);
  }

  public static void enterTemporaryBucketName(String bucket) throws IOException {
    ElementHelper.replaceElementValue(CdfBigQueryPropertiesLocators.temporaryBucketName, bucket);
  }

  /**
   * @deprecated Use either {@link io.cdap.e2e.utils.CdfHelper#openSinkPluginPreviewData(String)}
   * or {@link io.cdap.e2e.utils.CdfHelper#openSourcePluginPreviewData(String)} as per plugin type.
   */
  @Deprecated
  public static void clickPreviewData() {
    SeleniumHelper.waitAndClick(CdfBigQueryPropertiesLocators.previewData);
  }
}
