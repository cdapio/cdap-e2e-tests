package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.*;
import java.util.Properties;

public class SeleniumHelper {

    static String path;

    static {
        try {
            path = SeleniumHelper.readParameters("DownloadPath");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void folderName(String name) {

        File f1 = new File("src/test/resources/" + name);
        //Creating a folder using mkdir() method
        boolean bool = f1.mkdir();
        if (bool) {
            System.out.println("Folder is created successfully");
        } else {
            System.out.println("Error Found!");
        }
    }

    public static void filemove(String name, String input) throws IOException, InterruptedException {
        String aa = path + "/" + name + "/" + input;
        Thread.sleep(2000);
        File file = new File(path + "schema.json");
        if (file.renameTo
                (new File(path + "/" + name + "/" + input))) {
            // if file copied successfully then delete the original file
            file.delete();
            System.out.println("File moved successfully");
        } else {
            System.out.println("Failed to move the file");
        }

    }

    public static boolean isElementPresent(WebElement webElement) {
        try {
            boolean isPresent = webElement.isDisplayed();
            return isPresent;
        } catch (NoSuchElementException e) {
            return false;
        }

    }


    public static void dragAndDrop(WebElement from, WebElement to) {
        Actions act = new Actions(SeleniumDriver.getDriver());

        //Dragged and dropped.
        act.dragAndDrop(from, to).build().perform();
    }

    public static void clickObject(WebElement element) {
        element.click();
    }

    public static void sendKeys(WebElement element, String keys) {
        element.sendKeys(keys);
    }

    public static boolean waitElementInvisible(String element) throws InterruptedException {
        Thread.sleep(7000);
        WebDriverWait wait = new WebDriverWait(SeleniumDriver.getDriver(), 60);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(element)));
        return true;
    }

    public static String readParameters(String property) throws IOException {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = ClassLoader.getSystemClassLoader().getResourceAsStream("ConnectionParameters.properties");
            prop.load(input);
            return prop.getProperty(property);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property;
    }

}