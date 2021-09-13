package stepsDesign;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;
import java.util.UUID;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.actions.*;
import pages.locators.CdfStudioLocators;
import utils.SeleniumDriver;
import utils.SeleniumHelper;

import static utils.RemoteClass.createDriverFromSession;

public class GCSBasicDemo {
    static int i=0;

    @Given("Open Datafusion Project to configure pipeline")
    public void openDatafusionProjectToConfigurePipeline() throws IOException, InterruptedException {

        RemoteWebDriver driver = createDriverFromSession(SeleniumDriver.session(),SeleniumDriver.url);

        SeleniumDriver.waitForPageToLoad();
        if (i==0){
            SeleniumDriver.openPage(SeleniumHelper.readParameters("CDFURL"));
        }
        else {
            SeleniumDriver.openPage(SeleniumHelper.readParameters("CDFURL"));
            SeleniumDriver.getDriver().switchTo().alert().accept();
        }
        i++;
        SeleniumDriver.waitForPageToLoad();
    }

    @When("Source is GCS bucket")
    public void sourceIsGCSBucket() throws InterruptedException {
        CdfStudioActions.selectGCS();
    }


    @When("Target is BigQuery")
    public void targetIsBigQuery() {
        CdfStudioActions.SinkBigQuery();
    }

    @Then("Link Source and Sink to establish connection")
    public void linkSourceAndSinkToEstablishConnection() throws InterruptedException {
        Thread.sleep(2000);
       // CdfStudioActions.clickSource();
        SeleniumHelper.dragAndDrop(CdfStudioLocators.fromGCS,CdfStudioLocators.toBigQiery);
    }

    @Then("Enter the GCS Properties with {string} GCS bucket")
    public void enterTheGCSPropertiesWithGCSBucket(String bucket) throws InterruptedException, IOException {

        CdfGcsActions.gcsProperties();
        CdfGcsActions.enterReferenceName();
        CdfGcsActions.enterProjectId();
        CdfGcsActions.enterGcsBucket(bucket);
        CdfGcsActions.enterFormat();
        CdfGcsActions.skipHeader();
        CdfGcsActions.getSchema();
        Thread.sleep(10000);

    }


    @Then("Run and Preview")
    public void runAndPreview() throws InterruptedException {

        CdfStudioActions.runAndPreviewData();
    }

    @Then("Overrride the BigQuery Properties")
    public void overrrideTheBigQueryProperties() throws InterruptedException {
        pages.locators.CdfStudioLocators.bigQueryProperties.click();
        SeleniumDriver.getDriver().findElement(By.xpath("(//*[@title=\"int\"])[2]")).click();
        Thread.sleep(5000);
        Select drp=new Select(SeleniumDriver.getDriver().findElement(By.xpath("(//*[@title=\"int\"])[2]")));
        drp.selectByValue("string");
        pages.locators.CdfBigQueryPropertiesLocators.validateBttn.click();
        Thread.sleep(6000);
        CdfGcsActions.closeButton();
    }

    @Then("Open {string} link to login")
    public void openLinkToLogin(String arg0) throws IOException, InterruptedException {
            RemoteWebDriver driver = createDriverFromSession(SeleniumDriver.session(),SeleniumDriver.url);
            SeleniumDriver.openPage(SeleniumHelper.readParameters(arg0));
            Thread.sleep(3000);
    }


    @Then("enter the Query to check the count of table created {string}")
    public void enterTheQueryToCheckTheCountOfTableCreated(String table) throws InterruptedException {
        BigQueryActions.writeNewQuery("SELECT COUNT(*) FROM cdf-athena.test_automation."+table);
    }

    @Then("capture the count")
    public void captureTheCount() throws InterruptedException {
        BigQueryActions.countTable();
    }

    @Then("Save and Deploy Pipeline")
    public void saveAndDeployPipeline() throws InterruptedException {
        CdfStudioActions.pipelineName();
        CdfStudioActions.pipelineNameIp("TestPipeline"+ UUID.randomUUID().toString());
        CdfStudioActions.pipelineSave();
        Thread.sleep(3000);
        CdfStudioActions.pipelineDeploy();
    }

    @Then("Run the Pipeline in Runtime")
    public void runThePipelineInRuntime() {
        CdfPipelineRunAction.runClick();
    }

    @Then("Wait till pipeline is in running state")
    public void waitTillPipelineIsInRunningState() throws InterruptedException {
        Boolean bool= true;
        WebDriverWait wait = new WebDriverWait(SeleniumDriver.getDriver(), 1000000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-cy='Succeeded']")));
    }

    @Then("Verify the pipeline status is {string}")
    public void verifyThePipelineStatusIs(String status) {

        Assert.assertTrue( SeleniumDriver.getDriver().findElement(By.xpath("//*[@data-cy='"+status+"']")).isDisplayed());
    }

    @Then("Open Logs")
    public void openLogs() {
        CdfPipelineRunAction.logsClick();
    }

    @Then("validate successMessage is displayed")
    public void validateSuccessMessageIsDisplayed() {
        CdfLogActions.validateSucceeded();
    }

    @Then("Click on Advance logs and validate the success message")
    public void clickOnAdvanceLogsAndValidateTheSuccessMessage() {
        CdfLogActions.goToAdvanceLogs();
        CdfLogActions.validateSucceeded();
    }


    @Then("Enter the BigQuery Properties for table {string}")
    public void enterTheBigQueryPropertiesForTable(String arg0) throws InterruptedException {
        CdfBigQueryPropertiesActions.enterBigQueryProperties(arg0);
    }


    @Then("Close the GCS Properties")
    public void closeTheGCSProperties() {
        CdfGcsActions.closeButton();
    }

    @Then("Close the BigQuery Properties")
    public void closeTheBigQueryProperties() {
        CdfGcsActions.closeButton();
    }
}

