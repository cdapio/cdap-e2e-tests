/*
 * Copyright 2021 Google LLC
 */

package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
  features = {"src/test/resources/Test"},
  glue = {"stepsdesign", "stepsLogging", "stepsSecurity"},
  monochrome = true,
  plugin = {
    "pretty",
    "html:target/cucumber-html-report",
    "json:target/cucumber-reports/cucumber.json",
    "junit:target/cucumber-reports/cucumber.xmls"}

)


public class TestRunner {
}
