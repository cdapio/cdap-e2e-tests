package io.cdap.e2e.pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CdfPipelineRunLocators {
    @FindBy(xpath="//*[@class=\"icon-svg icon-play\"]")
    public WebElement run;

    @FindBy(xpath="//*[@class=\"icon-svg icon-sliders configure-icon\"]")
    public WebElement configure;

    @FindBy(xpath="//*[@class=\"btn pipeline-action-btn pipeline-stop-btn\"]")
    public WebElement stop;

    @FindBy(xpath="//*[@class=\"icon-svg icon-circle\"]")
    public WebElement runPipelineStatus;

    @FindBy(xpath="//*[@data-cy=\"Deployed\"]")
    public WebElement deployedStatus;

    @FindBy(xpath="//*[@data-cy=\"Provisioning\"]")
    public WebElement provisioningStatus;

    @FindBy(xpath="//*[@data-cy=\"Stopped\"]")
    public WebElement stoppedStatus;

    @FindBy(xpath="//*[@data-cy=\"Starting\"]")
    public WebElement startingStatus;

    @FindBy(xpath="//*[@data-cy=\"Failed\"]")
    public WebElement failedStatus;

    @FindBy(xpath="//*[@data-cy=\"Succeeded\"]")
    public WebElement succeededStatus;

    @FindBy(xpath="//*[@data-cy=\"Running\"]")
    public WebElement runningStatus;

    @FindBy(xpath="//*[@class=\"run-logs-btn\"]")
    public WebElement logs;

    @FindBy(xpath="(//*[@type=\"button\"])[7]")
    public WebElement logsArrow;

    @FindBy(xpath="//*[contains(text(), 'View Raw Logs') ]")
    public WebElement viewRawLogs;
}
