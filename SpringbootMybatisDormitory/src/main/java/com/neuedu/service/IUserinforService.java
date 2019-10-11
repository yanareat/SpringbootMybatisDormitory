package com.neuedu.service;

import com.neuedu.pojo.Result;
import com.neuedu.pojo.Userinfor;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangyujie
 * @since 2019-08-14
 */
public interface IUserinforService extends IService<Userinfor> {
    public Result list(Userinfor role);
    public Result logincheck(Userinfor role);
    public String getpwd(String username);
    public  Result getpwd1(String username);
}
