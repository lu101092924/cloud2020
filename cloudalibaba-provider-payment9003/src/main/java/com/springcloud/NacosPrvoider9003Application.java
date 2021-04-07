package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosPrvoider9003Application {
    public static void main(String[] args) {
        SpringApplication.run(NacosPrvoider9003Application.class,args);
    }
}
