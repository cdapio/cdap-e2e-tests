package io.cdap.e2e.utils;

import java.io.IOException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.cdap.e2e.utils.RemoteClass.createDriverFromSession;

public interface CdfHelper {

    public default void openCdf() throws IOException, InterruptedException {
        SeleniumDriver seleniumDriver=new SeleniumDriver();
        RemoteWebDriver driver = createDriverFromSession(seleniumDriver.session(), seleniumDriver.url);
        SeleniumDriver.openPage(SeleniumHelper.readParameters("CDFURL"));
        try
        {
            SeleniumDriver.getDriver().switchTo().alert().accept();
            SeleniumDriver.waitForPageToLoad();
        }
        catch (NoAlertPresentException Ex)
        {
            SeleniumDriver.waitForPageToLoad();
        }

    }
}
