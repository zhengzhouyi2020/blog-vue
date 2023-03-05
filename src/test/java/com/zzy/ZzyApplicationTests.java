package com.zzy;

import com.zzy.utils.properties.ZzyProperties;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ZzyApplicationTests {

    @Autowired
    ZzyProperties zzyProperties;

    @Test
    public void test() {
        System.out.println(zzyProperties.getSwagger());
    }


}
