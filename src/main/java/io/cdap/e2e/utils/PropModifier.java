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
package io.cdap.e2e.utils;

import cucumber.api.event.EventHandler;
import cucumber.api.event.EventListener;
import cucumber.api.event.EventPublisher;
import cucumber.api.event.TestRunFinished;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Event listener to update properties on the run, called as a plugin by TestRunner.
 */
public class PropModifier implements EventListener {

  private static final Logger logger = LoggerFactory.getLogger(PropModifier.class);

  private static Properties pluginPropBackup;

  public PropModifier(String fileName) {
    appendToProps(fileName);
  }

  private void appendToProps(String fileName) {
    try {
      pluginPropBackup = (Properties) PluginPropertyUtils.pluginProperties.clone();
      PluginPropertyUtils.pluginProperties.load(PropModifier.class.getResourceAsStream("/" + fileName));
    } catch (Exception e) {
      logger.error("Error while reading file " + fileName + ": " + e);
    }
  }

  @Override
  public void setEventPublisher(EventPublisher eventPublisher) {
    //post action: reset properties to default
    eventPublisher.registerHandlerFor(TestRunFinished.class, runFinishedHandler);
  }

  private EventHandler<TestRunFinished> runFinishedHandler = new EventHandler<TestRunFinished>() {
    @Override
    public void receive(TestRunFinished event) {
      PluginPropertyUtils.pluginProperties = pluginPropBackup;
    }
  };
}
