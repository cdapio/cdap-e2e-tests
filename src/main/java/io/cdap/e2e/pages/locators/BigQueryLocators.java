/*
 * Copyright 2021 Google LLC
 */

package io.cdap.e2e.pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class BigQueryLocators {

    @FindBy(how= How.XPATH,using="//*[@aria-label='Compose a new query']")
    public static WebElement composeNewQuery;

    @FindBy(how= How.XPATH,using="//*[@class='inputarea']")
    public static List<WebElement> writeQueryBox;

    @FindBy(how= How.XPATH,using="//*[contains(text(),' Run ')]")
    public static List<WebElement> runQuery;


    @FindBy(how= How.XPATH,using="//*[@class='p6n-bq-number-cell']")
    public static WebElement countTable;
}
