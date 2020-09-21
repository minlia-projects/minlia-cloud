package com.minlia.cloud.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.minlia.cloud.utils.LocalDateUtils;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author garen
 */
public class MinliaLocalDateTimeDeserializer extends LocalDateTimeDeserializer {

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public MinliaLocalDateTimeDeserializer(DateTimeFormatter formatter) {
        super(formatter);
    }

    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        // 处理时间戳,JsonToken.VALUE_NUMBER_INT
        if (parser.hasTokenId(JsonToken.VALUE_NUMBER_INT.id())) {
            return LocalDateUtils.timestampTolocalDateTime(parser.getLongValue());
        } else if (parser.hasTokenId(6)) {
            String string = parser.getText().trim();
            if (string.length() == 0) {
                return !this.isLenient() ? this._failForNotLenient(parser, context, JsonToken.VALUE_STRING) : null;
            } else {
                try {
                    if (this._formatter == DEFAULT_FORMATTER && string.length() > 10 && string.charAt(10) == 'T') {
                        if (string.endsWith("Z")) {
                            return LocalDateTime.ofInstant(Instant.parse(string), ZoneOffset.ofHours(8));
//                            return LocalDateTime.ofInstant(Instant.parse(string), ZoneId.systemDefault());
                        } else {
                            return LocalDateTime.parse(string, DEFAULT_FORMATTER);
                        }
                    }

                    if (string.charAt(10) != 'T') {
                        return LocalDateTime.parse(string, LocalDateUtils.DATE_TIME_FORMATTER);
                    } else {
                        return LocalDateTime.parse(string, this._formatter);
                    }
                } catch (DateTimeException var12) {
                    return (LocalDateTime) this._handleDateTimeException(context, var12, string);
                }
            }
        }
        return super.deserialize(parser, context);
    }

}
