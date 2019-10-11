package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neuedu.pojo.Reptile;
import com.neuedu.mapper.ReptileMapper;
import com.neuedu.pojo.Result;
import com.neuedu.service.IReptileService;
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
 * @since 2019-08-27
 */
@Service
public class ReptileServiceImpl extends ServiceImpl<ReptileMapper, Reptile> implements IReptileService {

    @Resource
    ReptileServiceImpl reptileService;

    @Override
    public Result list(Reptile reptile)
    {
        QueryWrapper<Reptile> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByAsc("AddTime");
        if(reptile.getBeginTime()!=null&&reptile.getEndTime()!=null)
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            queryWrapper.between("Time",dateFormat.format(reptile.getBeginTime()),dateFormat.format(reptile.getEndTime()));
        }
        if(reptile.getBegin()!=null&&reptile.getEnd()!=null)
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            queryWrapper.between("AddTime",dateFormat.format(reptile.getBegin()),dateFormat.format(reptile.getEnd()));
        }
        return new Result(1,this.list(queryWrapper),"查询成功");
    }
}
