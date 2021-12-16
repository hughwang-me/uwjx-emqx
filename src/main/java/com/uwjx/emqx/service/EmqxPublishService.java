package com.uwjx.emqx.service;

import com.uwjx.emqx.bean.EmqxMsgBody;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * @author wanghuan
 * @link https://huan.uwjx.com
 * @date 2021/12/16 14:36
 */
@Service
@Slf4j
public class EmqxPublishService {

    @Value("${emqx.qos}")
    int qos;

    @Autowired
    MqttClient mqttClient;

    public void publish(EmqxMsgBody msgBody){
        //发布消息
        MqttMessage message = new MqttMessage(msgBody.getContent().getBytes(StandardCharsets.UTF_8));
        message.setQos(qos);
        try {
            mqttClient.publish(msgBody.getTopic() , message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
