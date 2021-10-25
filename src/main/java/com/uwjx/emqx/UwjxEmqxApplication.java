package com.uwjx.emqx;

import com.uwjx.emqx.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UwjxEmqxApplication {

    public static void main(String[] args) {
        SpringApplication.run(UwjxEmqxApplication.class, args);
    }

}
