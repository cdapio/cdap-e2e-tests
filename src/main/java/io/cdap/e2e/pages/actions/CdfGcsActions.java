/*
 * Copyright © 2021 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package io.cdap.e2e.pages.actions;

import io.cdap.e2e.pages.locators.CdfGCSLocators;
import io.cdap.e2e.utils.SeleniumDriver;
import io.cdap.e2e.utils.SeleniumHelper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static io.cdap.e2e.utils.ConstantsUtil.COLOR;
import static io.cdap.e2e.utils.ConstantsUtil.DELIMITER;
import static io.cdap.e2e.utils.ConstantsUtil.OFFSET;
import static io.cdap.e2e.utils.ConstantsUtil.PROJECT_ID;
import static io.cdap.e2e.utils.ConstantsUtil.SAMPLE_SIZE;
import static io.cdap.e2e.utils.ConstantsUtil.VALUE;

/**
 * Represents CdfGcsActions.
 */
public class CdfGcsActions {
  public static boolean checkParameter = false;
  public static CdfGCSLocators cdfGCSLocators;

  static {
    cdfGCSLocators = SeleniumHelper.getPropertiesLocators(CdfGCSLocators.class);
  }

  public static void enterReferenceName() {
    CdfGCSLocators.referenceName.sendKeys(UUID.randomUUID().toString());
  }

  public static void enterProjectId() throws IOException {
    SeleniumHelper.replaceElementValue(CdfGCSLocators.projectID, SeleniumHelper.readParameters(PROJECT_ID));
  }

  public static void getGcsBucket(String bucket) {
    CdfGCSLocators.gcsPath.sendKeys(bucket);
  }

  @Deprecated
  public static void enterGcsBucket(String bucket) throws IOException {
    CdfGCSLocators.gcsPath.sendKeys(SeleniumHelper.readParameters(bucket));
  }

  @Deprecated
  public static void enterFormat() {
    CdfGCSLocators.format.click();
    SeleniumDriver.getDriver().findElement(By.xpath("//*[contains(text(),'csv')]")).click();
  }

  public static void selectFileEncoding(int utf) {
    CdfGCSLocators.fileEncoding.click();
    SeleniumDriver.getDriver().findElement(By.xpath("//*[contains(text(),'UTF-" + utf + "')]")).click();
  }

  public static void enterSamplesize() {
    CdfGCSLocators.samplesize.sendKeys(SAMPLE_SIZE);
  }

  public static void closeButton() {
    CdfGCSLocators.closeButton.click();
  }

  public static void gcsProperties() {
    CdfGCSLocators.gcsProperties.click();
  }

  public static void skipHeader() {
    CdfGCSLocators.skipHeader.click();
  }

  public static void getSchema() {
    CdfGCSLocators.getSchemaButton.click();
  }

  public static void delimiter() throws IOException {
    CdfGCSLocators.delimiter.sendKeys(SeleniumHelper.readParameters(DELIMITER));
  }

  public static void selectFormat(String formatType) throws InterruptedException {
    CdfGCSLocators.format.click();
    SeleniumHelper.waitAndClick(SeleniumDriver.getDriver().findElement(By.xpath(
      "//*[contains(text(),'" + formatType + "')]")));
  }

  public static void validateSchema() {
    for (WebElement schema : CdfGCSLocators.schemaSection) {
      String actualStr = schema.getAttribute(VALUE);
      String expectedStr = OFFSET;
      checkParameter = !expectedStr.equalsIgnoreCase(actualStr);
    }
    Assert.assertEquals(checkParameter, true);
  }

  public static void clickValidateButton() {
    CdfGCSLocators.validateBtn.click();
  }

  public static void validateSuccessMessage() {
    Assert.assertTrue(CdfGCSLocators.successMessage.isDisplayed());
  }

  public static void clickUseConnection() {
    CdfGCSLocators.useConnection.click();
  }

  public static void clickBrowseConnection() {
    CdfGCSLocators.browseConnection.click();
  }

  public static void selectConnection(String connectionName) {
    SeleniumHelper.waitAndClick(SeleniumDriver.getDriver().findElement(By.xpath(
      "//*[contains(text(),'" + connectionName + "') and contains(@class,'jss')]")));
  }

  public static void enterMaxsplitsize(String maxsplitSize) {
    CdfGCSLocators.maxSplitsize.sendKeys(maxsplitSize);
  }

  public static void enterMinsplitsize(String minsplitSize) {
    CdfGCSLocators.minSplitsize.sendKeys(minsplitSize);
  }

  public static void enterRegexpath(String regexPath) {
    CdfGCSLocators.regexPath.sendKeys(regexPath);
  }

  public static void enterPathfield(String pathField) {
    CdfGCSLocators.pathField.sendKeys(pathField);
  }

  public static void enterOverride(String override) {
    CdfGCSLocators.override.sendKeys(override);
  }

  public static void clickOverrideDatatype(String datatype) {
    CdfGCSLocators.overrideDatatype.click();
    SeleniumHelper.waitAndClick(SeleniumDriver.getDriver().findElement(By.xpath("//*[@data-value='"+datatype+"']")));
  }

  public static String getReferenceErrorColor() {
    return CdfGCSLocators.referenceError.getCssValue(COLOR);
  }

  public static String getPathError() {
    return CdfGCSLocators.pathError.getText();
  }

  public static String getPathErrorColor() {
    return CdfGCSLocators.pathError.getCssValue(COLOR);
  }

  public static boolean validateDatatype() {
    boolean flag = false;
    List<WebElement> elements = SeleniumDriver.getDriver().findElements(
      By.xpath("//*[@data-cy=\"select-undefined\"]/select"));
    for (WebElement datatype : elements) {
      String str = datatype.getAttribute("title");
      if (str.equals("timestamp")) {
        flag = true;
      } else {
        flag = false;
      }
    }
    return flag;
  }
}
