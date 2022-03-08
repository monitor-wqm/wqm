package com.water.quality;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WaterQualityApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaterQualityApplication.class, args);
    }

}
