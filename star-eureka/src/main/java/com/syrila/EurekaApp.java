package com.syrila;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer   //在当前SpringBoot服务中，启动Eureka服务器
public class EurekaApp {
    public static void main(String[] args){
        SpringApplication.run(EurekaApp.class,args);
    }
}
