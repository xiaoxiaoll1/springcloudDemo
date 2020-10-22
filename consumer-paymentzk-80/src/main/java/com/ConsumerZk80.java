package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @auther zzyy
 * @create 2020-02-19 15:19
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerZk80
{
    public static void main(String[] args) {
            SpringApplication.run(ConsumerZk80.class, args);
    }
}
