package com.osi.fas.aop;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class FASAfterThrowingAspect {
	
	private static Logger logger = LogManager.getLogger();
	
	@AfterThrowing(pointcut = "execution(* com.osi.fas..*.*(..))", throwing = "error")
	public void findAllMethodsOfFasRepositoryAfterThrowingAdvise(JoinPoint joinPoint, Throwable error) throws Throwable{
		
		Signature signature = joinPoint.getSignature();
	    //String methodName = signature.getName();
	    String stuff = signature.toString();
	    String arguments = Arrays.toString(joinPoint.getArgs());
	    
		logger.info("*****************************************************************************************");

		logger.error(" 1. Intercepted at class : " + joinPoint.getSignature());
		logger.error(" 2. Exception occured at : " + joinPoint.getSignature().getName());
		logger.error(" 3. Method arguments are : " + joinPoint.getSignature().getName() + " : " + arguments);
		logger.error(" 4. In : " + stuff);
		logger.error(" 5. Execption is : " + error.getMessage(), error);

		logger.info("****************************************************************************************");

	}

	
	 // Method afterThrowing() is a After Throwing advice
	 // implemented by providing @AfterThrowing annotation.
	 // This annotation takes in Pointcut expression, which 
	 // indicates when this advice executes.
	 // This Pointcut expression tells that afterThrowing() advice
	 // will execute after any exception is thrown from the method 
	 // divide of Divide interface.
	 // The after advice takes in JoinPoint and an Object. JoinPoint 
	 // which here represents method execution and pointcut expression
	 // takes in throwing property and makes it able to store, available 
	 // to the afterThrowing method.
}
