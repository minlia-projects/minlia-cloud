//package com.minlia.cloud.i18n;
//
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.LocaleResolver;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.i18n.CookieLocaleResolver;
//import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
//
//import java.util.Locale;
//
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
//public class LocaleConfig extends WebMvcConfigurerAdapter {
//
//    //Cookie
//    @BeanApiException
//    public LocaleResolver localeResolver() {
//        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
//        localeResolver.setCookieName("localeCookie");
//        //设置默认区域
//        localeResolver.setDefaultLocale(Locale.ENGLISH);
//        localeResolver.setCookieMaxAge(3600);//设置cookie有效期.
//        return localeResolver;
//    }
//
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
//        // 参数名
//        lci.setParamName("lang");
//        return lci;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor());
//    }
//
//}