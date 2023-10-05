/*
 * Copyright © 2023 Cask Data, Inc.
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

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Retry Util
 */
public class RetryUtils {

    /**
     * Implements a retry functionality for an action if required.
     *
     * @param retryDelay Delay in seconds after which each retry should be performed.
     * @param maxRetryDelay Max Delay in seconds after which retry logic should stop.
     * @param maxRetryCount Max Retry count after which retry logic should stop.
     * @param retryOperation Operation which needs to be performed while retrying.
     *
     */
    public static void retry(int retryDelay, int maxRetryDelay, int maxRetryCount, Supplier<Boolean> retryOperation)
      throws InterruptedException {
      int currentRetryDelay = 0;
      TimeUnit wait = TimeUnit.SECONDS;
      while (maxRetryCount > 0 && currentRetryDelay <= maxRetryDelay) {
        if (retryOperation.get()) {
          // If Operation succeeded, exit the retry loop.
          return;
        }

        currentRetryDelay += retryDelay;
        maxRetryCount--;

          wait.sleep(retryDelay);
        }
    }
}
