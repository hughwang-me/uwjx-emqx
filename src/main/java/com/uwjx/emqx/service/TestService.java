package com.uwjx.emqx.service;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * @author wanghuan
 * @link https://huan.uwjx.com
 * @date 2021/10/25 19:07
 */
//@Service
@Slf4j
public class TestService implements InitializingBean {

    @Value("${emqx.serverUri}")
    String serverUri;
    @Value("${emqx.username}")
    String username;
    @Value("${emqx.password}")
    String password;

    @Override
    public void afterPropertiesSet() throws Exception {
        run();
    }

    @Autowired
    MqttMsgCallback mqttMsgCallback;

    public void run(){
        log.warn("开始运行");
        log.warn("serverUri -> {}" , serverUri);
        log.warn("username -> {}" , username);
        log.warn("password -> {}" , password);

        String clientId = "client-uwjx-emqx";
        int qos = 2;
        String topic = "levo";
        String content = "hi, there!";

        MemoryPersistence persistence = new MemoryPersistence();
        try {
            MqttClient mqttClient = new MqttClient(serverUri , clientId , persistence);
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setUserName(username);
            connectOptions.setPassword(password.toCharArray());
            connectOptions.setCleanSession(true);
            mqttClient.setCallback(mqttMsgCallback);
            log.warn("开始建立连接...");
            mqttClient.connect(connectOptions);
            log.warn("建立成功 @@@");
            //订阅
            mqttClient.subscribe(topic);
            //发布消息
            MqttMessage message = new MqttMessage(content.getBytes(StandardCharsets.UTF_8));
            message.setQos(qos);
            mqttClient.publish(topic , message);
            log.warn("已经发布消息完毕");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
