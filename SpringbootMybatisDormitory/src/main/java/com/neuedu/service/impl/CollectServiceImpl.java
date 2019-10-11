package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neuedu.pojo.Collect;
import com.neuedu.mapper.CollectMapper;
import com.neuedu.pojo.Reptile;
import com.neuedu.pojo.Result;
import com.neuedu.service.ICollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ligeng
 * @since 2019-08-29
 */
@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements ICollectService {

    @Resource
    CollectServiceImpl collectService;

    @Override
    public Result list(Collect collect)
    {
        QueryWrapper<Collect> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByAsc("AddDate");
        if(collect.getBeginTime()!=null&&collect.getEndTime()!=null)
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            queryWrapper.between("OccurDate",dateFormat.format(collect.getBeginTime()),dateFormat.format(collect.getEndTime()));
        }
        if(collect.getBegin()!=null&&collect.getEnd()!=null)
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            queryWrapper.between("AddDate",dateFormat.format(collect.getBegin()),dateFormat.format(collect.getEnd()));
        }
        return new Result(1,this.list(queryWrapper),"查询成功");
    }
}