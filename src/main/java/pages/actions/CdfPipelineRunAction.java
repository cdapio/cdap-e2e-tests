package pages.actions;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import pages.locators.CdfPipelineRunLocators;
import utils.SeleniumDriver;
import utils.SeleniumHelper;

public class CdfPipelineRunAction {
    public static CdfPipelineRunLocators cdfPipelineRunLocators=null;

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
}
