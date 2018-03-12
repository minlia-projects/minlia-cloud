package com.minlia.cloud.holder;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;

/**
 * Created by user on 4/25/16.
 */
public class ContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicatioContext;

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        ContextHolder.applicatioContext = ctx;
    }

    public static ApplicationContext getContext() {
        return ContextHolder.applicatioContext;
    }

//    public static RelaxedPropertyResolver getSystemProperty() {
//        if(null!=applicatioContext) {
//            Environment environment = applicatioContext.getBean(Environment.class);
//            return new RelaxedPropertyResolver(environment, "system.");
//        }else{
//            return null;
//        }
//    }
}
