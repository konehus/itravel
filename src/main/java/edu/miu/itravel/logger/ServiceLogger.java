package edu.miu.itravel.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLogger {

    @Before(value = "execution(* edu.miu.itravel.service.*.*(..) ) ")
    public void beforeServiceLog(JoinPoint joinPoint) throws Throwable{
        Object[] objects = joinPoint.getArgs();
        System.out.print( joinPoint.getSignature().getName() + "( ");
        for(Object object:objects)
            System.out.print(object.getClass().getSimpleName() + " " + object);
        System.out.println(" )");


    }

}
