package com.uwjx.emqx.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

/**
 * @author wanghuan
 * @link https://huan.uwjx.com
 * @date 2021/10/25 19:14
 */
@Slf4j
@Service
public class MqttMsgCallback implements MqttCallback {

    @Override
    public void connectionLost(Throwable throwable) {
        log.warn("MqttMsgCallback -> connectionLost()");
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        log.warn("MqttMsgCallback -> messageArrived()");
        log.warn("收到消息 topic : {}  content : {}" , s , mqttMessage.toString());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        log.warn("MqttMsgCallback -> deliveryComplete() : {}" , JSON.toJSONString(iMqttDeliveryToken));
    }
}
