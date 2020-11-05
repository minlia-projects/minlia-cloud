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
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author user
 * @date 4/25/16
 */
@Component
public class ContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        ContextHolder.applicationContext = ctx;
    }

    public static ApplicationContext getContext() {
        return ContextHolder.applicationContext;
    }

    /**
     * 获取容器中的实例
     *
     * @param beanId 注入在Spring容器中的bean的ID 默认为类名首字母小写
     * @param clazz  获取的bean的实际的类的class
     */
    public static <T> T getBean(String beanId, Class<T> clazz) {
        return applicationContext.getBean(beanId, clazz);
    }

    /**
     * 获取容器中的实例
     *
     * @param clazz 获取的bean的实际的类的class
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
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
        Environment environment = applicationContext.getBean(Environment.class);
        Iterable<ConfigurationPropertySource> sources = ConfigurationPropertySources.get(environment);
        Binder binder = new Binder(sources);
        BindResult<Properties> bindResult = binder.bind("system", Properties.class);
        Properties properties = bindResult.get();
        return properties;
    }
}