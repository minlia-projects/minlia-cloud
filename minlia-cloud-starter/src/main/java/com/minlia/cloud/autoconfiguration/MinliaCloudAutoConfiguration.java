package com.minlia.cloud.autoconfiguration;

import com.minlia.cloud.config.AsyncConfiguration;
import com.minlia.cloud.holder.ContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan(basePackages = "com.minlia.*")
@Configuration
@ConditionalOnClass(MinliaCloudAutoConfiguration.class)
@Slf4j
public class MinliaCloudAutoConfiguration {

    public MinliaCloudAutoConfiguration() {
        log.debug("MinliaCloudAutoConfiguration Starting Autoconfiguration...");
    }

    @Bean
    @ConditionalOnMissingBean(ContextHolder.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ContextHolder contextHolder() {
        ContextHolder contextHolder = new ContextHolder();
        return contextHolder;
    }

//    @EnableWebMvc
//    @Configuration
//    @Import(WebMvcConfiguration.class)
//    @ConditionalOnMissingBean(WebMvcConfiguration.class)
//    public static class EnableMinliaWebMvcConfig {
//
//    }

    @Configuration
    @EnableAsync
    @EnableScheduling
    @EnableAspectJAutoProxy(proxyTargetClass = true)
    @Import(AsyncConfiguration.class)
    @ConditionalOnMissingBean(AsyncConfiguration.class)
    public static class EnableMinliaAsyncConfig {
    }

//    @Configuration
//    @Import(DozerConfiguration.class)
//    @ConditionalOnMissingBean(DozerConfiguration.class)
//    public static class EnableMinliaDozerConfiguration {
//    }

    //No Locale Configuration
//    @Configuration
//    @Import(LocaleConfiguration.class)
//    @ConditionalOnMissingBean(LocaleConfiguration.class)
//    public static class StarLionLocaleConfiguration {
//    }


    @Configuration
    @Import(WebMvcConfiguration.class)
//    @ConditionalOnMissingBean(WebMvcConfiguration.class)
    public static class StarLionWebMvcConfig {
//        @Bean
//        AuditLogInterceptor localInterceptor() {
//            return new AuditLogInterceptor();
//        }

//        @Configuration
//        public class CustomConfiguration {
//            @Bean
//            public HttpMessageConverters customConverters() {
//                Collection<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//                GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
//                messageConverters.add(gsonHttpMessageConverter);
//                return new HttpMessageConverters(true, messageConverters);
//            }
//        }


    }




//    @Configuration
//    @ConditionalOnClass(Gson.class)
//    @ConditionalOnMissingClass(value = "com.fasterxml.jackson.core.JsonGenerator")
//    @ConditionalOnBean(Gson.class)
//    protected static class GsonHttpMessageConverterConfiguration {
//
//        @Bean
//        @ConditionalOnMissingBean
//        public GsonHttpMessageConverter gsonHttpMessageConverter(Gson gson) {
//            GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
//            converter.setGson(gson);
//            return converter;
//        }
//
//    }

}
