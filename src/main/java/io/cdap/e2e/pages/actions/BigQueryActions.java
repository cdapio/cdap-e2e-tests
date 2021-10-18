/*
 * Copyright 2021 Google LLC
 */

package io.cdap.e2e.pages.actions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.cdap.e2e.pages.locators.BigQueryLocators;
import io.cdap.e2e.utils.SeleniumDriver;

import java.util.List;

public class BigQueryActions {

    public static BigQueryLocators bigQueryLocators = null;
    static {
        bigQueryLocators = PageFactory.initElements(SeleniumDriver.getDriver(), BigQueryLocators.class);
    }

    public static void composeNewQuery()  {
        BigQueryLocators.composeNewQuery.click();
    }

    public static void writeNewQuery(String query) throws InterruptedException {
        List<WebElement> sendbox= BigQueryLocators.writeQueryBox;
        Thread.sleep(4000);
        sendbox.get(0).sendKeys(query);
        Thread.sleep(2000);
        List<WebElement> run=BigQueryLocators.runQuery;
        run.get(0).click();
        Thread.sleep(4000);

    }

    public static void countTable() throws InterruptedException {


        System.out.println("Count"+BigQueryLocators.countTable.getText());

    }
}
