package com.neuedu.controller;


import com.neuedu.config.PassToken;
import com.neuedu.pojo.Error;
import com.neuedu.pojo.Result;
import com.neuedu.service.impl.ErrorServiceImpl;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
 * @since 2019-08-30
 */
@RestController
@RequestMapping("/error")
public class ErrorController {

    @Resource
    ErrorServiceImpl errorService;
    @InitBinder
    public void init(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
    @GetMapping("/list")
    @PassToken
    public Result list(Error error){
        error.setWithPage(0);
        return errorService.list(error);}

    @GetMapping("/del")
    @PassToken
    public Result del(Integer id){return new Result(1,errorService.removeById(id),"删除成功");}

    @GetMapping("/delSome")
    @PassToken
    public Result delSome(String ids){
        String temp[]=ids.split(",");
        ArrayList<Integer> id =new ArrayList<>();
        for(int i=0;i<temp.length;i++)
            id.add(Integer.parseInt(temp[i]));
        return new Result(1,errorService.removeByIds(id),"批量删除成功");}
}
