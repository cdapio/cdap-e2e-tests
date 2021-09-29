package io.cdap.e2e.pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CdfHomeLocators {

    @FindBy(how= How.XPATH,using="//a[@href='/cdap/ns/default/wrangler']")
    public static WebElement wrangler;

    @FindBy(how= How.XPATH,using="//a[@href='/pipelines/ns/default/studio']")
    public static WebElement studio;

}
