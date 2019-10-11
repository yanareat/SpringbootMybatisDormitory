package com.neuedu.controller;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neuedu.config.PassToken;
import com.neuedu.pojo.Result;
import com.neuedu.pojo.Userinfor;
import com.neuedu.service.IUserinforService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangyujie
 * @since 2019-08-14
 */
@RestController
@RequestMapping("/userinfor")
public class UserinforController {
    // 产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    // 产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAI4FsLrchZNDFMxtNGzc4X";   //改这里
    static final String accessKeySecret = "I7c5qwJpDrxuwEgviXC0rCcK4C5zB6"; //改这里
    @Resource
    IUserinforService roleService;


    @GetMapping("/list")
    public Result list(Userinfor role) {
        QueryWrapper<Userinfor> queryWrapper=new QueryWrapper<Userinfor>();
        return roleService.list(role);
    }
    @GetMapping("/queryone")
    public Result getUserById(Integer id)
    {
        return new Result(1,roleService.getById(id),"成功");
    }
    @PostMapping("/save")
    public Result save(Userinfor role){
        return new Result(1,roleService.saveOrUpdate(role),"成功");
    }
    @GetMapping("/del")
    public Result del(Userinfor role){
        role.setIsDel(0);
        return new Result(1,roleService.updateById(role),"删除成功");
    }
    @GetMapping("/resume")
    public Result resume(Userinfor role) {
        role.setIsDel(1);
        return new Result(1,roleService.updateById(role),"恢复成功");
    }
    @PostMapping("/login")
    @PassToken
    public Result login(Userinfor role)
    {
        QueryWrapper<Userinfor> loginyanzheng=new QueryWrapper<Userinfor>();
        return roleService.logincheck(role);
    }
    @GetMapping("/sendto")
    public String send(){
        try {
            Socket socket = new Socket("10.25.132.37",8001);

            //获取输出流，向服务器端发送信息
            OutputStream os=socket.getOutputStream();//字节输出流
            PrintWriter pw=new PrintWriter(os);//将输出流包装为打印流
            pw.write("我是Java服务器");
            pw.flush();
            socket.shutdownOutput();//关闭输出流

            InputStream is=socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String info=null;
            while((info=in.readLine())!=null){
                System.out.println("我是客户端，Python服务器说："+info);
            }
            is.close();
            in.close();
            socket.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "0000000";
    }

    public  String findpwd(String username)
    {
        QueryWrapper<Userinfor> loginyanzheng=new QueryWrapper<Userinfor>();
        return roleService.getpwd(username);
    }
    @GetMapping("/duanxin")
    @PassToken
    public String sendSms(String telephone) throws ClientException {
            String code=null;
            int newNum = (int)((Math.random()*9+1)*100000);
            code=String.valueOf(newNum);
        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(telephone);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName("校史上的今天");  //改这里
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_173349541");  //改这里
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的用户,您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\"" + code + "\"}");

        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");

        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        // hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return code;
    }
    @PassToken
    @GetMapping("/update")
    public Result update(Userinfor userinfor)
    {
        return new Result(1,roleService.saveOrUpdate(userinfor),"修改成功");
    }
    @GetMapping("/findpwd")
    public  Result findpwd1(String userName)
    {
        System.out.println("进入findpwd1");
        System.out.println(userName);
        QueryWrapper<Userinfor> loginyanzheng=new QueryWrapper<Userinfor>();
        return roleService.getpwd1(userName);
    }

}
