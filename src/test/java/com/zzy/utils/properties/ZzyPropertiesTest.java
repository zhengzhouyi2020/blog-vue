package com.zzy.utils.properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZzyPropertiesTest {

    @Autowired
    private ZzyProperties zzyProperties;
    @Test
    public void getShiro() {
        ShiroProperties shiro= zzyProperties.getShiro();
        SwaggerProperties swagger=zzyProperties.getSwagger();
        System.out.println(shiro.getCipherKey()+swagger.getAuthor());
    }
}