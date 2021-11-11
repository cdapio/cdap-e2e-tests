/*
 * Copyright 2021 Google LLC
 */

package io.cdap.e2e.pages.actions;

import io.cdap.e2e.utils.SeleniumDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Represents CdfStudioActions
 */
public class CdfStudioActions {
    public static io.cdap.e2e.pages.locators.CdfStudioLocators cdfStudioLocators = null;

    static {
        cdfStudioLocators =
          PageFactory.initElements(SeleniumDriver.getDriver(), io.cdap.e2e.pages.locators.CdfStudioLocators.class);
    }

    public static void selectGCS() throws InterruptedException {
        Thread.sleep(3000);
        io.cdap.e2e.pages.locators.CdfStudioLocators.gcsBucket.click();
    }


    public static void clickSource() {
        io.cdap.e2e.pages.locators.CdfStudioLocators.source.click();
    }


    public static void sinkBigQuery() {
        io.cdap.e2e.pages.locators.CdfStudioLocators.sink.click();
        io.cdap.e2e.pages.locators.CdfStudioLocators.bigQueryObject.click();
    }


    public static void runAndPreviewData() throws InterruptedException {
        io.cdap.e2e.pages.locators.CdfStudioLocators.previewButton.click();
        io.cdap.e2e.pages.locators.CdfStudioLocators.runButton.click();
    }

    public static void previewSelect() {
        io.cdap.e2e.pages.locators.CdfStudioLocators.preview.click();
    }

    public static void pipelineName() {
        io.cdap.e2e.pages.locators.CdfStudioLocators.pipelineName.click();
    }

    public static void pipelineNameIp(String pipelinName) {
        io.cdap.e2e.pages.locators.CdfStudioLocators.pipelineNameIp.sendKeys(pipelinName);
    }

    public static void pipelineSave() {
        io.cdap.e2e.pages.locators.CdfStudioLocators.pipelineSave.click();
    }

    public static void pipelineDeploy() {
        io.cdap.e2e.pages.locators.CdfStudioLocators.pipelineDeploy.click();
    }

    public static String bannerErrorMessage() throws InterruptedException {
        return io.cdap.e2e.pages.locators.CdfStudioLocators.bannerMssge.getText();

    }

}
