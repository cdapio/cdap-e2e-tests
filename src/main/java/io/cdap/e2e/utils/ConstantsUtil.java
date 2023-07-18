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

/**
 * Collection of Constants
 */
public class ConstantsUtil {
  public static final String AUTOMATION_TEST = "automation_test";
  public static final String PROJECT_ID = "projectId";
  public static final String DATASET = "dataset";
  public static final String TABLE = "tableName";
  public static final String SAMPLE_SIZE = "1000";
  public static final String DELIMITER = "delimiter@";
  public static final String VALUE = "value";
  public static final String OFFSET = "offset";
  public static final String JS_CLICK = "arguments[0].click();";
  public static final String JS_SCROLL_INTO_VIEW = "arguments[0].scrollIntoView(true);";
  public static final String COLOR = "color";
  public static final String GUSERNAME = "gLoginUsername";
  public static final String GPAZWRD = "gPassword";
  public static final String HDFUSERNAME = "hdfUsername";
  public static final String HDFPAZWRD = "hdfPassword";
  public static final String CDFUSERNAME = "cdfUsername";
  public static final String CDFPASSWORD = "cdfPassword";
  public static final String TESTONCDF = "testOnCdf";
  public static final String CDF_TEST_ACCOUNT_NAME = "CloudDataFution Automation";
  public static final String CDFURL = "cdfurl";
  public static final String WRANGLER_CONNECTIONS_URL = "cdfConnectionsUrl";
  public static final String REPLICATION_URL = "replicationUrl";
  public static final String TESTONHDF = "testOnHdf";
  public static final String BATCH = "Batch";
  public static final String REALTIME = "Realtime";
  public static final String ERROR = "ERROR";
  public static final String SINK_PLUGIN_GROUPNAME = "Sink";
  public static final int ONE = 1;
  public static final String PASS_CASE = "First case passed";
  public static final String ERROR_MSG_COLOR = "#a40403";
  public static final String ERROR_MSG_MANDATORY = "Required property 'PROPERTY' has no value.";
  public static final String DEFAULT_PLUGIN_PROPERTIES_FILE = "pluginParameters.properties";
  public static final String DEFAULT_ERROR_PROPERTIES_FILE = "errorMessage.properties";
  public static final String DEFAULT_DATACY_ATTRIBUTES_FILE = "pluginDataCyAttributes.properties";
  public static final String VALIDATION_SUCCESS_MESSAGE = "validationSuccessMessage";
  public static final String VALIDATION_ERROR_MESSAGE = "validationErrorMessage";
  public static final String INVALID_CREDENTIALS_ERROR_MESSAGE = "invalid.property.credentials";
  public static final String PROPERTIES_TAB = "Properties";
  public static final String PREVIEW_TAB = "Preview";
  public static final String DOCUMENTATION_TAB = "Documentation";
  public static final String SCREENSHOT_FOR_ALL_STEPS = "screenshotForAllSteps";
  public static final int MAX_RETRY_ATTEMPTS = 5;
  public static final String LOGS_SEPARATOR_MESSAGE = "---------------------------------------------------------" +
    "------------------------------MESSAGE----------------------------------------------------------------------" +
    "-----------------";
  /* TODO: Remove FIRST_PLUGIN_IN_LIST constant once https://cdap.atlassian.net/browse/CDAP-18862 is fixed */
  public static final String FIRST_PLUGIN_IN_LIST = "BigQuery";
  /**
   * IMPLICIT_TIMEOUT_SECONDS: Selenium driver will wait and retry for the specified time before failing.
   * It should be on the lower side as actions anticipating larger waits should be handled with pre-defined explicit
   * waits.
   * For external waits examples, refer: {@link WaitHelper}
   */
  public static final int IMPLICIT_TIMEOUT_SECONDS = 30;
  /**
   * DEFAULT_TIMEOUT_SECONDS: To be used in external wait helpers defined in {@link WaitHelper}
   */
  public static final int DEFAULT_TIMEOUT_SECONDS = 180;
  /**
   * PAGE_LOAD_TIMEOUT_SECONDS: To be used as Selenium driver's default page load timeout
   */
  public static final int PAGE_LOAD_TIMEOUT_SECONDS = 50;
  /**
   * SMALL_TIMEOUT_SECONDS: To be used as a small static wait (only if needed)
   */
  public static final int SMALL_TIMEOUT_SECONDS = 5;
  /**
   * MEDIUM_TIMEOUT_SECONDS: To be used as a medium static wait (only if needed)
   */
  public static final int MEDIUM_TIMEOUT_SECONDS = 10;
  /**
   * PIPELINE_DEPLOY_TIMEOUT_SECONDS: To be used as a timeout for deploying the pipeline
   */
  public static final int PIPELINE_DEPLOY_TIMEOUT_SECONDS = 120;
  /**
   * PIPELINE_PREVIEW_TIMEOUT_SECONDS: To be used as a timeout for Pipeline Preview
   */
  public static final int PIPELINE_PREVIEW_TIMEOUT_SECONDS = 600;

  /**
   * PIPELINE_RUN_TIMEOUT_SECONDS: To be used as a timeout for Pipeline Runs
   */
  public static final int PIPELINE_RUN_TIMEOUT_SECONDS = 900;

  public static final int PIPELINE_REFRESH_TIMEOUT_SECONDS = 120;
}
