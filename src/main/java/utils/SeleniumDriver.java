package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.concurrent.TimeUnit;


public class SeleniumDriver {

    private static SeleniumDriver seleniumDriver;

    //initialize webdriver

    private static ChromeDriver chromeDriver;
    //initialize timeouts
    public static WebDriverWait waitDriver;
    public final static int TIMEOUT = 50;
    public final static int PAGE_LOAD_TIMEOUT = 50;
    public static URL url;
    public static String chromeLinux=System.getProperty("user.dir")+ ("//executables/linux/chromedriver");
    public static String chromeWindow=System.getProperty("user.dir")+ ("//executables/window/chromedriver.exe");
    public static String chromeMac=System.getProperty("user.dir")+ ("//executables/mac/chromedriver");
    public static String currentOS;

      SeleniumDriver() {
          currentOS=System.getProperty("os.name");
          System.out.println("++++++++"+currentOS);
          if(currentOS.toLowerCase().contains("mac")) {
              System.setProperty("webdriver.chrome.driver", chromeMac);

          }
          else if (currentOS.toLowerCase().contains("window")) {
              System.setProperty("webdriver.chrome.driver", chromeWindow);
          }

          else if (currentOS.toLowerCase().contains("Linux")) {
              System.setProperty("webdriver.chrome.driver", chromeLinux);

          }
          ChromeOptions options = new ChromeOptions();
          options.addArguments("--headless");
          options.addArguments("--window-size=1920x1080");
          options.addArguments("start-maximized"); // open Browser in maximized mode
          options.addArguments("disable-infobars"); // disabling infobars
          options.addArguments("--disable-extensions"); // disabling extensions
          options.addArguments("--disable-gpu"); // applicable to windows os only
          options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
          options.addArguments("--remote-debugging-port=9222");
          options.addArguments("--no-sandbox");
          chromeDriver=new ChromeDriver(options);
        chromeDriver.manage().window().maximize();
          HttpCommandExecutor executor = (HttpCommandExecutor) chromeDriver.getCommandExecutor();
            url = executor.getAddressOfRemoteServer();
        waitDriver = new WebDriverWait(chromeDriver, TIMEOUT);
        chromeDriver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        String window=chromeDriver.getWindowHandle();
          SessionId session = chromeDriver.getSessionId();
          System.out.println("Session iD:"+session);
        System.out.println("Window ->"+window);

    }



    public static SessionId session()
    {

        SessionId session = chromeDriver.getSessionId();
       // System.out.println("Check thos one------"+session);
        return session;
    }

    public static void getWaitDriver() {
    new WebDriverWait(chromeDriver, 40000).until(ExpectedConditions.invisibilityOf(chromeDriver.
             findElement(By.className("loading-bar"))));

    }



    public static void openPage(String url) {
        System.out.println(url);
        System.out.println(chromeDriver);
        chromeDriver.get(url);
    }

    public static WebDriver getDriver() {
        return chromeDriver;
    }

    public static void setUpDriver() {
        if (seleniumDriver == null)
            seleniumDriver = new SeleniumDriver();

    }

    public static void tearDown() {
        if (chromeDriver != null) {
            chromeDriver.close();
            chromeDriver.quit();
        }
        seleniumDriver = null;
    }
    public static void waitForPageToLoad()
    {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

