package com.imooc.sell.loggerTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest1 {

    @Test
    public void test1(){
        String str1 = "temp1";
        String str2 = "ertsgd";
        log.debug("debug .......");
        log.info("name:{},password:{}",str1,str2);
        log.info("info .....");
        log.error("error .....");
    }
}
