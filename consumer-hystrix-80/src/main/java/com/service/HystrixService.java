package com.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = HystrixFallbackService.class)
public interface HystrixService {

    @GetMapping("/hystrix/ok/{id}")
    public String hystrixInfoOk(@PathVariable("id") Integer id);

    @GetMapping("/hystrix/timeout/{id}")
    public String hystrixInfoTimeout(@PathVariable("id") Integer id);





}
