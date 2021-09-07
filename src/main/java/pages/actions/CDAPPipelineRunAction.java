package pages.actions;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import pages.locators.CDAPPipelineRunLocators;
import utils.SeleniumDriver;

public class CDAPPipelineRunAction {
    public static CDAPPipelineRunLocators CDAPPipelineRunLocators =null;

    static {
        CDAPPipelineRunLocators = PageFactory.initElements(SeleniumDriver.getDriver(), CDAPPipelineRunLocators.class);
    }

    public static void runClick(){
        CDAPPipelineRunLocators.run.click();
    }

    public static String runPipelineStatus(){
        return CDAPPipelineRunLocators.runPipelineStatus.getAttribute("color");
    }

    public static Boolean isRunning(){
        boolean bool=false;
        try {

            bool= CDAPPipelineRunLocators.runningStatus.isDisplayed();
        }catch (NoSuchElementException e){
            System.out.println("");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return bool;
    }

    public static void logsClick(){
        CDAPPipelineRunLocators.logs.click();
    }

    public static Boolean isProvisioning(){
        boolean bool=false;
        try {
            bool= CDAPPipelineRunLocators.provisioningStatus.isDisplayed();
        }catch (NoSuchElementException e){
            System.out.println("");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return bool;
    }
}
