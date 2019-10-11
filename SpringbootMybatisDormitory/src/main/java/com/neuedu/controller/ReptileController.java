package com.neuedu.controller;


import com.alibaba.fastjson.JSONObject;
import com.neuedu.config.PassToken;
import com.neuedu.pojo.Reptile;
import com.neuedu.pojo.Result;
import com.neuedu.service.impl.ReptileServiceImpl;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ligeng
 * @since 2019-08-27
 */
@RestController
@RequestMapping("/reptile")
public class ReptileController {

    @Resource
    ReptileServiceImpl reptileService;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @GetMapping("/list")
    @PassToken
    public Result list(Reptile reptile) {
        reptile.setWithPage(0);
        return reptileService.list(reptile);
    }

    @GetMapping("/queryOne")
    @PassToken
    public Result queryOne(Integer id) {
        return new Result(1, reptileService.getById(id), "查询成功");
    }

    @GetMapping("/del")
    @PassToken
    public Result del(Integer id) {
        return new Result(1, reptileService.removeById(id), "删除成功");
    }

    @GetMapping("/delSome")
    @PassToken
    public Result delSome(String ids) {
        String temp[] = ids.split(",");
        ArrayList<Integer> id = new ArrayList<>();
        for (int i = 0; i < temp.length; i++)
            id.add(Integer.parseInt(temp[i]));
        return new Result(1, reptileService.removeByIds(id), "批量删除成功");
    }

    @GetMapping("/startReptile")
    @PassToken
    public Result startReptile(Reptile reptile) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String begin = dateFormat.format(reptile.getBeginTime());
        String end = dateFormat.format(reptile.getEndTime());
        OutputStream os = null;
        PrintStream ps = null;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        Socket socket = null;
        try {
            socket = new Socket("39.106.1.127", 8001);
            //获取输出流，向服务器端发送信息
            os = socket.getOutputStream();//字节输出流
            ps = new PrintStream(os);//将输出流包装为打印流
            JSONObject json = new JSONObject();
            json.put("num", 5);
            json.put("flag", 0);
            String b2e[] = {"2019-09-22", "2019-01-01"};
            json.put("b2e", b2e);
            String urls[] = {"http://ysu.edu.cn/index/xwtx.htm"};
            json.put("urls", urls);
            String str = json.toJSONString();
            System.out.println(str);
            ps.println(str);
            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            while ((str = br.readLine()) != null) {
                System.out.println(str);
                if (str.equals("end")) {
                    break;
                }
            }
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (os != null)
                    os.close();
                if (br != null)
                    br.close();
                if (isr != null)
                    isr.close();
                if (is != null)
                    is.close();
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return new Result(0, null, "爬虫失败");
            }
            return new Result(1, null, "爬虫成功");
        }
    }
}
