package io.cdap.e2e.pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CdfStudioLocators {
    @FindBy(how= How.XPATH,using="//*[@data-cy=\"plugin-GCSFile-batchsource\"]")
    public static WebElement source;

    @FindBy(how= How.XPATH,using="//*[@data-cy=\"plugin-GCSFile-batchsource\"]")
    public static WebElement gcsBucket;

    @FindBy(how= How.XPATH,using="//*[@data-cy='plugin-BigQueryTable-batchsink']")
    public static WebElement bigQueryObject;

    @FindBy(how= How.XPATH,using="//*[text()='Sink ']")
    public static WebElement sink;

    @FindBy(how= How.XPATH,using="//*[contains(@class,'plugin-endpoint_SAP-ODP')]")
    public static WebElement fromSAPODP;

    @FindBy(how= How.XPATH,using="//*[contains(@class,'plugin-endpoint_GCS')]")
    public static WebElement fromGCS;

    @FindBy(how= How.XPATH,using="//*[@title='BigQuery']")
    public static WebElement toBigQiery;

    @FindBy(how= How.XPATH,using="//*[@title=\"BigQuery\"]//following-sibling::div")
    public static WebElement bigQueryProperties;

    @FindBy(how= How.XPATH,using="//*[@data-cy='pipeline-preview-btn']")
    public static WebElement previewButton;

    @FindBy(how= How.XPATH,using="//*[@class='fa fa-play text-success']")
    public static WebElement runButton;

    @FindBy(how= How.XPATH,using="//*[contains(text(),'Preview')]")
    public static WebElement preview;

    @FindBy(how= How.XPATH,using="//*[@class='ng-binding ng-scope']")
    public static WebElement bannerMssge;

    @FindBy(how= How.XPATH,using="//*[@class=\"pipeline-name ng-binding ng-scope placeholder\"]")
    public static WebElement pipelineName;

    @FindBy(how= How.XPATH,using="//*[@id=\"pipeline-name-input\"]")
    public static WebElement pipelineNameIp;

    @FindBy(how= How.XPATH,using="//*[@class=\"btn btn-primary save-button\"]")
    public static WebElement pipelineSave;

    @FindBy(how= How.XPATH,using="//*[@data-cy=\"deploy-pipeline-btn\"]")
    public static WebElement pipelineDeploy;

    @FindBy(how = How.XPATH, using ="//*[@data-cy='preview-configure-run-btn']")
    public static WebElement configRun;

}



