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

package io.cdap.e2e.pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Represents GoogleSignInLocator
 */
public class GoogleSignInLocator {

  @FindBy(tagName = "input")
  public List<WebElement> username;

  @FindBy(className = "VfPpkd-vQzf8d")
  public WebElement nextButton;

  @FindBy(name = "password")
  public WebElement passwordField;


  @FindBy(name = "u")
  public WebElement corpUsername;

  @FindBy(name = "pw")
  public WebElement corpPassword;

  @FindBy(name = "signInButton")
  public WebElement corpSignnButton;


}

