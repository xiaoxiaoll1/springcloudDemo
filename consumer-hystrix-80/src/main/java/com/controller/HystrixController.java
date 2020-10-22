package com.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.service.HystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "global_fallback")
public class HystrixController {

    @Autowired
    HystrixService hystrixService;


    @GetMapping("/consumer/hystrix/ok/{id}")
    public String hystrixInfoOk(@PathVariable("id") Integer id){
        return hystrixService.hystrixInfoOk(id);
    }

    @GetMapping("/consumer/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "hystrixInfoTimeoutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//
//    })
    @HystrixCommand
    public String hystrixInfoTimeout(@PathVariable("id") Integer id){
        int i = 10/0;
        return hystrixService.hystrixInfoTimeout(id);
    }

    public String hystrixInfoTimeoutHandler(Integer id){
        return Thread.currentThread().getName()+"  fallback, sorry!!!!!!!  "+id;
    }
    public String global_fallback(){
        return Thread.currentThread().getName()+"  global_fallback, sorry!!!!!!!  ";
    }
}
