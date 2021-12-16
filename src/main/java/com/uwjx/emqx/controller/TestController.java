package com.uwjx.emqx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanghuan
 * @link https://huan.uwjx.com
 * @date 2021/11/2 15:01
 */
@RestController
public class TestController {

    @GetMapping(value = "t1")
    public String t1(){
        return "11111";
    }
}
