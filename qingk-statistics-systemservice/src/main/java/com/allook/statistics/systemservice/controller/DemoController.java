/**
 * 
 */
package com.allook.statistics.systemservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.allook.statistics.systemservice.enity.DemoPhData;
import com.allook.statistics.systemservice.service.DemoAppInfoService;
import com.allook.statistics.systemservice.service.DemoPhDataService;



/** 
 * @ClassName: DemoController 
 * @Description: TODO
 * @author: pengyu
 * @date: 2018年9月20日 下午4:57:22  
 */
@RestController
public class DemoController {
	
	
	@Autowired
	private DemoPhDataService demoPhDataService;
	
	@Autowired
	private DemoAppInfoService demoAppInfoService;
	
	@RequestMapping(value="/getPhoenixData",method=RequestMethod.GET)
	public String getPhoenixData(String id) {		
		return demoPhDataService.getDataById(id).toJson();
	}
	
	@RequestMapping(value="/getAppInfo",method=RequestMethod.POST)
	public String getAppInfo(@RequestBody String appkey) {
		 String result=demoAppInfoService.getAppInfoByAppKey(appkey).toJson();
		 return result;
	}

}
