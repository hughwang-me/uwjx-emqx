package com.uwjx.emqx.controller;

import com.alibaba.fastjson.JSON;
import com.uwjx.emqx.bean.EmqxMsgBody;
import com.uwjx.emqx.service.EmqxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    EmqxService emqxService;

    @PostMapping(value = "publish")
    public String publish(@RequestBody EmqxMsgBody msgBody){
        log.warn("publish : {}" , JSON.toJSONString(msgBody));
        emqxService.publish(msgBody);
        return "ok";
    }

    @PostMapping(value = "addSubscribe")
    public String addSubscribe(@RequestBody EmqxMsgBody msgBody){
        log.warn("addSubscribe : {}" , JSON.toJSONString(msgBody));
        emqxService.addSubscribe(msgBody.getTopic());
        return "ok";
    }

    @PostMapping(value = "listSubscribe")
    public String listSubscribe(@RequestBody EmqxMsgBody msgBody){
        log.warn("listSubscribe : {}" , JSON.toJSONString(msgBody));
        emqxService.listSubscribe();
        return "ok";
    }
}
