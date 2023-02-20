package com.coderpeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan("com.coderpeng.filter")
@SpringBootApplication
public class XiaopengSichuanRestaurantApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaopengSichuanRestaurantApplication.class, args);
    }

}
