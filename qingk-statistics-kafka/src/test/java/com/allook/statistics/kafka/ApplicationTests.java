package com.allook.statistics.kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.allook.statistics.kafka.producer.KafkaProducer;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationTests.class);
    
    @Autowired
    private KafkaProducer kafkaProducer;
    
    @Test
    public void findAccounts() {
    	kafkaProducer.send("new_statistics_test", System.currentTimeMillis()+"", "try a again222");
    }

}
