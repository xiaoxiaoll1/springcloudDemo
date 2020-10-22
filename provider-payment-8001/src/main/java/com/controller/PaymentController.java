package com.controller;



import com.pojo.CommonResults;
import com.pojo.Payment;
import com.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    PaymentService paymentService;


    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResults create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.debug("dsf"+payment);
        if(result>0){
            return new CommonResults(200,"插入消息成功serverPort"+serverPort,payment);
        }else{
            return new CommonResults(404,"插入消息失败",null);
        }
    }


    @GetMapping("/payment/get/{id}")
    public CommonResults<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        if(payment!=null){
            return new CommonResults(200,"查询成功serverPort"+serverPort,payment);
        }else {
            return new CommonResults(404,"查询失败",null);
        }

    }

    @GetMapping("/payment/discovery")
    public Object discover(){
        List<String> services = discoveryClient.getServices();
        services.forEach((service)->{
            log.info("service有"+service);
        });
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-CONSUMER-SERVICE");
        instances.forEach((instance)->{
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getInstanceId()+"\t"+instance.getUri());
        });
        List<ServiceInstance> instances2 = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances2.forEach((instance)->{
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getInstanceId()+"\t"+instance.getUri());
        });
        return discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String getServerPort(){
        return serverPort;
    }

    @GetMapping("/payment/timeout")
    public String timeout(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String zipkin(){
        return "I am zipkin";
    }
}
