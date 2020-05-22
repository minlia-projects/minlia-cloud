//package com.minlia.cloud.config.jackson;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.*;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
//import com.minlia.cloud.jackson.MinliaLocalDateTimeDeserializer;
//import com.minlia.cloud.utils.LocalDateUtils;
//import org.apache.commons.lang3.math.NumberUtils;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.annotation.Order;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.time.ZoneOffset;
//import java.time.format.DateTimeFormatter;
//
//import static com.minlia.cloud.utils.LocalDateUtils.DATE_TIME_FORMATTER;
//
///**
// * @author garen
// * @version 1.0
// * @description
// * @date 2019/4/28 2:39 PM
// */
////@EnableAutoConfiguration
////@Primary
//@Configuration
//@Order(1)
//public class ObjectMapperConfig {
//
//    /**
//     * Json序列化和反序列化转换器，用于转换Post请求体中的json以及将我们的对象序列化为返回响应的json
//     */
////  是在@ResponseBody转换json的时候不打印null的内容
////  @JsonInclude(JsonInclude.Include.NON_NULL)
//    @Bean
//    @Primary
//    public ObjectMapper objectMapper() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);               // 禁用序列化日期为timestamps
//        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);            // 禁用遇到未知属性抛出异常
//        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);     // 禁用将日期调整到时间区域
//        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);                     // 禁用空对象序列化
//        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);     // 启用空字符传为null
//        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);  // 允许属性名称没有引号
//        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);             // 启用美化打印
//        objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
////        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);               // 如果为空则不输出
//
//        objectMapper.registerModule(javaTimeModule());
//        return objectMapper;
//    }
//
//    /**
//     * LocalDateTime系列序列化和反序列化模块，继承自jsr310，我们在这里修改了日期格式
//     *
//     * @return
//     */
//    private JavaTimeModule javaTimeModule() {
//        JavaTimeModule javaTimeModule = new JavaTimeModule();
//        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATE_TIME_FORMATTER));
//        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ISO_LOCAL_DATE));
//        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ISO_LOCAL_TIME));
//
////        javaTimeModule.addSerializer(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
////            @Override
////            public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
////                jsonGenerator.writeNumber(LocalDateUtils.localDateTimeToTimestamp(localDateTime));
////            }
////        });
////
////        javaTimeModule.addSerializer(LocalDate.class, new JsonSerializer<LocalDate>() {
////            @Override
////            public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
////                jsonGenerator.writeNumber(LocalDateUtils.localDateToTimestamp(localDate));
////            }
////        });
//
//
////        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATE_TIME_FORMATTER));
////        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE));
////        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ISO_LOCAL_TIME));
//
//        javaTimeModule.addDeserializer(LocalDateTime.class, new MinliaLocalDateTimeDeserializer());
//        javaTimeModule.addDeserializer(LocalDate.class, new JsonDeserializer<LocalDate>() {
//            @Override
//            public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
//                if (NumberUtils.isDigits(jsonParser.getText())) {
//                    return LocalDateUtils.timestampTolocalDate(jsonParser.getLongValue());
//                } else if (jsonParser.getText().contains(":")) {
//                    return LocalDateTime.parse(jsonParser.getText(), DATE_TIME_FORMATTER).toLocalDate();
//                } else {
//                    return LocalDate.parse(jsonParser.getText());
//                }
//            }
//        });
//
//        javaTimeModule.addDeserializer(LocalTime.class, new JsonDeserializer<LocalTime>() {
//            @Override
//            public LocalTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
//                if (NumberUtils.isDigits(jsonParser.getText())) {
//                    return LocalDateUtils.timestampTolocalTime(jsonParser.getLongValue());
//                } else {
//                    return LocalTime.parse(jsonParser.getText());
//                }
//            }
//        });
//
//        //Date序列化和反序列化
////        javaTimeModule.addSerializer(Date.class, new JsonSerializer<Date>() {
////            @Override
////            public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
////                SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
////                String formattedDate = formatter.format(date);
////                jsonGenerator.writeString(formattedDate);
////            }
////        });
////        javaTimeModule.addDeserializer(Date.class, new JsonDeserializer<Date>() {
////            @Override
////            public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
////                SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
////                String date = jsonParser.getText();
////                try {
////                    return format.parse(date);
////                } catch (ParseException e) {
////                    throw new RuntimeException(e);
////                }
////            }
////        });
//        return javaTimeModule;
//    }
//
//}
