package com.controller;


import com.netflix.discovery.converters.Auto;
import com.pojo.CommonResults;
import com.pojo.Payment;
import com.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    PaymentService paymentService;

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
}
