package com.minlia.cloud.listener;

import com.minlia.cloud.holder.ServerHolder;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 初始化完成监听器
 *
 * @author user
 * @date 12/7/16
 */
@Component
public class WebServerInitializedEventListener implements ApplicationListener<WebServerInitializedEvent> {

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        ServerHolder.setPort(event.getWebServer().getPort());
        ServerHolder.setHost(getHost());
    }

    public String getHost() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return address.getHostAddress();
    }

}