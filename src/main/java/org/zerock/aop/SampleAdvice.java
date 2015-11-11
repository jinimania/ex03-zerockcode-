package org.zerock.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author LeeSoohoon
 */
@Component
@Aspect
public class SampleAdvice {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before(value = "execution(* org.zerock.service.MessageService*.*(..))")
    public void startLog(JoinPoint jp) {
        logger.info("-----------------------");
        logger.info("-----------------------");
        logger.info(Arrays.toString(jp.getArgs()));
    }

    @Around(value = "execution(* org.zerock.service.MessageService*.*(..))")
    public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
        final long startTime = System.currentTimeMillis();
        logger.info(Arrays.toString(pjp.getArgs()));

        final Object result = pjp.proceed();

        final long endTime = System.currentTimeMillis();
        logger.info(pjp.getSignature().getName() + " : " + (endTime - startTime));
        logger.info("========================");

        return result;
    }
}
