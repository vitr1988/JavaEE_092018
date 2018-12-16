package ru.otus.ejb.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;

@Aspect
public class CalculatorAspect {

    @Before("execution(* ru.otus.ejb.aop.aspect.AOPCalculator.sqrt(..))")
    public void methodInvocation(JoinPoint pjp) {
        System.out.println("AOP is working on " + pjp.getSignature().getName());
        System.out.println(" with args " + Arrays.toString(pjp.getArgs()));
    }
}
