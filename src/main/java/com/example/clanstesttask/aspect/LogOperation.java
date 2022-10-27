package com.example.clanstesttask.aspect;

import com.example.clanstesttask.repo.ClanService;
import com.example.clanstesttask.repo.UserService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;

@Slf4j
@Aspect
@Component
public class LogOperation {
    @Autowired
    UserService uService;
    @Autowired
    ClanService csImpl ;

    @Before("execution(* com.example.clanstesttask.service.userservice.*.*(..)) &&" +
            "args(userId, clanId,  gold)")
    public void doAccessCheck(JoinPoint joinPoint, long userId, long clanId, int gold) {
        log.info("User "+uService.getUser(userId).getName()
                        +" called method "+joinPoint.getSignature().getName()
                        +" for clan - "+ csImpl.getClan(clanId).getName()
                        +", gold - "+gold,
                new Date());
    }

    @After("execution(* com.example.clanstesttask.service.userservice.*.*(..)) &&" +
            "args(userId, clanId,  gold)")
    public void doAfter(JoinPoint joinPoint, long userId, long clanId, int gold) {
        log.info("User "+uService.getUser(userId).getName()
                        +" did method "+joinPoint.getSignature().getName()
                        +" for clan - "+ csImpl.getClan(clanId).getName()
                        +", gold - "+gold,
                new Date());
    }

    @AfterThrowing(value = "execution(* com.example.clanstesttask.service.userservice.*.*(..)) &&" +
            "args(userId, clanId,  gold)",
            throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex, long userId, long clanId, int gold) {
        log.error("User "+uService.getUser(userId).getName()
                        +" tried method "+joinPoint.getSignature().getName()
                        +" but was sent exception "+ex.getMessage()
                        +" for clan - "+ csImpl.getClan(clanId).getName()
                        +", gold - "+gold,
                new Date());
    }
}
