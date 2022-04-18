package com.dg3.forum.forum.customannotation;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.dg3.forum.forum.entity.Users;

@Aspect
@Component
public class NumberPhoneAspect {

    @Around("@annotation(NumberPhone)")
    public Object authorize(ProceedingJoinPoint joinPoint) throws Throwable {

    	//BEFORE METHOD EXECUTION
    	Users users = (Users) joinPoint.getArgs()[0];
    
        System.out.println("User phone: " + users.getPhone_number());

        //Only user id 33 is authorize to login, other user are not valid users.
        
    
        if (!users.getPhone_number().matches("0\\d{9}")) {
        	return "";
        }
		//This is where ACTUAL METHOD will get invoke
        Object result = joinPoint.proceed();
        // AFTER METHOD EXECUTION
        System.out.println(result);
        return result;
    }
}
