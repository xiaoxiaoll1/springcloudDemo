package com.config;


import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Level;

@Configuration

public class FeignConfig {


    @Bean
    public Logger.Level feignLevel(){
        return Logger.Level.FULL;
    }
}
