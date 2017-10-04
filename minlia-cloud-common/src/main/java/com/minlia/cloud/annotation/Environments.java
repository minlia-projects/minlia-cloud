package com.minlia.cloud.annotation;

import com.minlia.cloud.condition.EnvironmentsCondition;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Conditional;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Conditional(EnvironmentsCondition.class)
public @interface Environments {
      /**
      * The profile regex for which the annotated component should be registered.
      */
      String value();
}
