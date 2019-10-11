package com.neuedu.controller;

import com.neuedu.config.PassToken;
import com.neuedu.pojo.Collect;
import com.neuedu.pojo.Result;
import com.neuedu.service.impl.CollectServiceImpl;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
 * @since 2019-08-29
 */
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Resource
    CollectServiceImpl collectService;
    @InitBinder
    public void init(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
    @GetMapping("/list")
    @PassToken
    public Result list(Collect collect)
    {collect.setWithPage(0);
        return collectService.list(collect);
    }

    @GetMapping("/queryOne")
    @PassToken
    public  Result queryOne(Integer id)
    {return  new Result(1,collectService.getById(id),"查询成功");}

    @GetMapping("/del")
    @PassToken
    public  Result del(Integer id)
    {return new Result(1,collectService.removeById(id),"删除成功");}

    @GetMapping("/delSome")
    @PassToken
    public  Result delSome(String ids)
    { String temp[]=ids.split(",");
        ArrayList<Integer> id =new ArrayList<>();
        for(int i=0;i<temp.length;i++)
            id.add(Integer.parseInt(temp[i]));
        return new Result(1,collectService.removeByIds(id),"批量删除成功");}
}