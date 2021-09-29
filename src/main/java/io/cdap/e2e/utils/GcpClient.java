package io.cdap.e2e.utils;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;
import java.io.IOException;

public class GcpClient {

    public static int countBqQuery(String table) throws IOException, InterruptedException {

        String projectId = SeleniumHelper.readParameters("Project-ID");
        String datasetName = SeleniumHelper.readParameters("Data-Set");
        String query = "SELECT count(*) "
                + " FROM `"
                + projectId
                + "."
                + datasetName
                + "."
                + table
                + "`";
        BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query).build();
        TableResult results = bigquery.query(queryConfig);
        return Integer.parseInt((String) results.getValues().iterator().next().get(0).getValue());
    }
    //Deleting the table
    public static void dropBqQuery(String table) throws IOException, InterruptedException {

        String projectId = SeleniumHelper.readParameters("Project-ID");
        String datasetName = SeleniumHelper.readParameters("Data-Set");
        String query = "DROP TABLE `"
                + projectId
                + "."
                + datasetName
                + "."
                + table
                + "`";

        BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query).build();
        TableResult results = bigquery.query(queryConfig);


    }
}

