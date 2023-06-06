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
import io.cdap.e2e.pages.locators.CdfPluginPropertiesLocators;
import io.cdap.e2e.utils.ElementHelper;
import io.cdap.e2e.utils.SeleniumHelper;

import java.io.IOException;

/**
 * Represents CdfBigQueryPropertiesActions
 */
public class CdfBigQueryPropertiesActions {

  static {
    SeleniumHelper.getPropertiesLocators(CdfBigQueryPropertiesLocators.class);
  }

  public static void enterCmekProperty(String cmek) throws IOException {
    ElementHelper.sendKeys(CdfBigQueryPropertiesLocators.cmekKey, cmek);
  }

  public static void enterFilePath(String path) throws InterruptedException, IOException {
    ElementHelper.replaceElementValue(CdfBigQueryPropertiesLocators.serviceFilePath, path);
  }

  public static void enterBigQueryReferenceName(String referenceName) {
    ElementHelper.sendKeys(CdfBigQueryPropertiesLocators.bigQueryReferenceName, referenceName);
  }

  public static void enterProjectId(String projectId) throws IOException {
    ElementHelper.replaceElementValue(CdfBigQueryPropertiesLocators.projectID, projectId);
  }

  public static void enterDatasetProjectId(String projectId) throws IOException {
    ElementHelper.replaceElementValue(CdfBigQueryPropertiesLocators.datasetProjectID, projectId);
  }

  public static void enterBigQueryDataset(String dataset) {
    ElementHelper.sendKeys(CdfBigQueryPropertiesLocators.bigQueryDataSet, dataset);
  }

  public static void enterBigQueryTable(String tableName) {
    ElementHelper.sendKeys(CdfBigQueryPropertiesLocators.bigQueryTable, tableName);
  }

  public static void clickUpdateTable() {
    ElementHelper.clickOnElement(CdfBigQueryPropertiesLocators.updateTable);
  }

  public static void clickTruncatableSwitch() {
    ElementHelper.clickOnElement(CdfBigQueryPropertiesLocators.truncatableSwitch);
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
    ElementHelper.clickOnElement(CdfBigQueryPropertiesLocators.getSchemaButton);
  }

  public static void enterTemporaryBucketName(String bucket) throws IOException {
    ElementHelper.replaceElementValue(CdfBigQueryPropertiesLocators.temporaryBucketName, bucket);
  }

  public static void enterPartitionField(String partitionField) {
    ElementHelper.sendKeys(CdfBigQueryPropertiesLocators.partitionField, partitionField);
  }

  public static void enterChunkSize(String chunkSize) {
    ElementHelper.sendKeys(CdfBigQueryPropertiesLocators.chunkSize, chunkSize);
  }

  public static void selectPartitioningType(String option) {
    ElementHelper.selectRadioButton(CdfBigQueryPropertiesLocators.partitioningType(option));
  }

  public static void enterRangeStart(String rangeStart) {
    ElementHelper.sendKeys(CdfBigQueryPropertiesLocators.rangeStart, rangeStart);
  }

  public static void enterRangeEnd(String rangeEnd) {
    ElementHelper.sendKeys(CdfBigQueryPropertiesLocators.rangeEnd, rangeEnd);
  }

  public static void enterRangeInterval(String rangeInterval) {
    ElementHelper.sendKeys(CdfBigQueryPropertiesLocators.rangeInterval, rangeInterval);
  }

  public static void toggleRequirePartitionFilter() {
    ElementHelper.clickOnElement(CdfBigQueryPropertiesLocators.requirePartitionFilter);
  }

  public static void enterClusteringOrder(String clusteringOrder) {
    ElementHelper.sendKeys(CdfBigQueryPropertiesLocators.clusteringOrder, clusteringOrder);
  }

  public static void toggleEnableQueryingViews() {
    ElementHelper.clickOnElement(CdfBigQueryPropertiesLocators.enableQueryingViews); }

  public static void enterViewMaterializationProject(String viewProject) {
    ElementHelper.sendKeys(CdfBigQueryPropertiesLocators.viewMaterializationProject, viewProject);
  }

  public static void enterViewMaterializationDataset(String viewDataset) {
    ElementHelper.sendKeys(CdfBigQueryPropertiesLocators.viewMaterializationDataset, viewDataset);
  }

  public static void enterTableKey(String tableKey) {
    ElementHelper.sendKeys(CdfBigQueryPropertiesLocators.sinkTableKey, tableKey);
  }

  public static void enterDedupeByKey(String dedupeKey) {
    ElementHelper.sendKeys(CdfBigQueryPropertiesLocators.sinkDedupeKey, dedupeKey);
  }

  public static void selectDedupeByFunction(String function) {
    ElementHelper.selectDropdownOption(CdfBigQueryPropertiesLocators.dedupeFunctionName,
                                       CdfPluginPropertiesLocators.locateDropdownListItem(function));
  }
}
