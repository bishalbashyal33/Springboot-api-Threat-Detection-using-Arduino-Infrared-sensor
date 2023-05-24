package com.threatdetectapi.threatdetectapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.threatdetectapi.threatdetectapi")
public class ThreatdetectapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThreatdetectapiApplication.class, args);
    }
}
