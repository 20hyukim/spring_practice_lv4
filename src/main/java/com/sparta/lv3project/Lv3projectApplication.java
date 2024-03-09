package com.sparta.lv3project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Lv3projectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lv3projectApplication.class, args);
    }

}