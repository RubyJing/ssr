package com.ssm.aspect;

import com.ssm.entity.User;
import com.ssm.entity.UserInfo;
import com.ssm.service.UserInfoService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * AOP
 */

@Aspect
@Component
public class UserAdvice {

    @Autowired
    UserInfoService userInfoService;

    @AfterReturning(value = "execution(* com.ssm.service.UserService.insertOrUpdateUser(..))",returning = "xxx")
    public void afterAddUser(JoinPoint joinPoint , int xxx){
        Object [] args = joinPoint.getArgs();
        if (args.length == 2){
            Object o = args[1];
            if (o instanceof UserInfo){
                UserInfo userInfo = (UserInfo) o;
                if (xxx>0){
                    userInfoService.insertOrUpdateUserInfo(userInfo);
                }
            }
        }
    }

    @Around(value = "execution(* com.ssm.service.UserService.insert*(..))")
    public Object aroundAddUser(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object [] args = proceedingJoinPoint.getArgs();
        if (args.length == 2){
            Object o = args[0];
            if (o instanceof User){
                User user = (User) o;
                user.setPassword("123456");
            }
        }
        Object o = proceedingJoinPoint.proceed();

        return o;
    }
}
