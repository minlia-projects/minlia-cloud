/*
 * Copyright © 2016 Minlia (http://oss.minlia.com/license/framework/2016)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.minlia.cloud.utils;

import com.google.common.base.Throwables;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Sleeps {
    public static void sleep(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            Throwables.propagate(e);
        }
    }

    public static void randomSleep(int duration, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(ThreadLocalRandom.current().nextInt(duration));
        } catch (InterruptedException e) {
            Throwables.propagate(e);
        }
    }

    public static String getThreadName() {
        return Thread.currentThread().getName();
    }
}
