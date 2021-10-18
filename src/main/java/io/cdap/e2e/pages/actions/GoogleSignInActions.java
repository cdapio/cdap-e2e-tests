/*
 * Copyright 2021 Google LLC
 */

package io.cdap.e2e.pages.actions;

import io.cdap.e2e.pages.locators.GoogleSignInLocator;
import io.cdap.e2e.utils.SeleniumDriver;
import io.cdap.e2e.utils.SeleniumHelper;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Represents GoogleSignInActions
 */
public class GoogleSignInActions {
    public static io.cdap.e2e.pages.locators.GoogleSignInLocator googleSignInLocator = null;

    static {

        googleSignInLocator = PageFactory.initElements(SeleniumDriver.getDriver(), GoogleSignInLocator.class);

    }

    public static void login() throws InterruptedException, IOException {
        googleSignInLocator.username.get(0).sendKeys(SeleniumHelper.readParameters("gLoginUsername"));
        googleSignInLocator.nextButton.click();
        Thread.sleep(2000);
        googleSignInLocator.passwordField.sendKeys(SeleniumHelper.readParameters("gPassword"));
        Thread.sleep(2000);
        googleSignInLocator.nextButton.click();
        Thread.sleep(6000);
        googleSignInLocator.nextButton.click();
    }

    public static void corpLogin() throws InterruptedException, IOException {
        googleSignInLocator.corpUsername.sendKeys(SeleniumHelper.readParameters("gLoginUsername"));
        googleSignInLocator.corpPassword.sendKeys(SeleniumHelper.readParameters("gPassword"));
        googleSignInLocator.corpSignnButton.click();
        Thread.sleep(3000);
    }
}
