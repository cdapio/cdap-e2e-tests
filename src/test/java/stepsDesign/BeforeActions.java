package stepsDesign;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import utils.SeleniumDriver;

public class BeforeActions  {
    public static Scenario scenario;
	@Before
    public static void setUp() {
		//ExtentCucumberFormatter.initiateExtentCucumberFormatter();
    	System.out.println("Before");
       SeleniumDriver.setUpDriver();

    }
}
