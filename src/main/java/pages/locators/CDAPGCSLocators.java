package pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CDAPGCSLocators {

    @FindBy(how = How.XPATH, using = "//*[@placeholder='Name used to identify this source for lineage']")
    public static WebElement referenceName;

    @FindBy(how = How.XPATH, using = "//*[@data-cy='project' and @class='MuiInputBase-input']")
    public static WebElement projectID;

    @FindBy(how = How.XPATH, using = "//*[@data-cy='path' and @class='MuiInputBase-input']")
    public static WebElement gcsPath;

    @FindBy(how = How.XPATH, using = "//*[@data-cy='sampleSize' and @class='MuiInputBase-input']")
    public static WebElement samplesize;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Select one')]")
    public static WebElement format;

    @FindBy(how = How.XPATH, using = "//*[@class='fa fa-remove']")
    public static WebElement closeButton;

    @FindBy(how= How.XPATH,using="//*[@title=\"GCS\"]//following-sibling::div")
    public static WebElement gcsProperties;

    @FindBy(how = How.XPATH, using = "//*[@data-cy='switch-skipHeader']")
    public static WebElement skipHeader;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Get Schema')]")
    public static WebElement getSchemaButton;




}
