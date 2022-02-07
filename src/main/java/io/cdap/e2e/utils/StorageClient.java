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

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageException;
import com.google.cloud.storage.StorageOptions;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Represents Google Cloud Storage client.
 */
public class StorageClient {

  private static Storage storageService = null;

  private static Storage getStorageService() throws IOException {
    return (null == storageService) ? StorageOptions.newBuilder().setProjectId(
      PluginPropertyUtils.pluginProp(ConstantsUtil.PROJECT_ID)).build().getService() : storageService;
  }

  public static Page<Bucket> listBuckets() throws IOException {
    return getStorageService().list();
  }

  public static Page<Blob> listObjects(String bucketName) throws IOException {
    return getStorageService().list(bucketName);
  }

  public static Page<Blob> listObjectsWithPrefix(
    String bucketName, String directoryPrefix) throws IOException {
    return getStorageService().list(bucketName, Storage.BlobListOption.prefix(directoryPrefix),
        Storage.BlobListOption.currentDirectory());
  }

  public static boolean deleteBucket(String bucketName) throws IOException {
    return getStorageService().get(bucketName).delete();
  }

  public static boolean deleteObject(String bucketName, String objectName) throws IOException {
    return getStorageService().delete(bucketName, objectName);
  }

  public static Blob getObjectMetadata(String bucketName, String blobName)
    throws StorageException, IOException {
    return getStorageService().get(bucketName, blobName, Storage.BlobGetOption.fields(Storage.BlobField.values()));
  }

  public static Bucket getBucketMetadata(String bucketName) throws IOException {
    return getStorageService().get(bucketName, Storage.BucketGetOption.fields(Storage.BucketField.values()));
  }

  public static String getBucketCmekKey(String bucketName) throws IOException {
    return getBucketMetadata(bucketName).getDefaultKmsKeyName();
  }

  public static String getObjectCmekKey(String bucketName, String blobName) throws IOException {
    return getObjectMetadata(bucketName, blobName).getKmsKeyName();
  }

  public static Bucket createBucket(String bucketName) throws IOException {
    return getStorageService().create(BucketInfo.of(bucketName));
  }

  public static Blob uploadObject(String bucketName, String objectName, String filePath)
    throws IOException, URISyntaxException {
    return getStorageService().create(
      BlobInfo.newBuilder(BlobId.of(bucketName, objectName)).build(),
      Files.readAllBytes(Paths.get(StorageClient.class.getResource("/" + filePath).toURI())));
  }

}
