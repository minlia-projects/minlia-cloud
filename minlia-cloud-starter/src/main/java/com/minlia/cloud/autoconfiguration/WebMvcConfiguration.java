package com.minlia.cloud.autoconfiguration;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.minlia.cloud.jackson.MinliaLocalDateTimeDeserializer;
import com.minlia.cloud.utils.LocalDateUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static com.minlia.cloud.utils.LocalDateUtils.DATE_TIME_FORMATTER;

@EnableAutoConfiguration
@Configuration
@EnableWebMvc
public class WebMvcConfiguration implements WebMvcConfigurer {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/",
            "classpath:/resources/",
            "classpath:/static/",
            "classpath:/public/"};

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }


//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowCredentials(true)
//                .allowedHeaders(CorsConfiguration.ALL)
//                .allowedMethods(CorsConfiguration.ALL)
////                .allowedOrigins(CorsConfiguration.ALL)
//                .allowedOriginPatterns(CorsConfiguration.ALL)
//                //最大过期时间，默认为-1
//                .maxAge(3600);
//    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);
        // 允许访问的来源
        config.setAllowedOriginPatterns(Collections.singletonList(CorsConfiguration.ALL));
        // 允许访问的请求头
        config.addAllowedHeader(CorsConfiguration.ALL);
        // 允许访问的请求方式
        config.addAllowedMethod(CorsConfiguration.ALL);
        // 对接口配置跨域设置
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    /**
     * RequestContextListener注册
     */
    @Bean
    public ServletListenerRegistrationBean<RequestContextListener> requestContextListenerRegistration() {
        return new ServletListenerRegistrationBean<>(new RequestContextListener());
    }

    @Bean
    @Primary
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder
                .serializerByType(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)))
                .serializerByType(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)))
                .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)))
                .deserializerByType(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)))
                .deserializerByType(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)))
                .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
//        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
//        builder.serializationInclusion(JsonInclude.Include.NON_EMPTY);
//        builder.propertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

        // 序列换成json时,将所有的Long变成String
        builder.serializerByType(Long.class, ToStringSerializer.instance);
        // 序列换成json时,将所有的long变成string
//        builder.serializerByType(Long.TYPE, ToStringSerializer.instance);

        // 启用美化打印
        builder.indentOutput(true)
                .timeZone("GMT+8")                                                              // 解决时区差8小时问题
                .dateFormat(new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN))            // 设置全局的时间转化
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)              // 禁用序列化日期为timestamps
                .featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)           // 禁用遇到未知属性抛出异常
                .featuresToDisable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)    // 禁用将日期调整到时间区域
                .featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS)                    // 禁用空对象序列化
                .featuresToDisable(MapperFeature.DEFAULT_VIEW_INCLUSION)
                .featuresToEnable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)    // 启用空字符传为null
                .featuresToEnable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);               // 允许属性名称没有引号

        builder.modules(javaTimeModule());
        // https://github.com/spring-projects/spring-boot/issues/12389
        converters.add(0, new MappingJackson2HttpMessageConverter(builder.build()));
    }

    /**
     * LocalDateTime系列序列化和反序列化模块，继承自jsr310，我们在这里修改了日期格式
     *
     * @return
     */
    private JavaTimeModule javaTimeModule() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ISO_LOCAL_DATE));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ISO_LOCAL_TIME));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATE_TIME_FORMATTER));

//        javaTimeModule.addSerializer(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
//            @Override
//            public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//                jsonGenerator.writeNumber(LocalDateUtils.localDateTimeToTimestamp(localDateTime));
//            }
//        });
//
//        javaTimeModule.addSerializer(LocalDate.class, new JsonSerializer<LocalDate>() {
//            @Override
//            public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//                jsonGenerator.writeNumber(LocalDateUtils.localDateToTimestamp(localDate));
//            }
//        });


//        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATE_TIME_FORMATTER));
//        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE));
//        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ISO_LOCAL_TIME));

        //TODO
        javaTimeModule.addDeserializer(LocalDateTime.class, new MinliaLocalDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        javaTimeModule.addDeserializer(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                if (NumberUtils.isDigits(jsonParser.getText())) {
                    return LocalDateUtils.timestampTolocalDate(jsonParser.getLongValue());
                } else if (jsonParser.getText().contains(":")) {
                    return LocalDateTime.parse(jsonParser.getText(), DATE_TIME_FORMATTER).toLocalDate();
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
        return javaTimeModule;
    }

}