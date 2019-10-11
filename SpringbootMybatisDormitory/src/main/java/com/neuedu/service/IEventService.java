package com.neuedu.service;

import com.neuedu.pojo.Event;
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
public interface IEventService extends IService<Event> {
    Result list(Event event);
}
