package com.allook.statistics.systemservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.allook.statistics.elasticsearch.framework.Querybody;
import com.allook.statistics.kafka.producer.KafkaProducer;
import com.allook.statistics.redis.service.RedisService;
import com.allook.statistics.systemservice.enity.DemoAppInfo;
import com.allook.statistics.systemservice.enity.DemoPhData;
import com.allook.statistics.systemservice.enity.DemoStreamMonitor;
import com.allook.statistics.systemservice.service.DemoAppInfoService;
import com.allook.statistics.systemservice.service.DemoPhDataService;
import com.allook.statistics.systemservice.service.DemoStreamMonitorService;


/**
 * 
 * @ClassName: UnitTest 
 * @Description: TODO
 * @author: pengyu
 * @date: 2018年7月26日 上午9:30:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemServiceApplicationTests {
	
	@Autowired
    private DemoPhDataService demoDataService;
    
    @Autowired
    DemoAppInfoService appInfoService;
    
    //测试database数据源切换
    @Test
	public void contextLoads() {
    	DemoPhData dd=demoDataService.getDataById("1");
    	System.out.println(dd.getS3()+"-"+dd.getS2()+"-"+dd.getS3()+"-"+dd.getS4());
    	DemoAppInfo appInfo=appInfoService.getAppInfoByAppKey("wbcpvsadefbfsoqaaxocqptersppoxov");
    	System.out.println(appInfo.getApp_information_key()+"*****"+appInfo.getUrl());
	}
    
    @Autowired
    private DemoStreamMonitorService streamMonitorService;
    
    @Autowired
    private RedisService redisService;
    
    //测试es
    @Test
    public void findAccounts() {
        Querybody querybody=new Querybody();
        Map<String,String> termQuery=new HashMap<String,String>();
        termQuery.put("activitykey", "45a33a7d142d4faba98cf4aa144c8089");
        termQuery.put("status", "0");
        querybody.setTermQuery(termQuery);
        List<DemoStreamMonitor> list=streamMonitorService.listDatas(querybody);
        System.out.println(list.size());
    }
    
    
    //测试redis
    @Test
    public void testRedis() {
    	String m3u8Url=redisService.hget("qnlive_893c3952f1db454fba92a26c0f0f8b31", "m3u8Url");
    	System.out.println(m3u8Url);
    }
    
    
    @Autowired
    private KafkaProducer kafkaProducer;
    
    @Test
    public void testKafka() {
    	kafkaProducer.send("new_statistics_test", System.currentTimeMillis()+"", "try a again333");
    }
    
    
    
    

}
