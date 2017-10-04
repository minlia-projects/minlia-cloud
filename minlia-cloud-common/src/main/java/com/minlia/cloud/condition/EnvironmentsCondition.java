package com.minlia.cloud.condition;

import com.minlia.cloud.annotation.Environments;
import java.util.regex.Pattern;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

public class EnvironmentsCondition implements Condition {

 @Override
 public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
       Environment environment = context.getEnvironment();
       if (environment != null) {
            String[] profiles = environment.getActiveProfiles();
            MultiValueMap<String, Object> attrs = metadata.getAllAnnotationAttributes(Environments.class.getName());
           if (attrs != null) {
                for (Object value : attrs.get("value")){
                     for (String profile : profiles) {
                          boolean matches = Pattern.matches((String) value, profile);
                          if (matches) {
                             return true;
                          }
                     }
                }
                return false;
          }
      }
      return true;
 }
}