package stepsDesign;


import io.cucumber.core.api.Scenario;
import io.cucumber.java.AfterStep;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cdap.e2e.utils.SeleniumDriver;

public class AfterActions {

    @AfterStep
    public static void tearDown(Scenario scenario) {
        WebDriver driver=SeleniumDriver.getDriver();
        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshotBytes, "image/png");

    }


}
