package com.minlia.cloud.utils;
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

import com.minlia.cloud.exception.ApiException;
import org.springframework.http.HttpStatus;

/**
 * Simple static methods to be called at the start of your own methods to verify correct arguments and state. If the Precondition fails, an {@link HttpStatus} code is thrown
 */
public final class ApiPreconditions {

    private ApiPreconditions() {
        throw new AssertionError();
    }

    // API

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param reference
     *            an object reference
     * @return the non-null reference that was validated
     *             if {@code reference} is null
     */
    public static <T> T checkNotNull(final T reference,final Class<?> cls,Integer code) {
        if (reference == null) {
            String message = String.format("Exceptions%s%s", new Object[]{cls.getSimpleName(), String.valueOf(code)});
            throw new ApiException(code, message,Boolean.TRUE);
        }
        return reference;
    }


    /**
     * 非空验证并抛出异常
     * @param reference
     * @param code
     * @param <T>
     * @return
     */
    public static <T> T checkNotNull(final T reference,Integer code) {
        if (reference == null) {
            String message = String.format("Exceptions%s%s", new Object[]{"ApiCode", String.valueOf(code)});
            throw new ApiException(code, message,Boolean.TRUE);
        }
        return reference;
    }

    /**
     *
     * @param reference
     * @param code
     * @param reason
     * @param translateRequired
     * @param <T>
     * @return
     */
    public static <T> T checkNotNull(final T reference,Integer code,String reason,Boolean translateRequired) {
        if (reference == null) {
            throw new ApiException(code, reason,translateRequired);
        }
        return reference;
    }

    public static <T> T checkNull(final T reference,Integer code,String reason) {
        if (reference != null) {
            throw new ApiException(code, reason,Boolean.FALSE);
        }
        return reference;
    }



    public static void not(final Boolean expression, final Class<?> cls, Integer code) {
        if (!expression) {
            String message=String.format("Exceptions%s%s",cls.getSimpleName(),String.valueOf(code));
            throw new ApiException(code,message);
        }
    }

    public static void not(final Boolean expression, Integer code,String reason) {
        if (!expression) {
            throw new ApiException(code,reason,Boolean.FALSE);
        }
    }

    public static void not(final Boolean expression, Integer code) {
        if (!expression) {
            String message = String.format("Exceptions%s%s", new Object[]{"ApiCode", String.valueOf(code)});
            throw new ApiException(code,message,Boolean.TRUE);
        }
    }

    public static void is(final Boolean expression, final Class<?> cls, Integer code) {
        if (expression) {
            String message=String.format("Exceptions%s%s",cls.getSimpleName(),String.valueOf(code));
            throw new ApiException(code,message);
        }
    }

    public static void is(final Boolean expression, Integer code,String reason) {
        if (expression) {
            throw new ApiException(code,reason,Boolean.FALSE);
        }
    }

    public static void is(final Boolean expression, Integer code) {
        if (expression) {
            String message = String.format("Exceptions%s%s", new Object[]{"ApiCode", String.valueOf(code)});
            throw new ApiException(code,message,Boolean.TRUE);
        }
    }

}
