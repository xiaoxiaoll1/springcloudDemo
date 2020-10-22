package com.controller;


import com.service.HystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HystrixController {

    @Autowired
    HystrixService service;

    @GetMapping("/hystrix/ok/{id}")
    public String hystrixInfoOk(@PathVariable("id") Integer id){
        return service.hystrixInfoOk(id);
    }

    @GetMapping("/hystrix/timeout/{id}")
    public String hystrixInfoTimeout(@PathVariable("id") Integer id){
        return service.hystrixInfoTimeout(id);
    }

    @GetMapping("/hystrix/circuitBreaker/{id}")
    public String hystrixCircuitBreaker(@PathVariable("id")Integer id){
        return service.paymentCircuitBreaker(id);
    }
}
