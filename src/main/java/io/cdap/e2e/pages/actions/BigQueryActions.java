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

import io.cdap.e2e.pages.locators.BigQueryLocators;
import io.cdap.e2e.utils.SeleniumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Represents BigQueryActions
 */
public class BigQueryActions {

  public static BigQueryLocators bigQueryLocators = null;

  static {
    bigQueryLocators = PageFactory.initElements(SeleniumDriver.getDriver(), BigQueryLocators.class);
  }

  public static void composeNewQuery() {
    BigQueryLocators.composeNewQuery.click();
  }

  public static void writeNewQuery(String query) throws InterruptedException {
    List<WebElement> sendbox = BigQueryLocators.writeQueryBox;
    Thread.sleep(4000);
    sendbox.get(0).sendKeys(query);
    Thread.sleep(2000);
    List<WebElement> run = BigQueryLocators.runQuery;
    run.get(0).click();
    Thread.sleep(4000);

    }

    public static void countTable() throws InterruptedException {


      System.out.println("Count" + BigQueryLocators.countTable.getText());

    }
}
