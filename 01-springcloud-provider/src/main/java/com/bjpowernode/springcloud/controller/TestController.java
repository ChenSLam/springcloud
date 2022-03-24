package com.bjpowernode.springcloud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {
    @RequestMapping("/test")
    public String test(){

        return "第一个SpringCloud的服务提供者===";
    }
}
