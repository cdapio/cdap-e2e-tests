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

package io.cdap.e2e.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

/**
 * Represents SeleniumDriver
 */
public class SeleniumDriver {

  private static final Logger logger = LoggerFactory.getLogger(SeleniumDriver.class);
  public static WebDriverWait waitDriver;
  public static URL url;
  private static SeleniumDriver seleniumDriver;
  private static ChromeDriver chromeDriver;

  SeleniumDriver() throws IOException {
    ChromeDriverService service = new ChromeDriverService.Builder().build();
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--no-sandbox");
    chromeOptions.addArguments("--disable-setuid-sandbox");
    chromeOptions.addArguments("--headless");
    chromeOptions.addArguments("--window-size=" + SeleniumHelper.readParameters("windowSize"));
    chromeOptions.addArguments("--disable-gpu");
    chromeOptions.addArguments("--disable-dev-shm-usage");
    chromeOptions.addArguments("--disable-features=VizDisplayCompositor");
    chromeOptions.addArguments("--remote-allow-origins=*");
    // set default download directory
    String downloadDir = Paths.get("target").toAbsolutePath().toString() + "/downloads";
    HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
    chromePrefs.put("profile.default_content_settings.popups", 0);
    chromePrefs.put("download.default_directory", downloadDir);
    chromeOptions.setExperimentalOption("prefs", chromePrefs);
    chromeDriver = new ChromeDriver(service, chromeOptions);
    chromeDriver.manage().window().maximize();
    HttpCommandExecutor executor = (HttpCommandExecutor) chromeDriver.getCommandExecutor();
    url = executor.getAddressOfRemoteServer();
    waitDriver = new WebDriverWait(chromeDriver, ConstantsUtil.DEFAULT_TIMEOUT_SECONDS);
    chromeDriver.manage().timeouts()
      .implicitlyWait(Duration.of(ConstantsUtil.IMPLICIT_TIMEOUT_SECONDS, ChronoUnit.SECONDS));
    chromeDriver.manage().timeouts()
      .pageLoadTimeout(Duration.of(ConstantsUtil.PAGE_LOAD_TIMEOUT_SECONDS, ChronoUnit.SECONDS));
    String window = chromeDriver.getWindowHandle();
    SessionId session = chromeDriver.getSessionId();
    logger.info("Session iD:" + session);
    logger.info("Window ->" + window);
  }

  public static SessionId session() {
    SessionId session = chromeDriver.getSessionId();
    return session;
  }

  public static void openPage(String url) {
    logger.info("Navigate to url: " + url);
    logger.info(String.valueOf(chromeDriver));
    chromeDriver.get(url);
  }

  public static WebDriver getDriver() {
    return chromeDriver;
  }

  public static WebDriverWait getWaitDriver() {
    return waitDriver;
  }

  public static WebDriverWait getWaitDriver(long timeoutInSeconds) {
    return new WebDriverWait(chromeDriver, timeoutInSeconds);
  }

  public static void setUpDriver() throws IOException {
    if (seleniumDriver == null) {
      seleniumDriver = new SeleniumDriver();
    }
  }

  public static void tearDown() {
    if (chromeDriver != null) {
      chromeDriver.quit();
    }
    seleniumDriver = null;
  }

  /**
   * @deprecated Use {@link WaitHelper#waitForPageToLoad()}
   * Small static wait has been used here because of https://cdap.atlassian.net/browse/CDAP-18862
   */
  @Deprecated
  public static void waitForPageToLoad() {
    WaitHelper.waitForPageToLoad();
  }
}
