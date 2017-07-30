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
package com.minlia.cloud.config.async;

import java.time.Duration;
import java.util.concurrent.*;

public class TimedCompletables {

    public static Executor timed(ExecutorService executorService, Duration duration) {
        return new TimeOutExecutorService(executorService, duration);
    }

    static class TimeOutExecutorService extends CompletableExecutors.DelegatingCompletableExecutorService {
        private final Duration timeout;
        private final ScheduledExecutorService schedulerExecutor;

        TimeOutExecutorService(ExecutorService delegate, Duration timeout) {
            super(delegate);
            this.timeout = timeout;
            schedulerExecutor = Executors.newScheduledThreadPool(1);
        }

        // http://stackoverflow.com/questions/23575067/timeout-with-default-value-in-java-8-completablefuture/24457111#24457111
        @Override public <T> CompletableFuture<T> submit(Callable<T> task) {
            CompletableFuture<T> cf = new CompletableFuture<>();
            Future<?> future = delegate.submit(() -> {
                try {
                    cf.complete(task.call());
                } catch (CancellationException e) {
                    cf.cancel(true);
                } catch (Throwable ex) {
                    cf.completeExceptionally(ex);
                }
            });

            schedulerExecutor.schedule(() -> {
                if (!cf.isDone()) {
                    cf.completeExceptionally(new TimeoutException("Timeout after " + timeout));
                    future.cancel(true);
                }
            }, timeout.toMillis(), TimeUnit.MILLISECONDS);
            return cf;
        }
    }
}
