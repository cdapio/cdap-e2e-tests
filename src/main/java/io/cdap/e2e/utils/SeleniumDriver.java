/*
 * Copyright 2021 Google LLC
 */

package io.cdap.e2e.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Represents SeleniumDriver
 */
public class SeleniumDriver {

    public static final int TIMEOUT = 50;

    //initialize webdriver
    public static final int PAGE_LOAD_TIMEOUT = 50;
    //initialize timeouts
    public static WebDriverWait waitDriver;
    public static URL url;
    private static SeleniumDriver seleniumDriver;
    private static ChromeDriver chromeDriver;

    SeleniumDriver() throws IOException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--window-size=" + SeleniumHelper.readParameters("windowSize"));
        chromeOptions.addArguments("--disable-gpu");
        chromeDriver = new ChromeDriver(chromeOptions);
        chromeDriver.manage().window().maximize();
        HttpCommandExecutor executor = (HttpCommandExecutor) chromeDriver.getCommandExecutor();
        url = executor.getAddressOfRemoteServer();
        waitDriver = new WebDriverWait(chromeDriver, TIMEOUT);
        chromeDriver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        String window = chromeDriver.getWindowHandle();
        SessionId session = chromeDriver.getSessionId();
        System.out.println("Session iD:" + session);
        System.out.println("Window ->" + window);

    }


    public static SessionId session() {

        SessionId session = chromeDriver.getSessionId();
        // System.out.println("Check thos one------"+session);
        return session;
    }

    public static void getWaitDriver() {
        new WebDriverWait(chromeDriver, 40000).
          until(ExpectedConditions.invisibilityOf(chromeDriver.findElement(By.className("loading-bar"))));

    }


    public static void openPage(String url) {
        System.out.println(url);
        System.out.println(chromeDriver);
        chromeDriver.get(url);
    }

    public static WebDriver getDriver() {
        return chromeDriver;
    }

    public static void setUpDriver() throws IOException {
        if (seleniumDriver == null) {
            seleniumDriver = new SeleniumDriver();
        }

    }

    public static void tearDown() {
        if (chromeDriver != null) {
            chromeDriver.close();
            chromeDriver.quit();
        }
        seleniumDriver = null;
    }

    public static void waitForPageToLoad() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

