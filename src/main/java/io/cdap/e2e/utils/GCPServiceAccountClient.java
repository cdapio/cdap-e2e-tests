/*
 * Copyright © 2022 Cask Data, Inc.
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

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.iam.v1.Iam;
import com.google.api.services.iam.v1.IamScopes;
import com.google.api.services.iam.v1.model.CreateServiceAccountKeyRequest;
import com.google.api.services.iam.v1.model.ServiceAccountKey;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GCP Service Account interaction client
 */
public class GCPServiceAccountClient {

  private static final Logger logger = LoggerFactory.getLogger(GCPServiceAccountClient.class);

  public static List<ServiceAccountKey> getServiceAccountKeys(String projectId, String serviceAccountName)
    throws GeneralSecurityException, IOException {
    String serviceAccountEmail = serviceAccountName + "@" + projectId + ".iam.gserviceaccount.com";
    return
      initIAMService()
        .projects()
        .serviceAccounts()
        .keys()
        .list("projects/-/serviceAccounts/" + serviceAccountEmail)
        .execute()
        .getKeys();
  }

  public static Map<String, String> createServiceAccountKey(String projectId, String serviceAccountName)
    throws GeneralSecurityException, IOException {
    String serviceAccountEmail = serviceAccountName + "@" + projectId + ".iam.gserviceaccount.com";
    ServiceAccountKey key =
      initIAMService()
        .projects()
        .serviceAccounts()
        .keys()
        .create(
          "projects/-/serviceAccounts/" + serviceAccountEmail,
          new CreateServiceAccountKeyRequest())
        .execute();
    String jsonKeyFile = new String(Base64.getDecoder().decode(key.getPrivateKeyData()));
    String keyName = key.getName();
    logger.info("Service Account Key created successfully : " + keyName);
    Map<String, String> serviceAccountKeyDetails = new HashMap<>();
    serviceAccountKeyDetails.put("KeyName", keyName);
    serviceAccountKeyDetails.put("JsonKeyFile", jsonKeyFile);
    return serviceAccountKeyDetails;
  }

  public static void deleteServiceAccountKey(String keyToDelete) throws GeneralSecurityException, IOException {
    initIAMService().projects().serviceAccounts().keys().delete(keyToDelete).execute();
    logger.info("Deleted Service Account key: " + keyToDelete);
  }

  private static Iam initIAMService() throws GeneralSecurityException, IOException {
    GoogleCredentials credential =
      GoogleCredentials.getApplicationDefault()
        .createScoped(Collections.singleton(IamScopes.CLOUD_PLATFORM));
    return new Iam.Builder(
      GoogleNetHttpTransport.newTrustedTransport(),
      GsonFactory.getDefaultInstance(),
      new HttpCredentialsAdapter(credential))
      .setApplicationName("service-account-keys")
      .build();
  }
}
