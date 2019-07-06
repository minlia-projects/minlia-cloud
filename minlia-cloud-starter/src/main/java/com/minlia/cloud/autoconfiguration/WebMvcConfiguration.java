package com.minlia.cloud.autoconfiguration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.minlia.cloud.resolver.UnderlineToCamelArgumentResolver;
import com.minlia.cloud.utils.LocalDateUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    /**
     * 添加参数解析，将参数的形式从下划线转化为驼峰
     *
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new UnderlineToCamelArgumentResolver());
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

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        builder.serializationInclusion(JsonInclude.Include.NON_EMPTY);
//        builder.propertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

        // 启用美化打印
        builder.indentOutput(true)
//                .dateFormat(new SimpleDateFormat("yyyy-MM-dd"))
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)              // 禁用序列化日期为timestamps
                .featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)           // 禁用遇到未知属性抛出异常
                .featuresToDisable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)    // 禁用将日期调整到时间区域
                .featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS)                    // 禁用空对象序列化
                .featuresToDisable(MapperFeature.DEFAULT_VIEW_INCLUSION)
                .featuresToEnable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)    // 启用空字符传为null
                .featuresToEnable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);               // 允许属性名称没有引号

        builder.modules(javaTimeModule());
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
//        converters.add(new MappingJackson2XmlHttpMessageConverter(builder.createXmlMapper(true).build()));
    }

    /**
     * LocalDateTime系列序列化和反序列化模块，继承自jsr310，我们在这里修改了日期格式
     *
     * @return
     */
    private JavaTimeModule javaTimeModule() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
//        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATE_TIME_FORMATTER));
//        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ISO_LOCAL_DATE));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ISO_LOCAL_TIME));

        javaTimeModule.addSerializer(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
            @Override
            public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//                jsonGenerator.writeNumber(localDateTime.toEpochSecond(ZoneOffset.ofHours(8)));
                jsonGenerator.writeNumber(LocalDateUtils.localDateTimeToTimestamp(localDateTime));
            }
        });

        javaTimeModule.addSerializer(LocalDate.class, new JsonSerializer<LocalDate>() {
            @Override
            public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeNumber(LocalDateUtils.localDateToTimestamp(localDate));
            }
        });


//        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATE_TIME_FORMATTER));
//        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE));
//        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ISO_LOCAL_TIME));

        javaTimeModule.addDeserializer(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                if (NumberUtils.isDigits(jsonParser.getText())) {
                    return LocalDateUtils.timestampTolocalDateTime(jsonParser.getLongValue());
                } else {
                    if (jsonParser.getText().contains("T")) {
                        return LocalDateTime.parse(jsonParser.getText(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                    } else {
                        return LocalDateTime.parse(jsonParser.getText(), LocalDateUtils.DATE_TIME_FORMATTER);
                    }
                }
            }
        });

        javaTimeModule.addDeserializer(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                if (NumberUtils.isDigits(jsonParser.getText())) {
                    return LocalDateUtils.timestampTolocalDate(jsonParser.getLongValue());
                } else {
                    return LocalDate.parse(jsonParser.getText());
                }
            }
        });

        javaTimeModule.addDeserializer(LocalTime.class, new JsonDeserializer<LocalTime>() {
            @Override
            public LocalTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                if (NumberUtils.isDigits(jsonParser.getText())) {
                    return LocalDateUtils.timestampTolocalTime(jsonParser.getLongValue());
                } else {
                    return LocalTime.parse(jsonParser.getText());
                }
            }
        });

        //Date序列化和反序列化
//        javaTimeModule.addSerializer(Date.class, new JsonSerializer<Date>() {
//            @Override
//            public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//                SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
//                String formattedDate = formatter.format(date);
//                jsonGenerator.writeString(formattedDate);
//            }
//        });
//        javaTimeModule.addDeserializer(Date.class, new JsonDeserializer<Date>() {
//            @Override
//            public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
//                SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
//                String date = jsonParser.getText();
//                try {
//                    return format.parse(date);
//                } catch (ParseException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
        return javaTimeModule;
    }

}