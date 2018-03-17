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
package com.minlia.cloud.exception;

import com.minlia.cloud.body.impl.FailureResponseBody;
import lombok.Data;

@Data
public class ApiExceptionResponseBody extends FailureResponseBody {

    private String detail;

    protected String exception;

    //    protected String trace;
//    protected String path;
//    protected String error;

    public ApiExceptionResponseBody(){
    }

    public ApiExceptionResponseBody(final Integer code,Exception e) {
        this.code = code;
        this.message = e.getMessage();
        this.detail = String.format("%s with message: [%s]", e.getClass().getSimpleName(), message);
        this.exception = e.getClass().getSimpleName();
    }

    public ApiExceptionResponseBody(final Integer code,final String message,Exception e) {
        this.code = code;
        this.message = message;
        this.detail = String.format("%s with message: [%s]", e.getClass().getSimpleName(), message);
        this.exception = e.getClass().getSimpleName();
    }

}
