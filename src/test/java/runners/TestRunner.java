/*
 * Copyright 2021 Google LLC
 */

package runners;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features={"src/test/resources/Test"},
		glue= {"stepsDesign","stepsLogging","stepsSecurity"},
		monochrome=true, 
				plugin={"pretty","html:target/cucumber-html-report","json:target/cucumber-reports/cucumber.json","junit:target/cucumber-reports/cucumber.xmls"}

				)


public class TestRunner {
}
