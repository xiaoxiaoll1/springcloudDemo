package com.service;

import org.springframework.stereotype.Component;

@Component
public class HystrixFallbackService implements HystrixService {
    @Override
    public String hystrixInfoOk(Integer id) {
        return "HystrixFallbackService hystrixInfoOk is error";
    }

    @Override
    public String hystrixInfoTimeout(Integer id) {
        return "HystrixFallbackService hystrixInfoTimeout is error";
    }
}
