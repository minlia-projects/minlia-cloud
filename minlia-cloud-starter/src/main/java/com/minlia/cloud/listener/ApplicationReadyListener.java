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
package com.minlia.cloud.listener;

import com.minlia.cloud.holder.ServerHolder;
import com.minlia.cloud.utils.Environments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Priority;

/**
 * 应用程序启动监听器
 */
@Component
@Priority(value = Ordered.HIGHEST_PRECEDENCE)
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {

    public static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyListener.class);

    @Autowired
    private ServerProperties properties;

    public static final String LOCALHOST = "127.0.0.1";

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        LOGGER.debug("Application Ready");
        String ctx = properties.getServlet().getContextPath();
        if (StringUtils.isEmpty(ctx)) {
            ctx = "/";
        } else {
            ctx = ctx + "/";
        }

        String portPart = "";
        if (null != ServerHolder.getPort() && ServerHolder.getPort() != 80) {
            portPart = ":" + ServerHolder.getPort();
        }

        String host = ServerHolder.getHost();
        if (StringUtils.isEmpty(host)) {
            host = LOCALHOST;
        }
        if (!Environments.isProduction()) {
            host = LOCALHOST;
        }
        String message = "";
        message += "Access URLS: ";
        message += String.format("%s%s%s%s%s", "http://", host, portPart, ctx, "");
        message += String.format("    %s%s%s%s%s", "http://", host, portPart, ctx, "swagger-ui/index.html");
        LOGGER.info(message);
    }
}