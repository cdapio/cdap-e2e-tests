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
import com.google.cloud.bigquery.TableResult;

import java.io.IOException;

/**
 * Represents GcpClient
 */
public class GcpClient {

    public int countBqQuery(String table) throws IOException, InterruptedException {

        String projectId = SeleniumHelper.readParameters("Project-ID");
        String datasetName = SeleniumHelper.readParameters("Data-Set");
        String selectQuery = "SELECT count(*) "
          + " FROM `"
          + projectId
          + "."
          + datasetName
          + "."
          + table
          + "`";
        return executeQuery(selectQuery);
    }

    //Deleting the table
    public void dropBqQuery(String table) throws IOException, InterruptedException {

        String projectId = SeleniumHelper.readParameters("Project-ID");
        String datasetName = SeleniumHelper.readParameters("Data-Set");
        String dropQuery = "DROP TABLE `"
          + projectId
          + "."
          + datasetName
          + "."
          + table
          + "`";
        executeQuery(dropQuery);
    }

    private int executeQuery(String query) throws InterruptedException, IOException {
        BigQuery bigquery = BigQueryOptions.newBuilder().setProjectId(SeleniumHelper.readParameters("Project-ID"))
          .build().getService();
        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query).build();
        TableResult results = bigquery.query(queryConfig);
        if (results.getTotalRows() > 0) {
            //No of records present in table
            return Integer.parseInt((String) results.getValues().iterator().next().get(0).getValue());
        }
        return 0;
    }
}

