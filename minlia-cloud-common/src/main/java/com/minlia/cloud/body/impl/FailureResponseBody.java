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

/**
 * Created by user on 11/14/15.
 */

public class FailureResponseBody<T> extends ApiResponseBody {


    public FailureResponseBody() {
        this.setMessage(StatefulBody.FAILURE_MESSAGE);
        this.setCode(StatefulBody.FAILURE);
        this.setStatus(StatefulBody.FAILURE);
        this.setPayload(super.payload);
    }

    public FailureResponseBody(Integer code, Integer status, String message, T payload) {
        super(StatefulBody.FAILURE, StatefulBody.FAILURE, StatefulBody.FAILURE_MESSAGE, payload);
        this.code = code;
        this.status = status;
        this.message = message;
        this.payload = payload;
    }

    public FailureResponseBody(Integer code, Integer status, T payload) {
        super(StatefulBody.FAILURE, StatefulBody.FAILURE, StatefulBody.FAILURE_MESSAGE, (Object) null);
        this.code = code;
        this.status = status;
        this.payload = payload;
    }

    public FailureResponseBody(Integer code, T payload) {
        super(StatefulBody.FAILURE, StatefulBody.FAILURE, StatefulBody.FAILURE_MESSAGE, (Object) null);
        this.code = code;
        this.payload = payload;
    }

    public FailureResponseBody(Integer code) {
        super(StatefulBody.FAILURE, StatefulBody.FAILURE, StatefulBody.FAILURE_MESSAGE, (Object) null);
        this.code = code;
    }

    public FailureResponseBody(T payload) {
        super(StatefulBody.FAILURE, StatefulBody.FAILURE, StatefulBody.FAILURE_MESSAGE, (Object) null);
        this.payload = payload;
    }

    public static <T> FailureResponseBody.FailureResponseBodyBuilder<T> builder() {
        return new FailureResponseBody.FailureResponseBodyBuilder();
    }

    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, false);
    }

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

        public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this, false);
        }

        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }


        public FailureResponseBody.FailureResponseBodyBuilder<T> code(Integer code) {
            instance.code = code;
            return this;
        }

        public FailureResponseBody.FailureResponseBodyBuilder<T> status(Integer status) {
            instance.status = status;
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
