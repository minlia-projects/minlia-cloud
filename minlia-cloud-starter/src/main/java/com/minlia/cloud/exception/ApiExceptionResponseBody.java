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

    private String developerMessage;
    private String detail;

//    protected String trace;
//    protected String path;
//    protected String error;
//    protected String exception;

    public ApiExceptionResponseBody(){
    }

    public ApiExceptionResponseBody(final Integer code,final String message,Exception e) {
        this.code = code;
        this.message = message;
        this.detail = e.getMessage();
        this.developerMessage = e.getClass().getSimpleName();
    }

    public ApiExceptionResponseBody(final Integer code, final String message, final String developerMessage) {
        this.code = code;
        this.message = message;
        this.developerMessage = developerMessage;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(final String developerMessage) {
        this.developerMessage = developerMessage;
    }

}
