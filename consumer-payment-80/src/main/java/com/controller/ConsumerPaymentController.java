package com.controller;


import com.lb.LoadBalanced;

import com.pojo.CommonResults;
import com.pojo.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class ConsumerPaymentController {

    private static final String URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    RestTemplate restTemplate;

    //面向接口编程
    @Autowired
    LoadBalanced loadBalanced;

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/create")
    public CommonResults<Payment> create(Payment payment){
        CommonResults results = restTemplate.postForObject(URL + "/payment/create", payment, CommonResults.class);
        return results;
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResults<Payment> getPaymentById(@PathVariable("id") Long id){
        CommonResults com = restTemplate.getForObject(URL + "/payment/get/" + id, CommonResults.class);
        return com;
    }

    @GetMapping("/ribbon/payment/get/{id}")
    public CommonResults<Payment> getPaymentByIdRibbon(@PathVariable("id") Long id){
         ResponseEntity<CommonResults> entity = restTemplate.getForEntity(URL + "/payment/get/" + id, CommonResults.class);

        if(entity.getStatusCode().is2xxSuccessful()){
            return  entity.getBody();
        }else {
            return new CommonResults<>(444,"fail");
        }
    }

    @GetMapping("/consumer/payment/createRibbon")
    public CommonResults<Payment> createRibbon(Payment payment){
        ResponseEntity<CommonResults> entity = restTemplate.postForEntity(URL + "/payment/create", payment, CommonResults.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            return  entity.getBody();
        }else {
            return new CommonResults<>(444,"fail");
        }
    }

    @GetMapping("/consumer/payment/lb")
    public String loadBalancedTest(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        ServiceInstance ins = loadBalanced.instances(instances);
        URI uri = ins.getUri();
        return restTemplate.getForObject(uri + "/payment/1b", String.class);
    }

    @GetMapping("/consumer/payment/zipkin")
    public String zipkin(){
        String forObject = restTemplate.getForObject(URL + "/payment/zipkin", String.class);
        return forObject;
    }

}
