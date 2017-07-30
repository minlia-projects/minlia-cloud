package com.minlia.cloud.security.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SecuredByAuthority {

    /**
     * Authority names.
     *
     * @return String[] Array of authority names.
     */
    String[] value() default {};

}