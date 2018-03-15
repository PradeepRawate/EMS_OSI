package com.osi.fas.aop;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Before;

public class FASBeforeAspect {

	private static Logger logger = LogManager.getLogger();

	@Before("execution(* com.osi.fas..*.*(..))")
	public void forAllMethodsOfFasRepositoryBeforeAdvise(JoinPoint joinPoint) {

		Signature signature = joinPoint.getSignature();
		// String methodName = signature.getName();
		String stuff = signature.toString();
		String arguments = Arrays.toString(joinPoint.getArgs());

		logger.info("*****************************************************************************************");

		logger.info(" 1. Intercepted at class : " + joinPoint.getSignature());
		logger.info(" 2. At : " + joinPoint.getSignature().getName());
		logger.info(" 3. Method arguments are : " + joinPoint.getSignature().getName() + " : " + arguments);
		logger.info(" 4. In : " + stuff);

		logger.info("****************************************************************************************");
	}

}
