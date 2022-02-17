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
import io.cdap.e2e.utils.ElementHelper;
import io.cdap.e2e.utils.PluginPropertyUtils;
import io.cdap.e2e.utils.SeleniumDriver;
import io.cdap.e2e.utils.SeleniumHelper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import static io.cdap.e2e.utils.ConstantsUtil.DELIMITER;
import static io.cdap.e2e.utils.ConstantsUtil.OFFSET;
import static io.cdap.e2e.utils.ConstantsUtil.PROJECT_ID;
import static io.cdap.e2e.utils.ConstantsUtil.SAMPLE_SIZE;
import static io.cdap.e2e.utils.ConstantsUtil.VALUE;

/**
 * Represents CdfGcsActions
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
    ElementHelper.replaceElementValue(CdfGCSLocators.projectID, PluginPropertyUtils.pluginProp(PROJECT_ID));
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

  /**
   * @deprecated
   * Use {@link CdfGcsActions#enterSampleSize(String)}
   */
  @Deprecated
  public static void enterSamplesize() {
    CdfGCSLocators.samplesize.sendKeys(SAMPLE_SIZE);
  }

  public static void closeButton() {
    CdfGCSLocators.closeButton.click();
  }

  /**
   * @deprecated
   * Use either {@link io.cdap.e2e.utils.CdfHelper#openSinkPluginProperties(String)}
   * or {@link io.cdap.e2e.utils.CdfHelper#openSourcePluginProperties(String)} as per plugin type.
   */
  @Deprecated
  public static void gcsProperties() {
    CdfGCSLocators.gcsProperties.click();
  }

  public static void skipHeader() {
    CdfGCSLocators.skipHeader.click();
  }

  public static void getSchema() {
    CdfGCSLocators.getSchemaButton.click();
  }

  /**
   * @deprecated
   * Use {@link CdfGcsActions#enterDelimiterField(String)}
   */
  @Deprecated
  public static void delimiter() throws IOException {
    CdfGCSLocators.delimiter.sendKeys(SeleniumHelper.readParameters(DELIMITER));
  }

  public static void selectFormat(String formatType) throws InterruptedException {
    CdfGCSLocators.format.click();
    ElementHelper.clickOnElement(SeleniumDriver.getDriver().findElement(By.xpath(
      "//li[@data-value='" + formatType + "']")));
  }

  /**
   * @deprecated
   * Use {@link io.cdap.e2e.utils.CdfHelper#validateSchema(Map)}
   */
  @Deprecated
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
    Assert.assertTrue(CdfGCSLocators.validationSuccessMessage.isDisplayed());
  }

  public static void selectFileEncoding(String encoding) {
    CdfGCSLocators.fileEncoding.click();
    SeleniumDriver.getDriver().findElement(By.xpath("//li[text()='" + encoding + "']")).click();
  }

  public static void enterMaxSplitSize(String maxSplitSize) {
    CdfGCSLocators.maxSplitSize.sendKeys(maxSplitSize);
  }

  public static void enterMinSplitSize(String minSplitSize) {
    CdfGCSLocators.minSplitSize.sendKeys(minSplitSize);
  }

  public static void enterRegexPath(String regexPath) {
    CdfGCSLocators.regexPath.sendKeys(regexPath);
  }

  public static void enterPathField(String pathField) {
    CdfGCSLocators.pathField.sendKeys(pathField);
  }

  public static void enterOverride(String override) {
    CdfGCSLocators.override.sendKeys(override);
  }

  public static void clickOverrideDataType(String dataType) {
    CdfGCSLocators.overrideDataType.click();
    ElementHelper.clickOnElement(
      SeleniumDriver.getDriver().findElement(By.xpath("//*[@data-value='" + dataType + "']")));
  }

  public static void enterDelimiterField(String delimiter) {
    CdfGCSLocators.delimiterField.sendKeys(delimiter);
  }

  /**
   * @deprecated
   * Use either {@link io.cdap.e2e.utils.CdfHelper#openSinkPluginPreviewData(String)}
   * or {@link io.cdap.e2e.utils.CdfHelper#openSourcePluginPreviewData(String)} as per plugin type.
   */
  @Deprecated
  public static void clickPreviewData() {
    ElementHelper.clickOnElement(CdfGCSLocators.gcsPreviewData);
  }

  public static void enterSampleSize(String sampleSize) {
    ElementHelper.replaceElementValue(CdfGCSLocators.samplesize, sampleSize);
  }

  public static void enterPathSuffix(String pathSuffix) {
    ElementHelper.replaceElementValue(CdfGCSLocators.pathSuffix, pathSuffix);
  }

  public static void toggleWriteHeader() {
    CdfGCSLocators.writeHeaderSwitch.click();
  }

  public static void enterLocation(String location) {
    ElementHelper.replaceElementValue(CdfGCSLocators.location, location);
  }

  public static void selectContentType(String contentType) {
    CdfGCSLocators.contentType.click();
    ElementHelper.clickOnElement(SeleniumDriver.getDriver().findElement(By.xpath(
      "//li[@data-value='" + contentType + "']")));
  }

  public static void enterEncryptionKeyName(String cmek) {
    CdfGCSLocators.cmekKey.sendKeys(cmek);
  }

  public static void enterOutputFilePrefix(String filePrefix) {
    CdfGCSLocators.outputFilePrefix.sendKeys(filePrefix);
  }

  public static void enterFileSystemProperties(String fileSystemProperties) {
    CdfGCSLocators.fileSystemProperties.sendKeys(fileSystemProperties);
  }
}
