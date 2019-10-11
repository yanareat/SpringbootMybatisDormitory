package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neuedu.pojo.Collect;
import com.neuedu.pojo.Error;
import com.neuedu.mapper.ErrorMapper;
import com.neuedu.pojo.Result;
import com.neuedu.service.IErrorService;
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
 * @since 2019-08-30
 */
@Service
public class ErrorServiceImpl extends ServiceImpl<ErrorMapper, Error> implements IErrorService {

    @Resource
    ErrorServiceImpl errorService;

    @Override
    public Result list(Error error)
    {
        QueryWrapper<Error> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByAsc("AddDate");
        return new Result(1,this.list(queryWrapper),"查询成功");
    }
}
