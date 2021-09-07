package pages.actions;

import java.io.IOException;
import java.util.UUID;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

    public static void enterProjectId() throws IOException {
        for (int i = 0; i <=20; i++) { CDAPGCSLocators.projectID.sendKeys(Keys.BACK_SPACE); }
        CDAPGCSLocators.projectID.sendKeys(SeleniumHelper.readParameters("project-id"));
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
