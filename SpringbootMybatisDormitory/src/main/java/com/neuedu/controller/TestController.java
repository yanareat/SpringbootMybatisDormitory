package com.neuedu.controller;

import com.neuedu.config.PassToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 12483 on 2019/8/9.
 */
@RestController
public class TestController {
    @PassToken
    @GetMapping("/index")
    public String list()
    {
        return "ghjghjgjhb";
    }
}
