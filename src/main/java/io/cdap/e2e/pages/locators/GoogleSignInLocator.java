/*
 * Copyright 2021 Google LLC
 */

package io.cdap.e2e.pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Represents GoogleSignInLocator
 */
public class GoogleSignInLocator {

    @FindBy(tagName = "input")
    public List<WebElement> username;

    @FindBy(className = "VfPpkd-vQzf8d")
    public WebElement nextButton;

    @FindBy(name = "password")
    public WebElement passwordField;


    @FindBy(name = "u")
    public WebElement corpUsername;

    @FindBy(name = "pw")
    public WebElement corpPassword;

    @FindBy(name = "signInButton")
    public WebElement corpSignnButton;


}

