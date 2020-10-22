package com.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    static final String URL = "http://cloud-provider-payment";



    @GetMapping("/consumer/zk")
        public String paymentInfo()
        {
            String result = restTemplate.getForObject(URL+"/payment/zk",String.class);
            return result;

    }
}
