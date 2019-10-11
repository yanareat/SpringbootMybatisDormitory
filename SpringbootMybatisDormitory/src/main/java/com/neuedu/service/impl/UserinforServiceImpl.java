package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neuedu.pojo.Result;
import com.neuedu.pojo.Userinfor;
import com.neuedu.mapper.UserinforMapper;
import com.neuedu.service.IUserinforService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangyujie
 * @since 2019-08-14
 */
@Service
public class UserinforServiceImpl extends ServiceImpl<UserinforMapper, Userinfor> implements IUserinforService {
    @Resource
    UserinforMapper roleMapper;
    @Override
    public Result list(Userinfor role) {
        QueryWrapper<Userinfor> queryWrapper=new QueryWrapper<Userinfor>();
        if (StringUtils.isNotBlank(role.getUserName()))
        {
            queryWrapper.like("UserName","%"+role.getUserName()+"%");
        }
        if (role.getIsDel()!=null)
        {
            queryWrapper.eq("IsDel",role.getIsDel());
        }

        return new Result(1,role.getWithPage()==1?
                this.page(new Page<Userinfor>(role.getPageNo(),role.getPageSize()),queryWrapper):this.list(queryWrapper),
                "查询成功");

    }
    public Result logincheck(Userinfor role) {
        QueryWrapper<Userinfor> loginyanzheng=new QueryWrapper<Userinfor>();
        if (StringUtils.isNotBlank(role.getUserName()))
        {
            loginyanzheng.eq("UserName",role.getUserName());
        }
        Userinfor queryuser=roleMapper.selectOne(loginyanzheng);
        if (queryuser==null)
        {
            return new Result(0,null,"用户名不存在");
        }
        else{

            if(!queryuser.getPassword().equals(role.getPassword()))
            {
                return new Result(0,null,"密码输入错误");
            }
            else{
                if(role.getJurisdiction()==1&&queryuser.getJurisdiction()==0)
                {
                    return new Result(0,null,"您无权限登录");
                }
                else{ return new Result(1,queryuser,"登录成功");}
            }


        }


    }

    public String getpwd(String username)
    {
        QueryWrapper<Userinfor> loginyanzheng=new QueryWrapper<Userinfor>();
        loginyanzheng.eq("UserName",username);
        Userinfor queryuser=roleMapper.selectOne(loginyanzheng);
        return queryuser.getPassword();
    }
    public Result getpwd1(String username) {
        QueryWrapper<Userinfor> loginyanzheng=new QueryWrapper<Userinfor>();
        if (StringUtils.isNotBlank(username))
        {
            loginyanzheng.eq("UserName",username);
        }
        Userinfor queryuser=roleMapper.selectOne(loginyanzheng);
        if (queryuser==null)
        {
            return new Result(1,null,"用户名合法");
        }
        else{
               return new Result(0,null,"用户名已存在");

        }


    }

    }

