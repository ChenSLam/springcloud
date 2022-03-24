package com.bjpowernode.springcloud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class TestController {
    //注入一个Rest风格的请求模板对象
    @Resource
    private RestTemplate restTemplate;
    @RequestMapping("/test")
    public String test(){
        /*
            RestTemplate是一个基于Http协议的一个工具对象
            可以利用这个对象,以http协议发送请求到指定的某个web服务器中
            在SpringCloud中可以利用这个对象来访问服务提供者
            这个对象可以new,也可以交给Spring来创建(一般是交给Spring创建)
        * */
       // RestTemplate restTemplate = new RestTemplate();

        /*getForEntity 方法 是一个get方法提交请求,访问web服务器中的某个请求对应着另外一个工程的
        * @GetMapping或RequestMapping
        * 参数1:需要访问的具体请求路径
        * 参数2:本次请求以后服务器返回的数据类型
        *
        * 注意:由于SpringCloud返回的数据全部是Rest风格的数据   因此,所有数据类型全部都是String类型的Json数据格式
        * 可以根据具体的数据格式来指定返回类型交给Spring完成数据的封装
        *
        * 参数3:为可变长度的Object类型数据,表示本次请求中的参数数据
        *
        * 返回值:ResponseEntity对象,这个对象封装本次请求后的响应实体
        * 这个对象中我们可以获取本次请求的状态码 或 头文件信息,以及具体响应数据*/

        /*通过注册中心发现服务并访问服务器其中02-eureka-client-provider就是服务在注册中心的名称(不区分大小写)
        * Spring Cloud会根据这个服务名到注册中心获取服务名所对应的服务所在IP+端口号然后利用RestTemplate访问服务*/
        ResponseEntity<String> result = restTemplate.getForEntity("http://03-EUREKA-CLUSTER-CLIENT-PROVIDER/test", String.class);
        System.out.println("1111111"+result.getStatusCode());//获取响应编码  例如 200 或 404 等
        System.out.println("2222222"+result.getHeaders());//获取响应的头
        String body = result.getBody();//获取具体的响应数据具体类型取决于getForEntity方法的参数2
        return "使用了Eureka的服务消费者"+body;
    }
}
