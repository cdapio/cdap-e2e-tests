/*
 * Copyright 2021 Google LLC
 */

package io.cdap.e2e.pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Represents CdfBigQueryPropertiesLocators
 */
public class CdfBigQueryPropertiesLocators {


    @FindBy(how = How.XPATH, using = "//*[@data-cy='referenceName' and @class='MuiInputBase-input']")
    public static WebElement bigQueryReferenceName;

    @FindBy(how = How.XPATH, using = "//*[@placeholder='Dataset the table belongs to']")
    public static WebElement bigQueryDataSet;

    @FindBy(how = How.XPATH, using = "//*[@data-cy=\"datasetProject\" and @class='MuiInputBase-input']")
    public static WebElement bigQueryDataSetProjectID;

    @FindBy(how = How.XPATH, using = "//*[@placeholder='Table to write to']")
    public static WebElement bigQueryTable;

    @FindBy(how = How.XPATH, using = "//*[@data-cy='switch-truncateTable']")
    public static WebElement truncatableSwitch;

    @FindBy(how = How.XPATH, using = "//*[@data-cy='switch-allowSchemaRelaxation']")
    public static WebElement updateTable;

    @FindBy(how = How.XPATH, using = "//*[@data-cy='plugin-properties-validate-btn']")
    public static WebElement validateBttn;

    @FindBy(how = How.XPATH, using = "//*[@class='fa fa-remove']")
    public static WebElement closeButton;

    @FindBy(how = How.XPATH, using = "//*[@role='button' and contains(text(),'Actions')]")
    public static WebElement actionButton;

    @FindBy(how = How.XPATH, using = "//*[@role='option' and contains(text(),'Export')]")
    public static WebElement exportButton;
}
