package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ConfigClient3366Main {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClient3366Main.class, args);
    }
}
