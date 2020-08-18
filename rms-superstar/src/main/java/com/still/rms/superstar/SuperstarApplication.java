package com.still.rms.superstar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan({"com.still.rms.superstar.dao","com.still.rms.mbg.mapper"})
@ComponentScan({"com.still.rms.superstar","com.still.rms.security"})
public class SuperstarApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperstarApplication.class, args);
    }

}
