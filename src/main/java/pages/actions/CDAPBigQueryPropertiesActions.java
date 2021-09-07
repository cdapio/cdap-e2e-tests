package pages.actions;

import org.openqa.selenium.support.PageFactory;
import pages.locators.CDAPBigQueryPropertiesLocators;
import pages.locators.CDAPStudioLocators;
import utils.SeleniumDriver;


public class CDAPBigQueryPropertiesActions {
    public static CDAPBigQueryPropertiesLocators CDAPBigQueryPropertiesLocators = null;
    static int run =0;
    static {

        CDAPBigQueryPropertiesLocators = PageFactory.initElements(SeleniumDriver.getDriver(), CDAPBigQueryPropertiesLocators.class);
    }

    public static void enterBigQueryProperties(String arg0) throws InterruptedException {
        CDAPStudioLocators.bigQueryProperties.click();
        CDAPBigQueryPropertiesLocators.bigQueryReferenceName.sendKeys("automation_test");
        CDAPBigQueryPropertiesLocators.bigQueryDataSetProjectID.sendKeys("cdf-athena");
        CDAPBigQueryPropertiesLocators.bigQueryDataSet.sendKeys("test_automation");

        CDAPBigQueryPropertiesLocators.bigQueryTable.sendKeys(arg0);
        CDAPBigQueryPropertiesLocators.truncatableSwitch.click();
        CDAPBigQueryPropertiesLocators.validateBttn.click();
        Thread.sleep(8000);
    }




}
