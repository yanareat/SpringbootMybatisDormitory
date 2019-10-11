package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neuedu.pojo.Event;
import com.neuedu.mapper.EventMapper;
import com.neuedu.pojo.Result;
import com.neuedu.service.IEventService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>-
 *
 * @author ligeng
 * @since 2019-08-16
 */
@Service
public class EventServiceImpl extends ServiceImpl<EventMapper, Event> implements IEventService {
    @Resource
    EventServiceImpl eventService;
    @Override
    public Result list(Event event) {
        System.out.print("进入了eventlist的Service");
        QueryWrapper<Event> queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNotBlank(event.getTitle()))
        {
            System.out.print("进入了if1");
            queryWrapper.like("Title","%"+event.getTitle()+"%");}
        if(event.getIsDel()!=null)
        {
            System.out.print("进入了if2");
            queryWrapper.eq("isDel",event.getIsDel());}
        if(event.getTime()!=null){
            System.out.print("进入了if3");
            Calendar ca = Calendar.getInstance();
            ca.setTime(event.getTime());
            queryWrapper.eq("month(Time)",ca.get(Calendar.MONTH)+1);
            queryWrapper.eq("day(Time)",ca.get(Calendar.DAY_OF_MONTH));}
        if(event.getBeginTime()!=null&&event.getEndTime()!=null)
        {   System.out.print("进入了if4");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            queryWrapper.between("Time",dateFormat.format(event.getBeginTime()),dateFormat.format(event.getEndTime()));}
        if(event.getBegin()!=null&&event.getEnd()!=null)
        {
            System.out.print("进入了if5");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            queryWrapper.between("AddTime",dateFormat.format(event.getBegin()),dateFormat.format(event.getEnd()));}
        if(StringUtils.isNotBlank(event.getKeyword()))
        {
            System.out.print("进入了if6");
            queryWrapper.like("Keyword","%"+event.getKeyword()+"%");}
        if(StringUtils.isNotBlank(event.getDataSource()))
        {
            System.out.print("进入了if7");
            queryWrapper.like("dataSource","%"+event.getDataSource()+"%");}
        if(event.getCategory()!=null){
            System.out.print("进入了if8");
            System.out.println(event.getCategory());
            queryWrapper.eq("Category",event.getCategory());}
        return new Result(1,
                event.getWithPage()==1?
                        this.page(new Page<Event>(event.getPageNo(),event.getPageSize()),queryWrapper):
                        this.list(queryWrapper),"查询成功");
    }

}
