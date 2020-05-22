package com.minlia.cloud.listener;

import com.minlia.cloud.holder.ServerPortHolder;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by user on 12/7/16.
 */
@Component
public class WebServerInitializedEventListener implements ApplicationListener<WebServerInitializedEvent> {
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        //
        ServerPortHolder.setPort(event.getSource().getPort());
    }
}
