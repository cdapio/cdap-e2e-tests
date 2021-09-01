Feature: Demo1

  @TC-Demo-
  Scenario: User is able to Login and confirm data is getting transferred from GCS to BigQuery
    Given Open Datafusion Project to configure pipeline1
    When Source is GCS bucket1
    When Target is BigQuery1
    Then Link Source and Sink to establish connection1
    Then Enter the GCS Properties with "@TC-Demo-1_GCS" GCS bucket1
    Then Close the GCS Properties1
