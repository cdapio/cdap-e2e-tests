Feature: Demo1

  @TC-Demo-1
  Scenario: User is able to Login and confirm data is getting transferred from GCS to BigQuery
    Given Open Datafusion Project to configure pipeline
    When Source is GCS bucket
    When Target is BigQuery
    Then Link Source and Sink to establish connection
    Then Enter the GCS Properties with "@TC-Demo-1_GCS" GCS bucket
    Then Close the GCS Properties
    Then Enter the BigQuery Properties for table "DemoCheck1"
    Then Close the BigQuery Properties
    Then Save and Deploy Pipeline
    Then Run the Pipeline in Runtime
    Then Wait till pipeline is in running state
    Then Verify the pipeline status is "Succeeded"
    Then Open Logs
    Then validate successMessage is displayed
    Then Open "BigQuery" link to login
    Then enter the Query to check the count of table created "DemoCheck1"
    Then capture the count
