package com.minlia.cloud.utils;

import static com.minlia.cloud.constant.Constants.EXCEPTION_PREFIX;

import com.minlia.cloud.exception.ApiException;

/**
 * Inspired by Preconditions from google guava
 *
 * @updated updated at 2017-07-15 02:34:00
 * @author will@minlia.com
 *
 */
public final class ApiPreconditions {

    public static final String EXCEPTION_PATTERN = EXCEPTION_PREFIX+"%s%s";
    public static final String API_CODE = "ApiCode";

    private ApiPreconditions() {
        throw new AssertionError();
    }

    public static <T> T checkNotNull(final T reference, int code) {
        if (reference == null) {
            String message = String.format(EXCEPTION_PATTERN, new Object[]{API_CODE, String.valueOf(code)});
            throw new ApiException(code, message, Boolean.TRUE);
        }
        return reference;
    }

    public static <T> T checkNotNull(final T reference, int code, String reason) {
        if (reference == null) {
            throw new ApiException(code, reason, Boolean.FALSE);
        }
        return reference;
    }

    public static <T> T checkNull(final T reference, int code) {
        if (reference != null) {
            String message = String.format(EXCEPTION_PATTERN, new Object[]{API_CODE, String.valueOf(code)});
            throw new ApiException(code, message, Boolean.TRUE);
        }
        return reference;
    }

    public static <T> T checkNull(final T reference, int code, String reason) {
        if (reference != null) {
            throw new ApiException(code, reason, Boolean.FALSE);
        }
        return reference;
    }

    public static void not(final Boolean expression, int code, String reason) {
        if (!expression) {
            throw new ApiException(code, reason, Boolean.FALSE);
        }
    }

    public static void not(final Boolean expression, int code) {
        if (!expression) {
            String message = String.format(EXCEPTION_PATTERN, new Object[]{API_CODE, String.valueOf(code)});
            throw new ApiException(code, message, Boolean.TRUE);
        }
    }


    public static void is(final Boolean expression, int code, String reason) {
        if (expression) {
            throw new ApiException(code, reason, Boolean.FALSE);
        }
    }

    public static void is(final Boolean expression, int code) {
        if (expression) {
            String message = String.format(EXCEPTION_PATTERN, new Object[]{API_CODE, String.valueOf(code)});
            throw new ApiException(code, message, Boolean.TRUE);
        }
    }

}
