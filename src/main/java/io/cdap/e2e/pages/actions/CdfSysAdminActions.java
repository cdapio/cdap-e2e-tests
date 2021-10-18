 /*
  * Copyright 2021 Google LLC
  */

 package io.cdap.e2e.pages.actions;

 import io.cdap.e2e.pages.locators.CdfSysAdminLocators;
 import io.cdap.e2e.utils.SeleniumDriver;
 import org.junit.Assert;
 import org.openqa.selenium.support.PageFactory;
 import org.openqa.selenium.support.ui.Select;

 /**
  * Represents CdfSysAdminActions
  */
 public class CdfSysAdminActions {
     public static CdfSysAdminLocators cdfSysAdminLocators = null;
     static String checkParam = "false";

     static {
         cdfSysAdminLocators = PageFactory.initElements(SeleniumDriver.getDriver(), CdfSysAdminLocators.class);
     }

     public static void selectMacroAPIService(String service) {
         Select drpAPIRequest = new Select(CdfSysAdminLocators.apiCallType);
         drpAPIRequest.selectByVisibleText(service);
     }

     public static void enterURI(String service) {
         CdfSysAdminLocators.apiInputURI.sendKeys(service);

     }

     public static void enterRequestBody(String requestBody) {
         CdfSysAdminLocators.requestBody.sendKeys(requestBody);
     }

     public static void clearRequest() {
         CdfSysAdminLocators.clearButton.click();
     }

     public static void clearAllRequest() {
         CdfSysAdminLocators.clearAll.click();
     }

     public static void sendRequest() {
         CdfSysAdminLocators.sendButton.click();
     }

     public static void verifySuccess() {
         String verify = CdfSysAdminLocators.success200.getText();
         if (verify.contains("200")) {
             checkParam = "true";
             System.out.println("Succeess");
         }

         Assert.assertTrue(checkParam, true);
     }

 }
