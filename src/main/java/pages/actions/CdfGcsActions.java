package pages.actions;

import java.io.IOException;
import java.util.UUID;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import pages.locators.CdfGCSLocators;
import utils.SeleniumDriver;
import utils.SeleniumHelper;

public class CdfGcsActions {

    public static CdfGCSLocators cdfGCSLocators = null;
    static {
        cdfGCSLocators = PageFactory.initElements(SeleniumDriver.getDriver(), CdfGCSLocators.class);
    }

    public static void enterReferenceName()  {
        CdfGCSLocators.referenceName.sendKeys(UUID.randomUUID().toString());
    }

    public static void enterProjectId()  {
        CdfGCSLocators.projectID.sendKeys("cdf-athena");
    }

    public static void enterGcsBucket(String bucket) throws IOException {
        CdfGCSLocators.gcsPath.sendKeys(SeleniumHelper.readParameters(bucket));
    }

    public static void enterFormat()  {
        CdfGCSLocators.format.click();
        SeleniumDriver.getDriver().findElement(By.xpath("//*[contains(text(),'csv')]")).click();

    }

    public static void enterSamplesize()  {
        CdfGCSLocators.samplesize.sendKeys("1000");
    }

    public static void closeButton()  {
        CdfGCSLocators.closeButton.click();
    }

    public static void gcsProperties()  { CdfGCSLocators.gcsProperties.click(); }

    public static void skipHeader()  { CdfGCSLocators.skipHeader.click(); }

    public static void getSchema()  { CdfGCSLocators.getSchemaButton.click(); }
}
