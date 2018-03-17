package com.minlia.cloud.body.impl;

import com.minlia.cloud.body.ApiResponseBody;
import com.minlia.cloud.body.StatefulBody;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.http.HttpStatus;

public class SuccessResponseBody<T> extends ApiResponseBody {

    public SuccessResponseBody() {
        super(StatefulBody.SUCCESS,HttpStatus.OK.value(),StatefulBody.SUCCESS_MESSAGE,null);
    }

    public SuccessResponseBody(Integer code) {
        super(StatefulBody.SUCCESS,code,StatefulBody.SUCCESS_MESSAGE,null);
    }

    public SuccessResponseBody(T payload) {
        super(StatefulBody.SUCCESS,HttpStatus.OK.value(),StatefulBody.SUCCESS_MESSAGE,payload);
    }

    public SuccessResponseBody(Integer code, T payload) {
        super(StatefulBody.SUCCESS,code,StatefulBody.SUCCESS_MESSAGE,payload);
    }

    public SuccessResponseBody(Integer code, String message, T payload) {
        super(StatefulBody.SUCCESS,code,message,payload);
    }

    public static <T> SuccessResponseBody.SuccessResponseBodyBuilder<T> builder() {
        return new SuccessResponseBody.SuccessResponseBodyBuilder<>();
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, false);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public static class SuccessResponseBodyBuilder<T> {

        SuccessResponseBody instance = null;

        SuccessResponseBodyBuilder() {
            instance = new SuccessResponseBody();
        }

        public SuccessResponseBody<T> build() {
            return instance;
        }

        public SuccessResponseBody.SuccessResponseBodyBuilder<T> code(Integer code) {
            instance.code = code;
            return this;
        }

        public SuccessResponseBody.SuccessResponseBodyBuilder<T> message(String message) {
            instance.message = message;
            return this;
        }

        public SuccessResponseBody.SuccessResponseBodyBuilder<T> payload(T payload) {
            instance.payload = payload;
            return this;
        }

    }
}
