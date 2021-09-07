package pages.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import utils.SeleniumDriver;

public class CDAPHomeLocators {

    @FindBy(how= How.XPATH,using="//a[@href='/cdap/ns/default/wrangler']")
    public static WebElement wrangler;

    @FindBy(how= How.XPATH,using="//a[@href='/pipelines/ns/default/studio']")
    public static WebElement studio;

}
