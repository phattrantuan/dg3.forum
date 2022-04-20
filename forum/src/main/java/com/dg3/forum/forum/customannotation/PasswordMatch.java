package com.dg3.forum.forum.customannotation;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy=PasswordValidator.class)
@Documented
public @interface PasswordMatch {
    String message() default "{error.password.mismatch}";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}

