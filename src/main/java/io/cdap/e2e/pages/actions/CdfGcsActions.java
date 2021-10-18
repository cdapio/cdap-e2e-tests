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
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.UUID;

/**
 * Represents CdfGcsActions
 */
public class CdfGcsActions {

    public static CdfGCSLocators cdfGCSLocators = null;

    static {
        cdfGCSLocators = PageFactory.initElements(SeleniumDriver.getDriver(), CdfGCSLocators.class);
    }

    public static void enterReferenceName() {
        CdfGCSLocators.referenceName.sendKeys(UUID.randomUUID().toString());
    }

    public static void enterProjectId() throws IOException {
        SeleniumHelper.replaceElementValue(CdfGCSLocators.projectID, SeleniumHelper.readParameters("Project-ID"));
    }

    public static void enterGcsBucket(String bucket) throws IOException {
        CdfGCSLocators.gcsPath.sendKeys(SeleniumHelper.readParameters(bucket));
    }

    public static void enterFormat() {
        CdfGCSLocators.format.click();
        SeleniumDriver.getDriver().findElement(By.xpath("//*[contains(text(),'csv')]")).click();

    }

    public static void enterSamplesize() {
        CdfGCSLocators.samplesize.sendKeys("1000");
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
}
