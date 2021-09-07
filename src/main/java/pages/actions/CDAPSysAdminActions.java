 package pages.actions;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import org.testng.asserts.SoftAssert;
import pages.locators.CDAPSysAdminLocators;
import utils.SeleniumDriver;

public class CDAPSysAdminActions {
    public static CDAPSysAdminLocators CDAPSysAdminLocators = null;
    static SoftAssert softAssertion;
    static boolean checkParam=false;
    static {
        CDAPSysAdminLocators = PageFactory.initElements(SeleniumDriver.getDriver(), CDAPSysAdminLocators.class);
    }

    public static void selectMacroAPIService(String service){
        Select drpAPIRequest = new Select(CDAPSysAdminLocators.apiCallType);
        drpAPIRequest.selectByVisibleText(service);
    }

    public static void enterURI(String service){
        CDAPSysAdminLocators.apiInputURI.sendKeys(service);

    }

    public static void enterRequestBody(String requestBody){
        CDAPSysAdminLocators.requestBody.sendKeys(requestBody);
    }

    public static void clearRequest(){
        CDAPSysAdminLocators.clearButton.click();
    }

    public static void clearAllRequest(){ CDAPSysAdminLocators.clearAll.click(); }

    public static void sendRequest() {
        CDAPSysAdminLocators.sendButton.click();
    }

        public static void verifySuccess(){
           String verify= CDAPSysAdminLocators.success200.getText();
            if (verify.contains("200"))
            {
                checkParam=true;
                System.out.println("Succeess");
            }
            softAssertion=new SoftAssert();
            softAssertion.assertEquals(checkParam,true);
    }

}
