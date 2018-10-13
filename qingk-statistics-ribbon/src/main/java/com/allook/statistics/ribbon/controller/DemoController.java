/**
 * 
 */
package com.allook.statistics.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.allook.statistics.ribbon.service.PhoenixDataService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;






/** 
 * @ClassName: DemoController 
 * @Description: TODO
 * @author: pengyu
 * @date: 2018年9月20日 下午4:57:22  
 */
@RestController
public class DemoController {
	
	@Autowired
	PhoenixDataService phoenixDataService;
	
	@RequestMapping(value="/customerservice-ribbon/getDemoPhdata",method=RequestMethod.GET)
	public String getDemoPhdata(String id) {
		return phoenixDataService.getDemoPhdata(id);
	}
	
}
