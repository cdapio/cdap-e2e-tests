/*
 * Copyright 2021 Google LLC
 */

package io.cdap.e2e.pages.actions;

import java.io.IOException;
import org.openqa.selenium.support.PageFactory;
import io.cdap.e2e.pages.locators.GoogleSignInLocator;
import io.cdap.e2e.utils.SeleniumDriver;
import io.cdap.e2e.utils.SeleniumHelper;

public class GoogleSignInActions {
    public static io.cdap.e2e.pages.locators.GoogleSignInLocator GoogleSignInLocator = null;

    static {

        GoogleSignInLocator = PageFactory.initElements(SeleniumDriver.getDriver(), GoogleSignInLocator.class);

    }

    public static void login() throws InterruptedException, IOException {
        GoogleSignInLocator.username.get(0).sendKeys(SeleniumHelper.readParameters("gLoginUsername"));
        GoogleSignInLocator.nextButton.click();
        Thread.sleep(2000);
        GoogleSignInLocator.passwordField.sendKeys(SeleniumHelper.readParameters("gPassword"));
        Thread.sleep(2000);
        GoogleSignInLocator.nextButton.click();
        Thread.sleep(6000);
        GoogleSignInLocator.nextButton.click();
    }

    public static void corpLogin() throws InterruptedException, IOException {
        GoogleSignInLocator.corpUsername.sendKeys(SeleniumHelper.readParameters("gLoginUsername"));
        GoogleSignInLocator.corpPassword.sendKeys(SeleniumHelper.readParameters("gPassword"));
        GoogleSignInLocator.corpSignnButton.click();
        Thread.sleep(3000);
    }
}
