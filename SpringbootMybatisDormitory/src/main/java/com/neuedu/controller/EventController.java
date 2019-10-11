package com.neuedu.controller;


import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.neuedu.config.PassToken;
import com.neuedu.pojo.Event;
import com.neuedu.pojo.Result;
import com.neuedu.service.impl.EventServiceImpl;
import lombok.Data;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ligeng
 * @since 2019-08-16
 */
@RestController
@RequestMapping("/event")
public class EventController {
    @Resource
    EventServiceImpl eventService;
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat sdf_yyyyMMdd_hhmmss = new SimpleDateFormat("yyyy-MM-dd");
        sdf_yyyyMMdd_hhmmss.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf_yyyyMMdd_hhmmss, true));
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class,true));
        binder.registerCustomEditor(String.class,new StringTrimmerEditor(false));
        binder.registerCustomEditor(Long.class,new CustomNumberEditor(Long.class,true));
    }

        @GetMapping("/list")
    @PassToken
    public Result list(Event event) {
        /*DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(dateFormat.format(event.getAddTime()));*/
        event.setWithPage(1);
        System.out.print("进入了eventlist的Controller");
        return eventService.list(event);
    }

    @GetMapping("/queryOne")
    @PassToken
    public Result queryOne(Integer id){
        return new Result(1,eventService.getById(id),"查询成功");}

    @PostMapping("/save")
    @PassToken
    public Result save (Event event){
        return new Result(1,eventService.saveOrUpdate(event),"修改成功");}

    @GetMapping("/del")
    @PassToken
    public Result del(Event event){
        event.setIsDel(0);
        return  new Result(1,eventService.updateById(event),"删除成功");
    }

    @GetMapping("/resume")
    @PassToken
    public Result resume(Event event){
        event.setIsDel(1);
        return  new Result(1,eventService.updateById(event),"恢复成功");
    }

    @GetMapping("/queryOneDay")
    @PassToken
    public Result queryOneDay(Event event){
        event.setWithPage(0);
        return eventService.list(event);
    }

    @PostMapping("/content")
    @PassToken
    public Result content (@RequestBody  String content){
        System.out.println(content);
        return new Result(1,"","修改成功");
    }

    @Resource
    protected FastFileStorageClient storageClient;
    @PostMapping("/saveImg")
    @PassToken
    public Result saveImg (MultipartFile imgfile)throws IOException
    {
        StorePath storePath=storageClient.uploadFile(imgfile.getInputStream(),imgfile.getSize(), FilenameUtils.getExtension(imgfile.getOriginalFilename()),null);
        System.out.println(storePath.getFullPath());
        return new Result(1,storePath.getFullPath(),"保存成功");
    }
}
