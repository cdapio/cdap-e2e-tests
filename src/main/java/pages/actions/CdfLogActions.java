package pages.actions;

import org.openqa.selenium.support.PageFactory;
import pages.locators.GoogleSignInLocator;
import utils.SeleniumDriver;
import utils.SeleniumHelper;

import java.io.IOException;

public class CdfLogActions {
    public static pages.locators.CdfLogLocators cdfLogLocators = null;

    static {

        cdfLogLocators = PageFactory.initElements(SeleniumDriver.getDriver(), pages.locators.CdfLogLocators.class);

    }

    public static void validateErrorPopupLog() throws InterruptedException {
        Thread.sleep(4000);
        cdfLogLocators.errorMessagePopup.click();
    }

    public static void validateErrorPopupLog(String error) throws InterruptedException {
        Thread.sleep(4000);

        cdfLogLocators.errorMessagePopup.click();
    }


    public static void dismissPopup()  {
        cdfLogLocators.dismissable.click();
    }

    public static void goToLogs() {
        cdfLogLocators.goToLogs.click();
    }

    public static void validateFailed() {
        cdfLogLocators.validateFailed.isDisplayed();
    }
    public static void validateCategoryError() {
        cdfLogLocators.validateCategoryError.isDisplayed();
    }

    public static void validateLogError() {
        cdfLogLocators.validateCategoryError.isDisplayed();
    }
    public static void goToAdvanceLogs() {
        cdfLogLocators.getGoToAdvanceLogs.click();
    }

    public static void validateSucceeded() {
        cdfLogLocators.validateSucceeded.isDisplayed();
    }

    public static void closeLogs() {
        cdfLogLocators.closeLogs.click();
    }

}
