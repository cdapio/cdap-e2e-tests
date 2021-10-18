/*
 * Copyright 2021 Google LLC
 */

package stepsdesign;

import io.cdap.e2e.utils.SeleniumDriver;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents BeforeActions
 */
public class BeforeActions {
    public static Scenario scenario;
    public static File myObj;

    @Before
    public void setUp(Scenario scenario) throws IOException {
        //ExtentCucumberFormatter.initiateExtentCucumberFormatter();
        this.scenario = scenario;
        SeleniumDriver.setUpDriver();
        String[] tab = scenario.getId().split("/");
        int rawFeatureNameLength = tab.length;
        String featureName = tab[rawFeatureNameLength - 1].split(":")[0];
        featureName = featureName.replace(".", "-");
        String scenarioName = scenario.getName().replace(" ", "-").concat(".txt");
        new File("target/e2e-debug/" + featureName).mkdirs();
        myObj = new File("target/e2e-debug/" + featureName + "/" + scenarioName);
        Path path = Paths.get(String.valueOf(myObj));
        if (Files.deleteIfExists(path)) {
            myObj.createNewFile();
        }
        myObj.createNewFile();

    }
}
