package com.uwjx.emqx.controller;

import com.alibaba.fastjson.JSON;
import com.uwjx.emqx.bean.EmqxMsgBody;
import com.uwjx.emqx.service.EmqxPublishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanghuan
 * @link https://huan.uwjx.com
 * @date 2021/11/2 15:01
 */
@RestController
@RequestMapping(value = "test")
@Slf4j
public class TestController {

    @Autowired
    EmqxPublishService emqxPublishService;

    @GetMapping(value = "publish")
    public String publish(@RequestBody EmqxMsgBody msgBody){
        log.warn("msgBody : {}" , JSON.toJSONString(msgBody));
        emqxPublishService.publish(msgBody);
        return "ok";
    }
}
