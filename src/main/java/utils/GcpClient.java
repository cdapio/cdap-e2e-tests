package utils;

import com.google.cloud.bigquery.*;
import java.io.IOException;

public class GcpClient {

    public static int countBqQuery(String table) throws IOException {

        String projectId = SeleniumHelper.readParameters("Project-ID");
        String datasetName = SeleniumHelper.readParameters("Data-Set");
        String tableName = SeleniumHelper.readParameters(table);
        String query = "SELECT count(*) "
                + " FROM `"
                + projectId
                + "."
                + datasetName
                + "."
                + tableName
                + "`";
        return query(query);
    }

    public static void dropBqQuery(String table) throws IOException {

        String projectId = SeleniumHelper.readParameters("Project-ID");
        String datasetName = SeleniumHelper.readParameters("Data-Set");
        ;
        String tableName = SeleniumHelper.readParameters(table);
        String query = "DROP TABLE `"
                + projectId
                + "."
                + datasetName
                + "."
                + tableName
                + "`";

    }

    public static int query(String query) {
        try {
            // Initialize client that will be used to send requests. This client only needs to be created
            // once, and can be reused for multiple requests.
            BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
            QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query).build();
            TableResult results = bigquery.query(queryConfig);
            System.out.println("Query performed successfully.");
            return Integer.parseInt((String) results.getValues().iterator().next().get(0).getValue());
        } catch (BigQueryException | InterruptedException e) {
            System.out.println("Query not performed \n" + e.toString());
        }
        return 0;
    }
}

