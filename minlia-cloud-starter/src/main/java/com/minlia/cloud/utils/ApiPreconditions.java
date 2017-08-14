package com.minlia.cloud.utils;

import com.minlia.cloud.exception.ApiException;

/**
 * Inspired by Preconditions from google guava
 *
 * @updated updated at 2017-07-15 02:34:00
 * @author will@minlia.com
 *
 */
public final class ApiPreconditions {

    public static final String EXCEPTION_PATTERN = "Exceptions%s%s";
    public static final String API_CODE = "ApiCode";

    private ApiPreconditions() {
        throw new AssertionError();
    }

    public static <T> T checkNotNull(final T reference, Integer code) {
        if (reference == null) {
            String message = String.format(EXCEPTION_PATTERN, new Object[]{API_CODE, String.valueOf(code)});
            throw new ApiException(code, message, Boolean.TRUE);
        }
        return reference;
    }

    public static <T> T checkNotNull(final T reference, Integer code, String reason) {
        if (reference == null) {
            throw new ApiException(code, reason, Boolean.FALSE);
        }
        return reference;
    }

    public static <T> T checkNull(final T reference, Integer code) {
        if (reference != null) {
            String message = String.format(EXCEPTION_PATTERN, new Object[]{API_CODE, String.valueOf(code)});
            throw new ApiException(code, message, Boolean.TRUE);
        }
        return reference;
    }

    public static <T> T checkNull(final T reference, Integer code, String reason) {
        if (reference != null) {
            throw new ApiException(code, reason, Boolean.FALSE);
        }
        return reference;
    }

    public static void not(final Boolean expression, Integer code, String reason) {
        if (!expression) {
            throw new ApiException(code, reason, Boolean.FALSE);
        }
    }

    public static void not(final Boolean expression, Integer code) {
        if (!expression) {
            String message = String.format(EXCEPTION_PATTERN, new Object[]{API_CODE, String.valueOf(code)});
            throw new ApiException(code, message, Boolean.TRUE);
        }
    }


    public static void is(final Boolean expression, Integer code, String reason) {
        if (expression) {
            throw new ApiException(code, reason, Boolean.FALSE);
        }
    }

    public static void is(final Boolean expression, Integer code) {
        if (expression) {
            String message = String.format(EXCEPTION_PATTERN, new Object[]{API_CODE, String.valueOf(code)});
            throw new ApiException(code, message, Boolean.TRUE);
        }
    }

}
