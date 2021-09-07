package pages.actions;

import java.io.IOException;
import java.util.UUID;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import pages.locators.CDAPGCSLocators;
import utils.SeleniumDriver;
import utils.SeleniumHelper;

public class CDAPGcsActions {

    public static CDAPGCSLocators CDAPGCSLocators = null;
    static {
        CDAPGCSLocators = PageFactory.initElements(SeleniumDriver.getDriver(), CDAPGCSLocators.class);
    }

    public static void enterReferenceName()  {
        CDAPGCSLocators.referenceName.sendKeys(UUID.randomUUID().toString());
    }

    public static void enterProjectId()  {
        CDAPGCSLocators.projectID.sendKeys("cdf-athena");
    }

    public static void enterGcsBucket(String bucket) throws IOException {
        CDAPGCSLocators.gcsPath.sendKeys(SeleniumHelper.readParameters(bucket));
    }

    public static void enterFormat()  {
        CDAPGCSLocators.format.click();
        SeleniumDriver.getDriver().findElement(By.xpath("//*[contains(text(),'csv')]")).click();

    }

    public static void enterSamplesize()  {
        CDAPGCSLocators.samplesize.sendKeys("1000");
    }

    public static void closeButton()  {
        CDAPGCSLocators.closeButton.click();
    }

    public static void gcsProperties()  { CDAPGCSLocators.gcsProperties.click(); }

    public static void skipHeader()  { CDAPGCSLocators.skipHeader.click(); }

    public static void getSchema()  { CDAPGCSLocators.getSchemaButton.click(); }
}
