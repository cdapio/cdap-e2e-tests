package io.cdap.e2e.pages.actions;

import java.util.ArrayList;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import io.cdap.e2e.pages.locators.CdfPipelineRunLocators;
import io.cdap.e2e.utils.SeleniumDriver;
import io.cdap.e2e.utils.SeleniumHelper;

public class CdfPipelineRunAction {
    public static CdfPipelineRunLocators  cdfPipelineRunLocators=null;

    static {
        cdfPipelineRunLocators = PageFactory.initElements(SeleniumDriver.getDriver(), CdfPipelineRunLocators.class);
    }

    public static void runClick() throws InterruptedException {
        SeleniumHelper.waitElementIsVisible(cdfPipelineRunLocators.run);
        cdfPipelineRunLocators.run.click();
    }

    public static String runPipelineStatus(){
        return cdfPipelineRunLocators.runPipelineStatus.getAttribute("color");
    }

    public static Boolean isRunning(){
        boolean bool=false;
        try {

            bool=cdfPipelineRunLocators.runningStatus.isDisplayed();
        }catch (NoSuchElementException e){
            System.out.println("");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return bool;
    }

    public static void logsClick(){
        cdfPipelineRunLocators.logs.click();
    }

    public static Boolean isProvisioning(){
        boolean bool=false;
        try {
            bool=cdfPipelineRunLocators.provisioningStatus.isDisplayed();
        }catch (NoSuchElementException e){
            System.out.println("");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return bool;
    }

    public static String captureRawLogs() {
        try {

            JavascriptExecutor js = (JavascriptExecutor) SeleniumDriver.getDriver();
            js.executeScript("arguments[0].click()", cdfPipelineRunLocators.logsArrow);
            cdfPipelineRunLocators.viewRawLogs.click();
            String parent = SeleniumDriver.getDriver().getWindowHandle();
            ArrayList<String> tabs2 = new ArrayList<String>(SeleniumDriver.getDriver().getWindowHandles());
            SeleniumDriver.getDriver().switchTo().window(tabs2.get(1));
            String logs = SeleniumDriver.getDriver().findElement(By.xpath("/html/body/pre")).getText();
            SeleniumDriver.getDriver().close();
            SeleniumDriver.getDriver().switchTo().window(parent);
            return logs;
        } catch (Exception e) {
          return "*****Error in Capturing logs:+"+e;
        }
    }
}
