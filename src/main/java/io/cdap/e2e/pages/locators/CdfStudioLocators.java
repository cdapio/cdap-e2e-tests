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

package io.cdap.e2e.pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Represents CdfStudioLocators
 */
public class CdfStudioLocators {
  @FindBy(how = How.XPATH, using = "//*[@data-cy=\"plugin-GCSFile-batchsource\"]")
  public static WebElement source;

  @FindBy(how = How.XPATH, using = "//*[@data-cy=\"plugin-GCSFile-batchsource\"]")
  public static WebElement gcsBucket;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='plugin-BigQueryTable-batchsink']")
  public static WebElement bigQueryObject;

  @FindBy(how = How.XPATH, using = "//*[text()='Sink ']")
  public static WebElement sink;

  @FindBy(how = How.XPATH, using = "//*[contains(@class,'plugin-endpoint_SAP-ODP')]")
  public static WebElement fromSAPODP;

  @FindBy(how = How.XPATH, using = "//*[contains(@class,'plugin-endpoint_GCS')]")
  public static WebElement fromGCS;

  @FindBy(how = How.XPATH, using = "//*[@title='BigQuery']")
  public static WebElement toBigQiery;

  @FindBy(how = How.XPATH, using = "//*[@title=\"BigQuery\"]//following-sibling::div")
  public static WebElement bigQueryProperties;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='pipeline-preview-btn']")
  public static WebElement previewButton;

  @FindBy(how = How.XPATH, using = "//*[@class='fa fa-play text-success']")
  public static WebElement runButton;

  @FindBy(how = How.XPATH, using = "//*[contains(text(),'Preview')]")
  public static WebElement preview;

  @FindBy(how = How.XPATH, using = "//*[@class='ng-binding ng-scope']")
  public static WebElement bannerMssge;

  @FindBy(how = How.XPATH, using = "//*[@class=\"pipeline-name ng-binding ng-scope placeholder\"]")
  public static WebElement pipelineName;

  @FindBy(how = How.XPATH, using = "//*[@id=\"pipeline-name-input\"]")
  public static WebElement pipelineNameIp;

  @FindBy(how = How.XPATH, using = "//*[@class=\"btn btn-primary save-button\"]")
  public static WebElement pipelineSave;

  @FindBy(how = How.XPATH, using = "//*[@data-cy=\"deploy-pipeline-btn\"]")
  public static WebElement pipelineDeploy;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='preview-configure-run-btn']")
  public static WebElement configRun;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='valium-banner-hydrator']")
  public static WebElement pipelineSaveSuccessBanner;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='plugin-BigQueryTable-batchsource']")
  public static WebElement sourceBigQuery;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='plugin-GCS-batchsink']")
  public static WebElement gcs;

  @FindBy(how = How.XPATH, using = "//*[contains(@class,'plugin-endpoint_BigQuery')]")
  public static WebElement fromBigQuery;

  @FindBy(how = How.XPATH, using = "//*[@title='GCS']")
  public static WebElement toGCS;

  @FindBy(how = How.XPATH, using = "//*[@title=\"GCS\"]//following-sibling::div")
  public static WebElement gcsProperties;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='valium-banner-hydrator']")
  public static WebElement previewSuccessBanner;

  @FindBy(how = How.XPATH, using = "//*[@title='BigQuery2']")
  public static WebElement toBigQuery2;

  @FindBy(how = How.XPATH, using = "//*[@title='BigQuery2']//following-sibling::div")
  public static WebElement bigQueryProperties2;

  @FindBy(how = How.XPATH, using = "//*[@data-cy='valium-banner-hydrator']//span")
  public static WebElement pipelineFail;
}
