package io.cdap.e2e.utils;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;
import java.io.IOException;


public class GcpClient {

    public  int countBqQuery(String table) throws IOException, InterruptedException {

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
    public  void dropBqQuery(String table) throws IOException, InterruptedException {

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

    public int executeQuery(String query) throws InterruptedException, IOException {
        BigQuery bigquery =  BigQueryOptions.newBuilder().setProjectId(SeleniumHelper.readParameters("Project-ID"))
                .build().getService();
        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query).build();
       TableResult results = bigquery.query(queryConfig);
       if (results.getTotalRows()>0) {
         return Integer.parseInt((String) results.getValues().iterator().next().get(0).getValue());
       }
        return 0;
    }
}

