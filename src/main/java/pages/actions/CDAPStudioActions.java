package pages.actions;

import org.openqa.selenium.support.PageFactory;
import pages.locators.CDAPStudioLocators;
import utils.SeleniumDriver;
import utils.SeleniumHelper;

public class CDAPStudioActions {
        public static CDAPStudioLocators CDAPStudioLocators = null;

        static {
            CDAPStudioLocators = PageFactory.initElements(SeleniumDriver.getDriver(), CDAPStudioLocators.class);
        }

    public static void selectGCS() throws InterruptedException {
        Thread. sleep(3000);
        CDAPStudioLocators.gcsBucket.click();
    }


    public static void clickSource() {
        CDAPStudioLocators.source.click();
    }




    public static void SinkBigQuery() {
        CDAPStudioLocators.sink.click();
        CDAPStudioLocators.bigQueryObject.click();
    }


    public static void runAndPreviewData() throws InterruptedException {
        CDAPStudioLocators.previewButton.click();
        CDAPStudioLocators.runButton.click();
        SeleniumHelper.waitElementInvisible("//*[@class='fa fa-stop text-danger']");
        System.out.println("fa fa-stop text-danger");
        SeleniumHelper.waitElementInvisible("//*[@class='fa fa-play text-success']");
        System.out.println("text-success");

    }

    public static void previewSelect()
    {
        CDAPStudioLocators.preview.click();
    }

    public static void pipelineName()
    {
        CDAPStudioLocators.pipelineName.click();
    }

    public static void pipelineNameIp(String pipelinName)
    {
        CDAPStudioLocators.pipelineNameIp.sendKeys(pipelinName);
    }

    public static void pipelineSave()
    {
        CDAPStudioLocators.pipelineSave.click();
    }

    public static void pipelineDeploy()
    {
        CDAPStudioLocators.pipelineDeploy.click();
    }

    public static String  bannerErrorMessage() throws InterruptedException {
            return CDAPStudioLocators.bannerMssge.getText();

    }

    public static void runfromConfigure() throws InterruptedException {
        CDAPStudioLocators.configRun.click();
        SeleniumHelper.waitElementInvisible("//*[@class='fa fa-stop text-danger']");

    }
}
