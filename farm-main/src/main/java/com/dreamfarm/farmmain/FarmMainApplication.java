package com.dreamfarm.farmmain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FarmMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(FarmMainApplication.class, args);
    }

}
