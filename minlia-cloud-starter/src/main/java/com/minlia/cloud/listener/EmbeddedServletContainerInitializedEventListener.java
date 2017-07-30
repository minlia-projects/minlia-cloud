package com.minlia.cloud.listener;

import com.minlia.cloud.holder.ServerPortHolder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by user on 12/7/16.
 */
@Component
public class EmbeddedServletContainerInitializedEventListener implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {
    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
        ServerPortHolder.setPort(event.getSource().getPort());
    }
}
