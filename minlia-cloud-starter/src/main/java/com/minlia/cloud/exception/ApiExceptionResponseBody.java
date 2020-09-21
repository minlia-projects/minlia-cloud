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

import com.minlia.cloud.body.Response;
import com.minlia.cloud.code.Code;
import com.minlia.cloud.code.SystemCode;
import com.minlia.cloud.utils.Environments;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Data
public class ApiExceptionResponseBody extends Response {
    //Spring 返回
//        {
//            "timestamp": 1536935424270,
//                "status": 404,
//                "error": "Not Found",
//                "message": "Not Found",
//                "path": "/api/user/registration"
//        }

    protected String exception;

    protected String error;

    protected String path = getPath();

    public ApiExceptionResponseBody() {
        super();
    }

    public ApiExceptionResponseBody(HttpStatus status, String message) {
        super(status.value(), status.name(), message);
    }

    public ApiExceptionResponseBody(HttpStatus status, final String code, final String message) {
        super(status.value(), code, message);
    }


    public ApiExceptionResponseBody(HttpStatus status, Exception ex) {
        super(status.value(), status.name(), status.getReasonPhrase());
        if (!Environments.isProduction()) {
            this.exception = getException(ex);
            this.error = ex.getMessage();
        }
    }

    public ApiExceptionResponseBody(HttpStatus httpStatus, Code code, Exception ex) {
        super(httpStatus.value(), code.code(), code.message());
        if (Environments.isDevelopment()) {
            this.exception = getException(ex);
            this.error = ex.getMessage();
        }
    }

    public ApiExceptionResponseBody(HttpStatus status, final String code, final String message, Exception ex) {
        super(status.value(), code, message);
        if (Environments.isDevelopment()) {
            this.exception = getException(ex);
            this.error = ex.getMessage();
        }
    }

    public ApiExceptionResponseBody(final String code, Exception ex) {
        super(STATUS_FAILURE, code, SystemCode.Exception.INTERNAL_SERVER_ERROR.message());
        if (Environments.isDevelopment()) {
            this.exception = getException(ex);
            this.error = ex.getMessage();
        }
    }

    public ApiExceptionResponseBody(Code code, Exception ex) {
        super(STATUS_FAILURE, code.code(), code.message());
        if (Environments.isDevelopment()) {
            this.exception = getException(ex);
            this.error = ex.getMessage();
        }
    }

    public ApiExceptionResponseBody(final String code, final String message, Exception ex) {
        super(STATUS_FAILURE, code, message);
        if (Environments.isDevelopment()) {
            this.exception = getException(ex);
            this.error = ex.getMessage();
        }
    }

    private String getException(Exception e) {
        String exception = e.getClass().getSimpleName();
        if (null != e.getCause()) {
            exception += "：" + e.getCause().getMessage();
        }
        return exception;
    }

    private static String getPath() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        return httpServletRequest.getRequestURL().toString();
    }

}
