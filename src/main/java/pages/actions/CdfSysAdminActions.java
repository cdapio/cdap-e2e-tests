 package pages.actions;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.locators.CdfSysAdminLocators;
import utils.SeleniumDriver;

public class CdfSysAdminActions {
    public static pages.locators.CdfSysAdminLocators cdfSysAdminLocators = null;
    static boolean checkParam=false;
    static {
        cdfSysAdminLocators = PageFactory.initElements(SeleniumDriver.getDriver(), pages.locators.CdfSysAdminLocators.class);
    }

    public static void selectMacroAPIService(String service){
        Select drpAPIRequest = new Select(CdfSysAdminLocators.apiCallType);
        drpAPIRequest.selectByVisibleText(service);
    }

    public static void enterURI(String service){
        CdfSysAdminLocators.apiInputURI.sendKeys(service);

    }

    public static void enterRequestBody(String requestBody){
        CdfSysAdminLocators.requestBody.sendKeys(requestBody);
    }

    public static void clearRequest(){
        CdfSysAdminLocators.clearButton.click();
    }

    public static void clearAllRequest(){ CdfSysAdminLocators.clearAll.click(); }

    public static void sendRequest() {
        CdfSysAdminLocators.sendButton.click();
    }

        public static void verifySuccess(){
           String verify= CdfSysAdminLocators.success200.getText();
            if (verify.contains("200"))
            {
                checkParam=true;
                System.out.println("Succeess");
            }
    }

}
