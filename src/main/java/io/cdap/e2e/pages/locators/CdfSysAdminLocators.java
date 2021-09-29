package io.cdap.e2e.pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CdfSysAdminLocators {

    @FindBy(how= How.XPATH,using="//*[@data-cy='request-method-selector']")
    public static WebElement apiCallType;


    @FindBy(how= How.XPATH,using="//*[@data-cy='request-path-input']")
    public static WebElement apiInputURI;

    @FindBy(how= How.XPATH,using="//*[@data-cy='request-body']")
    public static WebElement requestBody;

    @FindBy(how= How.XPATH,using="//*[contains(text(),'Clear')]")
    public static WebElement clearButton;

    @FindBy(how= How.XPATH,using="//*[contains(text(),'Send')]")
    public static WebElement sendButton;

    @FindBy(how= How.XPATH,using="//*[@data-cy='request-path']/parent::*//child::div[2]")
    public static WebElement success200;

    @FindBy(how= How.XPATH,using= "//*[@data-cy='Clear All']")
    public static WebElement clearAll;


}
