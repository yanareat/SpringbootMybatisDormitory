package com.neuedu.config;

/**
 * Created by 12483 on 2019/8/28.
 */
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuedu.pojo.Result;
import com.neuedu.pojo.Userinfor;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
//import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyu on 2019/8/8.
 */
@Aspect
@Component
public class MyAop {
//    @Resource
//    StringRedisTemplate stringRedisTemplate;
    @Resource
    ObjectMapper objectMapper;
 //   @Around("execution(* com.neuedu.service.impl.BuildingServiceImpl.getAll(..))")
//    public List<Building> listaop(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//
//        List<Building> list = null;
//        if(stringRedisTemplate.hasKey("buildings")){
//            System.out.println("缓存中存在");
//            String str = stringRedisTemplate.opsForValue().get("buildings");
//            list = objectMapper.readValue(str,List.class);
//        }else{
//            System.out.println("key不存在,调用原先的方法");
//            list = (List<Building>) proceedingJoinPoint.proceed();
//            stringRedisTemplate.opsForValue().set("buildings",objectMapper.writeValueAsString(list),90, TimeUnit.DAYS);
//        }
//        return list;
//    }



    @Around("execution(* com.neuedu.controller.UserinforController.login(..))")
    public Result loginaop(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Result result = (Result) proceedingJoinPoint.proceed();
        System.out.println("进入aop11111111111111111111111111111111111111111111");
        Map<String,Object> map = new HashMap<>();
        if( result.getObj() != null){
            Userinfor user =(Userinfor)result.getObj();
            String token = JWT.create().withAudience(user.getUserName()).sign(Algorithm.HMAC384(user.getPassword()));
            map.put("user",user);
            map.put("token",token);
            // 以loginId为key user对象转成json为value存入redis
            return new Result(1,map,"登录成功");
        }
        return result;
    }

    /*    @Around("execution(* com.neuedu.controller.BuildingController.*(..))")
    public Object updateRedis(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
       Object result = proceedingJoinPoint.proceed();
        String methodName=proceedingJoinPoint.getSignature().getName();
        if(methodName.equals("resume")||methodName.equals("del")||methodName.equals("sava")){
            if(stringRedisTemplate.hasKey("buildings"))
                stringRedisTemplate.delete("buildings");
        }
       return result;
    }*/
//    @AfterReturning("execution(* com.neuedu.controller.BuildingController.*(..))")
//    public void after(JoinPoint joinPoint){
//        String methodName=joinPoint.getSignature().getName();
//        if(methodName.equals("resume")||methodName.equals("del")||methodName.equals("sava")){
//            System.out.println("更新缓存");
//            if(stringRedisTemplate.hasKey("buildings"))
//                stringRedisTemplate.delete("buildings");
//        }
//    }
}