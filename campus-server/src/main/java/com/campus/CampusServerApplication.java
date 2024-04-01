package com.campus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.properties","com.campus"})
@MapperScan("com.campus.mapper")


public class CampusServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusServerApplication.class, args);
    }

}

