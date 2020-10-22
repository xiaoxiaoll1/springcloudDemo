package com.service;


import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Component;

@Component
public class HystrixService {


    public String hystrixInfoOk(Integer id){
        return Thread.currentThread().getName()+"  ok  "+id;
    }


    @HystrixCommand(fallbackMethod = "hystrixInfoTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")

    })
    public String hystrixInfoTimeout(Integer id){
        try {
            Thread.sleep(3000);
//            int i = 10/0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Thread.currentThread().getName()+"  timeout  "+id;
    }

    public String hystrixInfoTimeoutHandler(Integer id){
        return Thread.currentThread().getName()+"  fallback, sorry!!!!!!!  "+id;
    }


    @HystrixCommand(fallbackMethod = "circuitBreakerFallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
    })
    public String paymentCircuitBreaker(Integer id){
        if(id<0){
            throw new RuntimeException("error id");
        }
        String uu = IdUtil.simpleUUID();
        return uu+" paymentCircuitBreaker"+Thread.currentThread().getName();
    }

    public String circuitBreakerFallback(Integer id){
        return "circuit breaker fall back " +id;
    }
}
