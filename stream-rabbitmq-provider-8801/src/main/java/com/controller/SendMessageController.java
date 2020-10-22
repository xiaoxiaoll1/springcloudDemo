package com.controller;


import com.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

    @Autowired
    IMessageService service;

    @GetMapping("/send")
    public String sendMessage(){
        String message = service.send();
        return message;
    }
}
