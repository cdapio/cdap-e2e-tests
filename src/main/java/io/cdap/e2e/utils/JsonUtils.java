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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * JsonUtils contains the json helper functions.
 */
public class JsonUtils {
  private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

  public static Map<String, String> convertKeyValueJsonArrayToMap(String json) {
    Map<String, String> keyValueMap = new HashMap<>();
    try {
      JsonArray jsonArray = (JsonArray) new JsonParser().parse(json);
      for (JsonElement jsonElement : jsonArray) {
        keyValueMap.put(jsonElement.getAsJsonObject().get("key").getAsString(),
                        jsonElement.getAsJsonObject().get("value").getAsString());
      }
    } catch (JsonSyntaxException e) {
      logger.error("JsonUtils : Error while converting to Map - " + e);
    } catch (Exception e) {
      logger.error("JsonUtils : Error while converting to Map - key or value node not present in input json - " + e);
    }
    return keyValueMap;
  }

  public static int countJsonNodeSize(String json, String node) {
    int size = 0;
    try {
      JsonObject jsonObject = (JsonObject) new JsonParser().parse(json);
      if (jsonObject.get(node).isJsonArray()) {
        JsonArray dataObject = jsonObject.getAsJsonArray(node);
        size = dataObject.size();
      } else if (!jsonObject.get(node).isJsonNull()) {
        size = 1;
      }
    } catch (JsonSyntaxException e) {
      logger.error("JsonUtils : Error while parsing JsonString - " + e);
    } catch (NullPointerException e) {
      logger.error("JsonUtils : Node \"" + node + "\" is not present in input json " + e);
    }
    return size;
  }
}
