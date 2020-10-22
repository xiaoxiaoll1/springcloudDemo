package com.controller;


import com.pojo.CommonResults;
import com.pojo.Payment;
import com.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    PaymentService service;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResults<Payment> getPaymentById(@PathVariable("id") Long id){
        CommonResults<Payment> paymentById = service.getPaymentById(id);
        return paymentById;
    }

    @GetMapping("/consumer/payment/timeout")
    public String timeout(){
        String timeout = service.timeout();
        return timeout;
    }
}
