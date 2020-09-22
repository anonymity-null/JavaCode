package com.maki;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class SpringBootMyblogApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMyblogApplication.class, args);

    }

}
