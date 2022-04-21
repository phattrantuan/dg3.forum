package com.dg3.forum.forum.customannotation;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;



@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
    String locale() default "";

    String message() default "Invalid phone number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

    

   // @Override
   // public void initialize(Phone constraintAnnotation) {

    //}


