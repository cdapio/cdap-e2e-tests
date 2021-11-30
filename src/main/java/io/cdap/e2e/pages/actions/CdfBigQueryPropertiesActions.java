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
import io.cdap.e2e.utils.SeleniumHelper;

import java.io.IOException;
import java.util.UUID;

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

  public static void enterBigQueryProperties(String tableProp) throws InterruptedException, IOException {
    CdfStudioLocators.bigQueryProperties.click();
    CdfBigQueryPropertiesLocators.bigQueryReferenceName.sendKeys(AUTOMATION_TEST);
    CdfBigQueryPropertiesLocators.datasetProjectId.sendKeys(SeleniumHelper.readParameters(PROJECT_ID));
    SeleniumHelper.replaceElementValue
      (CdfBigQueryPropertiesLocators.projectID, SeleniumHelper.readParameters(PROJECT_ID));
    CdfBigQueryPropertiesLocators.bigQueryDataSet.sendKeys(SeleniumHelper.readParameters(DATASET));
    CdfBigQueryPropertiesLocators.bigQueryTable.sendKeys(tableProp);
    CdfBigQueryPropertiesLocators.updateTable.click();
    CdfBigQueryPropertiesLocators.truncatableSwitch.click();
    CdfBigQueryPropertiesLocators.validateBttn.click();
    SeleniumHelper.waitElementIsVisible(CdfBigQueryPropertiesLocators.textSuccess, ONE);
  }

  public static void enterCmekProperty(String prop) throws IOException {
    CdfStudioLocators.bigQueryProperties.click();
    CdfBigQueryPropertiesLocators.cmekKey.sendKeys(SeleniumHelper.readParameters(prop));
  }

  public static void clickBigQueryProperties() {
    CdfStudioLocators.bigQueryProperties.click();
  }

  public static void enterBigQueryReferenceName(String referenceName) {
    CdfBigQueryPropertiesLocators.bigQueryReferenceName.sendKeys(referenceName);
  }

  public static void enterProjectId(String projectId) throws IOException {
    SeleniumHelper.replaceElementValue(CdfBigQueryPropertiesLocators.projectID, projectId);
  }

  public static void enterDatasetProjectId(String projectId) throws IOException {
    SeleniumHelper.replaceElementValue(CdfBigQueryPropertiesLocators.datasetProjectId, projectId);
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

  public static void clickValidateButton() {
    CdfBigQueryPropertiesLocators.validateBttn.click();
  }

  public static void enterReferenceName() {
    CdfBigQueryPropertiesLocators.referenceName.sendKeys(UUID.randomUUID().toString());
  }

  public static void enterDataset(String dataset) throws IOException {
    SeleniumHelper.replaceElementValue(CdfBigQueryPropertiesLocators.dataset, dataset);
  }

  public static void enterTable(String tableName) throws IOException {
    SeleniumHelper.replaceElementValue(CdfBigQueryPropertiesLocators.table, tableName);
  }

  public static void enterPartitionStartDate(String partitionStartDate) throws IOException {
    SeleniumHelper.replaceElementValue(CdfBigQueryPropertiesLocators.partitionStartDate, partitionStartDate);
  }

  public static void enterPartitionEndDate(String partitionEndDate) throws IOException {
    SeleniumHelper.replaceElementValue(CdfBigQueryPropertiesLocators.partitionEndDate, partitionEndDate);
  }

  public static void enterFilter(String filter) throws IOException {
    SeleniumHelper.replaceElementValue(CdfBigQueryPropertiesLocators.filter, filter);
  }

  public static void getSchema() {
    CdfBigQueryPropertiesLocators.getSchemaButton.click();
  }

  public static void closeButton() {
    SeleniumHelper.waitElementIsVisible(CdfBigQueryPropertiesLocators.closeButton, 5);
    CdfBigQueryPropertiesLocators.closeButton.click();
  }

  public static void enableQueryingViews() {
    CdfBigQueryPropertiesLocators.enableQueryingViews.click();
  }

  public static void viewMaterializationProject(String projectId) throws IOException {
    SeleniumHelper.replaceElementValue(CdfBigQueryPropertiesLocators.viewMaterializationProject, projectId);
  }

  public static void viewMaterializationDataset(String dataset) throws IOException {
    SeleniumHelper.replaceElementValue(CdfBigQueryPropertiesLocators.viewMaterializationDataset, dataset);
  }

  public static void clickPreviewData() {
    SeleniumHelper.waitElementIsVisible(CdfBigQueryPropertiesLocators.previewData);
    CdfBigQueryPropertiesLocators.previewData.click();
  }
}
