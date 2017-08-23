package com.minlia.cloud.annotation.i18n;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Created by will on 8/22/17.
 */
@Target({ ElementType.TYPE,ElementType.FIELD})
@Retention(RUNTIME)
@Inherited
public @interface Localized {
    Localize[] values() default {};
}