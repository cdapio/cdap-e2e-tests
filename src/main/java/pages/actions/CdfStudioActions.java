package pages.actions;

import org.openqa.selenium.support.PageFactory;
import utils.SeleniumDriver;
import utils.SeleniumHelper;

public class CdfStudioActions {
        public static pages.locators.CdfStudioLocators CdfStudioLocators = null;

        static {
            CdfStudioLocators = PageFactory.initElements(SeleniumDriver.getDriver(), pages.locators.CdfStudioLocators.class);
        }

    public static void selectGCS() throws InterruptedException {
        Thread. sleep(3000);
        pages.locators.CdfStudioLocators.gcsBucket.click();
    }


    public static void clickSource() {
        pages.locators.CdfStudioLocators.source.click();
    }




    public static void SinkBigQuery() {
        pages.locators.CdfStudioLocators.sink.click();
        pages.locators.CdfStudioLocators.bigQueryObject.click();
    }


    public static void runAndPreviewData() throws InterruptedException {
        pages.locators.CdfStudioLocators.previewButton.click();
        pages.locators.CdfStudioLocators.runButton.click();
        SeleniumHelper.waitElementInvisible("//*[@class='fa fa-stop text-danger']");
        System.out.println("fa fa-stop text-danger");
        SeleniumHelper.waitElementInvisible("//*[@class='fa fa-play text-success']");
        System.out.println("text-success");

    }

    public static void previewSelect()
    {
        pages.locators.CdfStudioLocators.preview.click();
    }

    public static void pipelineName()
    {
        pages.locators.CdfStudioLocators.pipelineName.click();
    }

    public static void pipelineNameIp(String pipelinName)
    {
        pages.locators.CdfStudioLocators.pipelineNameIp.sendKeys(pipelinName);
    }

    public static void pipelineSave()
    {
        pages.locators.CdfStudioLocators.pipelineSave.click();
    }

    public static void pipelineDeploy()
    {
        pages.locators.CdfStudioLocators.pipelineDeploy.click();
    }

    public static String  bannerErrorMessage() throws InterruptedException {
            return pages.locators.CdfStudioLocators.bannerMssge.getText();

    }

    public static void runfromConfigure() throws InterruptedException {
        pages.locators.CdfStudioLocators.configRun.click();
        SeleniumHelper.waitElementInvisible("//*[@class='fa fa-stop text-danger']");

    }
}
