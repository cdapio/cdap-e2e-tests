package io.cdap.e2e.pages.actions;

import io.cdap.e2e.pages.locators.CdfStudioLocators;
import io.cdap.e2e.utils.SeleniumHelper;
import java.io.IOException;
import org.openqa.selenium.support.PageFactory;
import io.cdap.e2e.utils.SeleniumDriver;


public class CdfBigQueryPropertiesActions {
    public static io.cdap.e2e.pages.locators.CdfBigQueryPropertiesLocators CdfBigQueryPropertiesLocators = null;
    static int run =0;
    static {

        CdfBigQueryPropertiesLocators = PageFactory.initElements(SeleniumDriver.getDriver(), io.cdap.e2e.pages.locators.CdfBigQueryPropertiesLocators.class);
    }

    public static void enterBigQueryProperties(String arg0) throws InterruptedException, IOException {
        CdfStudioLocators.bigQueryProperties.click();
        io.cdap.e2e.pages.locators.CdfBigQueryPropertiesLocators.bigQueryReferenceName.sendKeys("automation_test");
        io.cdap.e2e.pages.locators.CdfBigQueryPropertiesLocators.bigQueryDataSetProjectID.
                sendKeys(SeleniumHelper.readParameters("Project-ID"));
        io.cdap.e2e.pages.locators.CdfBigQueryPropertiesLocators.bigQueryDataSet.
                sendKeys(SeleniumHelper.readParameters("Data-Set"));
        io.cdap.e2e.pages.locators.CdfBigQueryPropertiesLocators.bigQueryTable.sendKeys(arg0);

        io.cdap.e2e.pages.locators.CdfBigQueryPropertiesLocators.updateTable.click();
        io.cdap.e2e.pages.locators.CdfBigQueryPropertiesLocators.truncatableSwitch.click();
        io.cdap.e2e.pages.locators.CdfBigQueryPropertiesLocators.validateBttn.click();
        Thread.sleep(8000);
    }




}
