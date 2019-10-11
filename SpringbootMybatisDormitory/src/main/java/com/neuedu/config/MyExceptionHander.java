package com.neuedu.config;

import com.neuedu.pojo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 12483 on 2019/8/12.
 */
@ControllerAdvice
@ResponseBody
public class MyExceptionHander {
    @ExceptionHandler
    public Result exceptionHandler(Exception ex)
    {
        return new Result(0,"错了",ex.getMessage());
    }
}
