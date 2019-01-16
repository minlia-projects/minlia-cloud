package com.minlia.cloud.autoconfiguration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebMvcConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/",
            "classpath:/resources/",
            "classpath:/static/",
            "classpath:/public/"};

    private ApplicationContext applicationContext;

    /**
     * 设置上下文
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

//    /**
//     * Thymeleaf模板资源解析器(自定义的需要做前缀绑定)
//     */
//    @Bean
//    @ConfigurationProperties(prefix = "spring.thymeleaf")
//    public SpringResourceTemplateResolver templateResolver() {
//        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
//        templateResolver.setCharacterEncoding("UTF-8");
//        templateResolver.setApplicationContext(this.applicationContext);
//        return templateResolver;
//    }
//
//    /**
//     * Thymeleaf标准方言解释器
//     */
//    @Bean
//    public SpringTemplateEngine templateEngine() {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver());
//        //支持spring EL表达式
//        templateEngine.setEnableSpringELCompiler(true);
//        return templateEngine;
//    }
//
//    /**
//     * 视图解析器
//     */
//    @Bean
//    public ThymeleafViewResolver thymeleafViewResolver() {
//        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
//        thymeleafViewResolver.setTemplateEngine(templateEngine());
//        thymeleafViewResolver.setCharacterEncoding("UTF-8");
//        return thymeleafViewResolver;
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
        //将swagger-ui.html 添加 到 resources目录下
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
    }

//    /**
//     * 解决跨域问题
//     *
//     * @param registry
//     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") // **代表所有路径
//                .allowedOrigins("*") // allowOrigin指可以通过的ip，*代表所有，可以使用指定的ip，多个的话可以用逗号分隔，默认为*
//                .allowedMethods("GET", "POST", "HEAD", "PUT", "DELETE") // 指请求方式 默认为*
//                .allowCredentials(false) // 支持证书，默认为true
//                .maxAge(3600) // 最大过期时间，默认为-1
//                .allowedHeaders("*");
//    }
//
//    /**
//     * RequestContextListener注册
//     */
//    @Bean
//    public ServletListenerRegistrationBean<RequestContextListener> requestContextListenerRegistration() {
//        return new ServletListenerRegistrationBean<>(new RequestContextListener());
//    }

}