package test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * Created by 12483 on 2019/8/27.
 */
public class test3 {



    public static void main(String[] args)
    {
        /*  1. 第一次登录时根据输入时输入的账号密码生成token,传给前端vuex保存下来,以后每次向后端发请求都会带着token
        String token=JWT.create().withAudience("001").sign(Algorithm.HMAC384("123456"));
        System.out.print(token);
        */

        /*  2. 当前端向后端发请求时，会先解析请求中的token,根据token解析出用户账号*/
        DecodedJWT decodedJWT= JWT.decode("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJhdWQiOiIwMDEifQ.arhHS4jdbGqQM7CmD5ooQ3vBGpGd-7wR7e13jen-L5KjwwgPhBSxOXXO49Hwy9lq");
        String username=decodedJWT.getAudience().get(0);
        System.out.println(username);
        /*  3. String password=???  此处为根据解析出的用户账号在数据库中查询出密码，假设为123456*/

       /*   4. 根据查出的密码再次生成一个签名用来检验用户请求中发来的token对不对，如果不报错，则通过*/
       JWTVerifier require=JWT.require(Algorithm.HMAC384("123456")).build();
       DecodedJWT decodedJWT1=require.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJhdWQiOiIwMDEifQ.arhHS4jdbGqQM7CmD5ooQ3vBGpGd-7wR7e13jen-L5KjwwgPhBSxOXXO49Hwy9lq");
       System.out.println(decodedJWT1.getAudience().get(0));
        /*   总结，以上4步验证法的好处是： 如果别人盗用了你的账号密码，正在使用系统。当你修改了密码之后，第四步中根据密码生成的新签名会立即改变，这样验证就通不过，前端发送的新请求立刻都不能实现了*/
      }

}
