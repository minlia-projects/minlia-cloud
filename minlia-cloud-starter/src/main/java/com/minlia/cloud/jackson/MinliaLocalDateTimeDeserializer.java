package com.minlia.cloud.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.JSR310DateTimeDeserializerBase;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.minlia.cloud.utils.LocalDateUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class MinliaLocalDateTimeDeserializer extends JSR310DateTimeDeserializerBase<LocalDateTime> {

    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter DEFAULT_FORMATTER;
    public static final MinliaLocalDateTimeDeserializer INSTANCE;

    public MinliaLocalDateTimeDeserializer() {
        this(DEFAULT_FORMATTER);
    }

    public MinliaLocalDateTimeDeserializer(DateTimeFormatter formatter) {
        super(LocalDateTime.class, formatter);
    }

    protected JsonDeserializer<LocalDateTime> withDateFormat(DateTimeFormatter formatter) {
        return new LocalDateTimeDeserializer(formatter);
    }

    public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        if (NumberUtils.isDigits(parser.getText())) {
            return LocalDateUtils.timestampTolocalDateTime(parser.getLongValue());
        }

        if (parser.hasTokenId(6)) {
            String string = parser.getText().trim();
            if (string.length() == 0) {
                return null;
            }

            try {
                if (this._formatter == DEFAULT_FORMATTER && string.length() > 10 && string.charAt(10) == 'T') {
                    if (string.endsWith("Z")) {
                        return LocalDateTime.ofInstant(Instant.parse(string), ZoneOffset.ofHours(8));
//                        return LocalDateTime.ofInstant(Instant.parse(string), ZoneId.systemDefault());
                    }
                    return LocalDateTime.parse(string, DEFAULT_FORMATTER);
                }

                if (string.charAt(10) != 'T') {
                    return LocalDateTime.parse(string, LocalDateUtils.DATE_TIME_FORMATTER);
                } else {
                    return LocalDateTime.parse(string, this._formatter);
                }
            } catch (DateTimeException var10) {
                this._rethrowDateTimeException(parser, context, var10, string);
            }
        }

        if (parser.isExpectedStartArrayToken()) {
            if (parser.nextToken() == JsonToken.END_ARRAY) {
                return null;
            } else {
                int year = parser.getIntValue();
                int month = parser.nextIntValue(-1);
                int day = parser.nextIntValue(-1);
                int hour = parser.nextIntValue(-1);
                int minute = parser.nextIntValue(-1);
                if (parser.nextToken() != JsonToken.END_ARRAY) {
                    int second = parser.getIntValue();
                    if (parser.nextToken() != JsonToken.END_ARRAY) {
                        int partialSecond = parser.getIntValue();
                        if (partialSecond < 1000 && !context.isEnabled(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS)) {
                            partialSecond *= 1000000;
                        }

                        if (parser.nextToken() != JsonToken.END_ARRAY) {
                            throw context.wrongTokenException(parser, JsonToken.END_ARRAY, "Expected array to end.");
                        } else {
                            return LocalDateTime.of(year, month, day, hour, minute, second, partialSecond);
                        }
                    } else {
                        return LocalDateTime.of(year, month, day, hour, minute, second);
                    }
                } else {
                    return LocalDateTime.of(year, month, day, hour, minute);
                }
            }
        } else if (parser.hasToken(JsonToken.VALUE_EMBEDDED_OBJECT)) {
            return (LocalDateTime) parser.getEmbeddedObject();
        } else {
            throw context.wrongTokenException(parser, JsonToken.VALUE_STRING, "Expected array or string.");
        }
    }

    static {
        DEFAULT_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        INSTANCE = new MinliaLocalDateTimeDeserializer();
    }
}
