/*
 * Copyright © 2022 Cask Data, Inc.
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

import io.cdap.e2e.pages.locators.OdfSignInLocator;
import io.cdap.e2e.utils.SeleniumHelper;

import java.io.IOException;

import static io.cdap.e2e.utils.ConstantsUtil.ODFPAZWRD;
import static io.cdap.e2e.utils.ConstantsUtil.ODFUSERNAME;

/**
 * Represents OdfSignInActions
 */
public class OdfSignInActions {
  public static OdfSignInLocator odfSignInLocator;

  static {
    odfSignInLocator = SeleniumHelper.getPropertiesLocators(OdfSignInLocator.class);
  }

  public static void login() throws InterruptedException, IOException {
    odfSignInLocator.odfUsername.sendKeys(SeleniumHelper.readParameters(ODFUSERNAME));
    odfSignInLocator.odfPassword.sendKeys(SeleniumHelper.readParameters(ODFPAZWRD));
    odfSignInLocator.loginButton.click();
  }
}
