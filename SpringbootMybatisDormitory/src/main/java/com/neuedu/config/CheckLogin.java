package com.neuedu.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.neuedu.controller.UserinforController;
import com.neuedu.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by 12483 on 2019/8/27.
 */
public class CheckLogin implements HandlerInterceptor {
    @Resource
    ObjectMapper objectMapper;
    @Autowired
    UserinforController u;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

         String token=  request.getHeader("token");
         if(handler instanceof HandlerMethod){

             HandlerMethod handlerMethod=(HandlerMethod)handler;

             Method method=handlerMethod.getMethod();

             if(!method.isAnnotationPresent(PassToken.class))
             {

                 if(token==null)
                 {
                     response.getWriter().write(objectMapper.writeValueAsString(new Result(-1,null,"misstoken")));
                     return false;
                 }
                 else{

                     try {
                         DecodedJWT decodedJWT = JWT.decode(token);
                         String username = decodedJWT.getAudience().get(0);
                         String pas=u.findpwd(username);
                         JWTVerifier require=JWT.require(Algorithm.HMAC384(pas)).build();
                         DecodedJWT decodedJWT1=require.verify(token);
                         //根据密码生成新token
                         //与传来的token比较
                         return true;
                     }
                     catch (Exception ex){
                         System.out.println("测试位置5");
                         System.out.print("异常");
                         response.getWriter().write(objectMapper.writeValueAsString(new Result(-1,null,"misstoken")));
                         return false;
                     }

                 }

             }
         }

      return true;
    }
}
