package pages.actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.locators.CDAPHomeLocators;
import utils.SeleniumDriver;

public class CDAPHomeActions {
    public static CDAPHomeLocators CDAPHomeLocators = null;

    static {

        CDAPHomeLocators = PageFactory.initElements(SeleniumDriver.getDriver(), CDAPHomeLocators.class);

    }

    public static void clickStudio()
    {

        JavascriptExecutor js = (JavascriptExecutor)SeleniumDriver.getDriver();
        WebElement element= CDAPHomeLocators.studio;
           js.executeScript("arguments[0].click();",element);
        System.out.println("FIrst case passed");

    }



}
