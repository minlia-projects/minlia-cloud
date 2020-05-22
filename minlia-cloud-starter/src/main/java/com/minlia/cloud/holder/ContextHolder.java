package com.minlia.cloud.holder;

import org.springframework.beans.BeansException;
//import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;

import java.util.Properties;

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

    public static Properties getSystemProperty() {
        Environment environment = applicatioContext.getBean(Environment.class);
        Iterable<ConfigurationPropertySource> sources = ConfigurationPropertySources.get(environment);
        Binder binder = new Binder(sources);
        BindResult<Properties> bindResult = binder.bind("system", Properties.class);
        Properties properties = bindResult.get();
        return properties;
    }
}
