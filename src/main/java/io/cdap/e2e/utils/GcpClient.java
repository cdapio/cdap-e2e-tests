package io.cdap.e2e.utils;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;
import java.io.IOException;


public class GcpClient {
    public static  String query;
    public static int noOfRecords;

    public static int countBqQuery(String table) throws IOException, InterruptedException {

        String projectId = SeleniumHelper.readParameters("Project-ID");
        String datasetName = SeleniumHelper.readParameters("Data-Set");
         query = "SELECT count(*) "
                + " FROM `"
                + projectId
                + "."
                + datasetName
                + "."
                + table
                + "`";
         createBigQueryClient();
        return noOfRecords;
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
        createBigQueryClient();
    }

    public static void createBigQueryClient() throws InterruptedException, IOException {
        BigQuery bigquery =  BigQueryOptions.newBuilder().setProjectId(SeleniumHelper.readParameters("Project-ID"))
                .build().getService();
        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query).build();
       TableResult results = bigquery.query(queryConfig);
        noOfRecords=Integer.parseInt((String)results.getValues().iterator().next().get(0).getValue());
    }
}

