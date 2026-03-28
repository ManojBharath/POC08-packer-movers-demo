package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RestController
public class DemoApplication {

    @GetMapping("/")
    public String home() {
        return "<h1 style='color:blue; animation: marquee 5s infinite;'>Packers and Movers</h1>";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}