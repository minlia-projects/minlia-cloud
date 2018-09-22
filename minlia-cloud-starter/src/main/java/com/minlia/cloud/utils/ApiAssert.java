package com.minlia.cloud.utils;

import com.minlia.cloud.code.Code;
import com.minlia.cloud.exception.ApiException;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.Map;


public class ApiAssert extends Assert {

    public static void state(boolean expression, Code code, Object... args) {
        if(!expression) {
            throw new ApiException(code, args);
        }
    }

    public static void state(boolean expression, int code, String message) {
        if(!expression) {
            throw new ApiException(code, message);
        }
    }

    public static void isNull(Object object, Code code, Object... args) {
        if(object != null) {
            throw new ApiException(code, args);
        }
    }

    public static void isNull(Object object, int code, String message) {
        if(object != null) {
            throw new ApiException(code, message);
        }
    }

    public static void notNull(Object object, Code code, Object... args) {
        if(object == null) {
            throw new ApiException(code, args);
        }
    }

    public static void notNull(Object object, int code, String message) {
        if(object == null) {
            throw new ApiException(code, message);
        }
    }

    public static void hasLength(String text, Code code, Object... args) {
        if(!org.springframework.util.StringUtils.hasLength(text)) {
            throw new ApiException(code, args);
        }
    }

    public static void hasText(String text, Code code, Object... args) {
        if(!org.springframework.util.StringUtils.hasText(text)) {
            throw new ApiException(code, args);
        }
    }

    public static void doesNotContain(String textToSearch, String substring, Code code) {
        if(org.springframework.util.StringUtils.hasLength(textToSearch) && org.springframework.util.StringUtils.hasLength(substring) && textToSearch.contains(substring)) {
            throw new ApiException(code);
        }
    }


    public static void notEmpty(Object[] array, Code code) {
        if(ObjectUtils.isEmpty(array)) {
            throw new ApiException(code);
        }
    }

    public static void noNullElements(Object[] array, Code code, Object... args) {
        if(array != null) {
            Object[] var2 = array;
            int var3 = array.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Object element = var2[var4];
                if(element == null) {
                    throw new ApiException(code, args);
                }
            }
        }

    }

    public static void notEmpty(Collection<?> collection, Code code, Object... args) {
        if(CollectionUtils.isEmpty(collection)) {
            throw new ApiException(code, args);
        }
    }

    public static void notEmpty(Map<?, ?> map, Code code, Object... args) {
        if(CollectionUtils.isEmpty(map)) {
            throw new ApiException(code, args);
        }
    }

    public static void isInstanceOf(Class<?> type, Object obj, Code code) {
        notNull(type, "Type to check against must not be null");
        if(!type.isInstance(obj)) {
            throw new ApiException(code);
        }

    }

    public static void isAssignable(Class<?> superType, Class<?> subType, Code code) {
        notNull(superType, "Super type to check against must not be null");
        if(subType == null || !superType.isAssignableFrom(subType)) {
            throw new ApiException(code);
        }
    }

}
