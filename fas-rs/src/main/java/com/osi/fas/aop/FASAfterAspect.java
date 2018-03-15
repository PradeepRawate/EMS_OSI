package com.osi.fas.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class FASAfterAspect {
	
	private static Logger logger = LogManager.getLogger();

	@After("execution(* com.osi.fas..*.*(..))")
	public void forAllMethodsOfFasRepositoryAfterAdvise(JoinPoint joinPoint) {

		logger.info(
				"*******************************************************************************************************");
		logger.debug("***AspectJ*** After is running for the : " + joinPoint.getSignature());
		logger.info("***AspectJ***  After is running for the : " + joinPoint.getSignature().getName());
		logger.info(
				"*******************************************************************************************************");
	}

}
