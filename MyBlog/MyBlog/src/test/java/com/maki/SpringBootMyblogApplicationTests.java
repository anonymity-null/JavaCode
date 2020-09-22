package com.maki;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class SpringBootMyblogApplicationTests {

    @Autowired
    private DataSource ds;
    @Test
    void contextLoads() {
        System.out.println(ds);
    }

}
