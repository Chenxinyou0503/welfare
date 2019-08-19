package com.welfare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.welfare.dao")
public class WelfareApplication {

    public static void main(String[] args) {
        SpringApplication.run(WelfareApplication.class, args);
    }

}
