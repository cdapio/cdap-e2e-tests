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

package stepsdesign;

import io.cdap.e2e.utils.SeleniumDriver;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
  public static File file;
  public static boolean beforeAllFlag = true;
  private static final Logger logger = LoggerFactory.getLogger(BeforeActions.class);

  @Before(order = 0)
  public void setUp(Scenario scenario) throws IOException {
    if (beforeAllFlag) {
      Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        SeleniumDriver.tearDown();
        logger.info("-----------------Selenium session closed------------------");
      }));
      beforeAllFlag = false;
      SeleniumDriver.setUpDriver();
      logger.info("-----------------Selenium session created------------------");
    }
    this.scenario = scenario;
    String[] tab = scenario.getId().split("/");
    int rawFeatureNameLength = tab.length;
    String featureName = tab[rawFeatureNameLength - 1].split(":")[0];
    featureName = featureName.replace(".", "-");
    String scenarioName = scenario.getName().replace(" ", "-").concat(".txt");
    new File("target/e2e-debug/" + featureName).mkdirs();
    file = new File("target/e2e-debug/" + featureName + "/" + scenarioName);
    Path path = Paths.get(String.valueOf(file));
    if (Files.deleteIfExists(path)) {
      file.createNewFile();
    }
    file.createNewFile();
  }
}
