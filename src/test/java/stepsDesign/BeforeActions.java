package stepsDesign;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import java.io.IOException;
import io.cdap.e2e.utils.SeleniumDriver;

public class BeforeActions  {
    public static Scenario scenario;
	@Before
    public void setUp(Scenario scenario) throws IOException {
		//ExtentCucumberFormatter.initiateExtentCucumberFormatter();
        this.scenario = scenario;
       SeleniumDriver.setUpDriver();

    }
}
