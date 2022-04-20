package com.dg3.forum.forum.customannotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<Phone, String> {

	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (value == null) {
			return false;
		}

		
		try{
			return value.matches("0[0-9]{9}");
        }
        catch (Exception e){
            return false;
           
        }
	

	}
}
