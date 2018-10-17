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
import com.minlia.cloud.code.SystemCode;
import lombok.Data;

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

//    protected String path;

    public ApiExceptionResponseBody() {
        super();
    }

    public ApiExceptionResponseBody(final Integer code, Exception e) {
        super(FAILURE, code, SystemCode.Exception.INTERNAL_SERVER_ERROR.message());
//        if (!Environments.isProduction()) {
            this.exception = e.getClass().getSimpleName() + "：" + e.getCause().getMessage();
            this.error = e.getMessage();
//        }
    }

    public ApiExceptionResponseBody(final Integer code, final String message, Exception e) {
        super(FAILURE, code, message);
//        if (!Environments.isProduction()) {
            this.exception = e.getClass().getSimpleName() + "：" + e.getCause().getMessage();
            this.error = e.getMessage();
//        }
    }

}
