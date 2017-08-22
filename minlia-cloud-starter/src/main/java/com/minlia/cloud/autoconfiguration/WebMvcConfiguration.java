package com.minlia.cloud.autoconfiguration;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import java.util.List;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

//    @Autowired
//    private AuditLogInterceptor auditLogInterceptor;

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(auditLogInterceptor);
//    }
//
//
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        argumentResolvers.add(new FilteringSpecificationArgumentResolver());
//    }


//  @Override
//  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//    GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
//    converters.add(gsonHttpMessageConverter);
//  }








//  @Override
//  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//    super.configureMessageConverters(converters);
//
//    FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//
//    FastJsonConfig fastJsonConfig = new FastJsonConfig();
//    fastJsonConfig.setSerializerFeatures(
//        SerializerFeature.PrettyFormat
//    );
//    fastConverter.setFastJsonConfig(fastJsonConfig);
//
//    converters.add(fastConverter);
//  }




}