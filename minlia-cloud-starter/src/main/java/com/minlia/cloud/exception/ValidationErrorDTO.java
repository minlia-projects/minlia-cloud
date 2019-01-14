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

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDTO extends ApiExceptionResponseBody {

    private List<FieldErrorDTO> errorDetails = new ArrayList<FieldErrorDTO>();

    public final void addFieldError(final String path, final String message) {
        final FieldErrorDTO error = new FieldErrorDTO(path, message);
        errorDetails.add(error);
    }

    public final List<FieldErrorDTO> getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(List<FieldErrorDTO> errorDetails) {
        this.errorDetails = errorDetails;
    }

    @Override
    public final String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ValidationErrorDTO [errorDetails=").append(errorDetails).append("]");
        return builder.toString();
    }

}
