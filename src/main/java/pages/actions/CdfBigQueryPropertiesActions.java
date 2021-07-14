package pages.actions;

import org.openqa.selenium.support.PageFactory;
import utils.SeleniumDriver;


public class CdfBigQueryPropertiesActions {
    public static pages.locators.CdfBigQueryPropertiesLocators CdfBigQueryPropertiesLocators = null;
    static int run =0;
    static {

        CdfBigQueryPropertiesLocators = PageFactory.initElements(SeleniumDriver.getDriver(), pages.locators.CdfBigQueryPropertiesLocators.class);
    }

    public static void enterBigQueryProperties(String arg0) throws InterruptedException {
        pages.locators.CdfStudioLocators.bigQueryProperties.click();
        pages.locators.CdfBigQueryPropertiesLocators.bigQueryReferenceName.sendKeys("automation_test");
        pages.locators.CdfBigQueryPropertiesLocators.bigQueryDataSetProjectID.sendKeys("cdf-athena");
        pages.locators.CdfBigQueryPropertiesLocators.bigQueryDataSet.sendKeys("test_automation");

        pages.locators.CdfBigQueryPropertiesLocators.bigQueryTable.sendKeys(arg0);
        pages.locators.CdfBigQueryPropertiesLocators.truncatableSwitch.click();
        pages.locators.CdfBigQueryPropertiesLocators.validateBttn.click();
        Thread.sleep(8000);
    }




}
