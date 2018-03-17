/*
 * Copyright © 2016 Minlia (http://oss.minlia.com/license/framework/2016)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.minlia.cloud.body.impl;

import com.minlia.cloud.body.ApiResponseBody;
import com.minlia.cloud.body.StatefulBody;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.http.HttpStatus;

/**
 * Created by user on 11/14/15.
 */
public class FailureResponseBody<T> extends ApiResponseBody {

    public FailureResponseBody() {
        super(StatefulBody.FAILURE,HttpStatus.INTERNAL_SERVER_ERROR.value(),StatefulBody.FAILURE_MESSAGE,null);
    }

    public FailureResponseBody(Integer code) {
        super(StatefulBody.FAILURE,code,StatefulBody.FAILURE_MESSAGE,null);
    }

    public FailureResponseBody(T payload) {
        super(StatefulBody.FAILURE,HttpStatus.INTERNAL_SERVER_ERROR.value(),StatefulBody.FAILURE_MESSAGE,payload);
    }

    public FailureResponseBody(Integer code, T payload) {
        super(StatefulBody.FAILURE,code,StatefulBody.FAILURE_MESSAGE,payload);
    }

    public FailureResponseBody(Integer code, String message, T payload) {
        super(StatefulBody.FAILURE,code,message,payload);
    }

    public static <T> FailureResponseBody.FailureResponseBodyBuilder<T> builder() {
        return new FailureResponseBody.FailureResponseBodyBuilder();
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

    public static class FailureResponseBodyBuilder<T> {

        FailureResponseBody instance = null;

        FailureResponseBodyBuilder() {
            instance = new FailureResponseBody();
        }

        public FailureResponseBody<T> build() {
            return instance;
        }

        protected boolean canEqual(Object other) {
            return EqualsBuilder.reflectionEquals(this, other);
        }

        public FailureResponseBody.FailureResponseBodyBuilder<T> code(Integer code) {
            instance.code = code;
            return this;
        }

        public FailureResponseBody.FailureResponseBodyBuilder<T> message(String message) {
            instance.message = message;
            return this;
        }

        public FailureResponseBody.FailureResponseBodyBuilder<T> payload(T payload) {
            instance.payload = payload;
            return this;
        }
    }

}
