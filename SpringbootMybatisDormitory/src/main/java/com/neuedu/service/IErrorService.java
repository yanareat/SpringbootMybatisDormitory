package com.neuedu.service;

import com.neuedu.pojo.Error;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.pojo.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangyujie
 * @since 2019-09-05
 */
public interface IErrorService extends IService<Error> {
    Result list(Error error);
}
