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

public class TestGCS1 {
    static int i = 0;


    @Given("Open Datafusion Project to configure pipeline1")
    public void openDatafusionProjectToConfigurePipeline() throws IOException, InterruptedException {

        RemoteWebDriver driver = createDriverFromSession(SeleniumDriver.session(), SeleniumDriver.url);
            SeleniumDriver.openPage(SeleniumHelper.readParameters("CDFURL"));
        SeleniumDriver.waitForPageToLoad();
    }

    @When("Source is GCS bucket1")
    public void sourceIsGCSBucket() throws InterruptedException {
        CdfStudioActions.selectGCS();

    }


    @When("Target is BigQuery1")
    public void targetIsBigQuery() {
        CdfStudioActions.SinkBigQuery();
    }

    @Then("Link Source and Sink to establish connection1")
    public void linkSourceAndSinkToEstablishConnection() throws InterruptedException {
        Thread.sleep(2000);
        // CdfStudioActions.clickSource();
        SeleniumHelper.dragAndDrop(CdfStudioLocators.fromGCS, CdfStudioLocators.toBigQiery);
    }

    @Then("Enter the GCS Properties with {string} GCS bucket1")
    public void enterTheGCSPropertiesWithGCSBucket(String bucket) throws InterruptedException, IOException {

        CdfGcsActions.gcsProperties();
        CdfGcsActions.enterReferenceName();
        CdfGcsActions.enterGcsBucket(bucket);
        CdfGcsActions.enterFormat();
        CdfGcsActions.skipHeader();
        CdfGcsActions.getSchema();
        Thread.sleep(10000);

    }


    @Then("Close the GCS Properties1")
    public void closeTheGCSProperties() {
        CdfGcsActions.closeButton();
    }
}
