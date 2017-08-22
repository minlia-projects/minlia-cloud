package com.minlia.cloud.annotation.i18n;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by will on 8/22/17.
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface Localized {
    Localize[] values() default {};
}