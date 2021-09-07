package pages.actions;

import org.openqa.selenium.support.PageFactory;
import pages.locators.CDAPLogLocators;
import utils.SeleniumDriver;

public class CDAPLogActions {
    public static CDAPLogLocators CDAPLogLocators = null;

    static {

        CDAPLogLocators = PageFactory.initElements(SeleniumDriver.getDriver(), CDAPLogLocators.class);

    }

    public static void validateErrorPopupLog() throws InterruptedException {
        Thread.sleep(4000);
        CDAPLogLocators.errorMessagePopup.click();
    }

    public static void validateErrorPopupLog(String error) throws InterruptedException {
        Thread.sleep(4000);

        CDAPLogLocators.errorMessagePopup.click();
    }


    public static void dismissPopup()  {
        CDAPLogLocators.dismissable.click();
    }

    public static void goToLogs() {
        CDAPLogLocators.goToLogs.click();
    }

    public static void validateFailed() {
        CDAPLogLocators.validateFailed.isDisplayed();
    }
    public static void validateCategoryError() {
        CDAPLogLocators.validateCategoryError.isDisplayed();
    }

    public static void validateLogError() {
        CDAPLogLocators.validateCategoryError.isDisplayed();
    }
    public static void goToAdvanceLogs() {
        CDAPLogLocators.getGoToAdvanceLogs.click();
    }

    public static void validateSucceeded() {
        CDAPLogLocators.validateSucceeded.isDisplayed();
    }

    public static void closeLogs() {
        CDAPLogLocators.closeLogs.click();
    }

}
