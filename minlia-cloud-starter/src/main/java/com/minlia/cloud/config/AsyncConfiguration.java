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
package com.minlia.cloud.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.minlia.cloud.config.async.CompletableExecutors;
import com.minlia.cloud.config.async.TimedCompletables;
import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurer;

public class AsyncConfiguration implements AsyncConfigurer {

    private final Logger logger = LoggerFactory.getLogger(AsyncConfiguration.class);

//  @Bean
//  public Executor asyncExecutor() {
//    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//    executor.setCorePoolSize(2);
//    executor.setMaxPoolSize(2);
//    executor.setQueueCapacity(500);
//    executor.setThreadNamePrefix("Minlia-X-");
//    executor.initialize();
//    return executor;
//  }

    @Override
    public Executor getAsyncExecutor() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("Minlia-Async-%d").build();
        return CompletableExecutors.completable(Executors.newFixedThreadPool(10, threadFactory));
    }

    @Bean(name = "timed")
    public Executor timeoutExecutor() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("timed-%d").build();
        return TimedCompletables.timed(Executors.newFixedThreadPool(10, threadFactory), Duration.ofSeconds(2));
    }

    @Override
     public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
     return (ex, method, params) -> logger.error("Uncaught async error", ex);
     }
}
