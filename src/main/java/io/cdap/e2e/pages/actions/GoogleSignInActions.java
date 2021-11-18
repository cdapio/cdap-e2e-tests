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

import io.cdap.e2e.pages.locators.GoogleSignInLocator;
import io.cdap.e2e.utils.SeleniumHelper;

import java.io.IOException;

import static io.cdap.e2e.utils.ConstantsUtil.GPAZWRD;
import static io.cdap.e2e.utils.ConstantsUtil.GUSERNAME;

/**
 * Represents GoogleSignInActions
 */
public class GoogleSignInActions {
  public static GoogleSignInLocator googleSignInLocator;

  static {
    googleSignInLocator = SeleniumHelper.getPropertiesLocators(GoogleSignInLocator.class);
  }

  public static void login() throws InterruptedException, IOException {
    googleSignInLocator.username.get(0).sendKeys(SeleniumHelper.readParameters(GUSERNAME));
    googleSignInLocator.nextButton.click();
    googleSignInLocator.passwordField.sendKeys(SeleniumHelper.readParameters(GPAZWRD));
    googleSignInLocator.nextButton.click();
    googleSignInLocator.nextButton.click();
  }

  public static void corpLogin() throws InterruptedException, IOException {
    googleSignInLocator.corpUsername.sendKeys(SeleniumHelper.readParameters(GUSERNAME));
    googleSignInLocator.corpPassword.sendKeys(SeleniumHelper.readParameters(GPAZWRD));
    googleSignInLocator.corpSignnButton.click();
  }
}
