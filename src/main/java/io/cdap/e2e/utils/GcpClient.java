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

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.Table;
import com.google.cloud.bigquery.TableId;
import com.google.cloud.bigquery.TableResult;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * Represents GcpClient
 */
public class GcpClient {

  private static BigQuery bigQueryService = null;

  private static BigQuery getBigQueryService() throws IOException {
    return (null == bigQueryService) ?
      BigQueryOptions.newBuilder().setProjectId(
        SeleniumHelper.readParameters(ConstantsUtil.PROJECT_ID)).build().getService() : bigQueryService;
  }

  public static int countBqQuery(String table) throws IOException, InterruptedException {
    String projectId = SeleniumHelper.readParameters(ConstantsUtil.PROJECT_ID);
    String datasetName = SeleniumHelper.readParameters(ConstantsUtil.DATASET);
    String selectQuery = "SELECT count(*) " + " FROM `" + projectId + "." + datasetName + "." + table + "`";
    return executeQuery(selectQuery);
  }

  //Deleting the table
  public static void dropBqQuery(String table) throws IOException, InterruptedException {
    String projectId = SeleniumHelper.readParameters(ConstantsUtil.PROJECT_ID);
    String datasetName = SeleniumHelper.readParameters(ConstantsUtil.DATASET);
    String dropQuery = "DROP TABLE `" + projectId + "." + datasetName + "." + table + "`";
    executeQuery(dropQuery);
  }

  public static int executeQuery(String query) throws InterruptedException, IOException {
    QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query).build();
    TableResult results = getBigQueryService().query(queryConfig);
    if (results.getTotalRows() > 0) {
      //No of records present in table
      return Integer.parseInt((String) results.getValues().iterator().next().get(0).getValue());
    }
    return 0;
  }

  public static String executeSelectQuery(String query) throws InterruptedException, IOException {
    QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query).build();
    TableResult results = getBigQueryService().query(queryConfig);
    String outputRowValue = StringUtils.EMPTY;
    if (results.getTotalRows() > 0) {
      outputRowValue =  (String) results.getValues().iterator().next().get(0).getValue();
    }
    return outputRowValue;
  }

  public static boolean verifyCmekKey(String tableName, String cmekKey) throws IOException {
    Table table = getBigQueryService().getTable(
      TableId.of(SeleniumHelper.readParameters(ConstantsUtil.PROJECT_ID),
                 SeleniumHelper.readParameters(ConstantsUtil.DATASET), tableName));
    return cmekKey.equals(table.getEncryptionConfiguration().getKmsKeyName());
  }
}
