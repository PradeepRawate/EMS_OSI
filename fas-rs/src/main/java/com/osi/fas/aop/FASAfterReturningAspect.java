package com.osi.fas.aop;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class FASAfterReturningAspect {

	private static Logger logger = LogManager.getLogger();

	@AfterReturning(pointcut = "execution(* com.osi.fas..*.*(..))", returning = "result")
	public void findAllMethodsOfFasRepositoryAfterReturningAdvise(JoinPoint joinPoint, Object result) {

		Signature signature = joinPoint.getSignature();
		// String methodName = signature.getName();
		String stuff = signature.toString();
		String arguments = Arrays.toString(joinPoint.getArgs());

		logger.info("*****************************************************************************************");

		logger.info(" 1. Intercepted at class : " + joinPoint.getSignature());
		logger.info(" 2. At : " + joinPoint.getSignature().getName());
		logger.info(" 3. Method arguments are : " + joinPoint.getSignature().getName() + " : " + arguments);
		logger.info(" 4. In : " + stuff);
		logger.info(" 5. Method returned value is : " + result);

		logger.info("****************************************************************************************");

	}

}
