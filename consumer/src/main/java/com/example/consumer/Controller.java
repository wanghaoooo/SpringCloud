package com.example.consumer;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {
    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
    @Autowired
    private RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "executeHystrixHandle")
    @RequestMapping("/api/test/haha/get")
    public String consumer(){
        return this.restTemplate.getForObject("http://localhost:6110/producer/haha", String.class);
    }
    public String executeHystrixHandle() {
        return  "警报，服务器负载过高";
    }
}
