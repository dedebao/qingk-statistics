/**
 * 
 */
package com.allook.statistics.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/** 
 * @ClassName: PhoenixDataService 
 * @Description: TODO
 * @author: pengyu
 * @date: 2018年10月12日 下午1:56:25  
 */
@Service
public class PhoenixDataService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/customerservice-ribbon/getDemoPhdata",method=RequestMethod.GET)
	@HystrixCommand(fallbackMethod="phoenixDataFallback")
	public String getDemoPhdata(String id) {
		return restTemplate.getForEntity("http://SYSTEMSERVICE/getPhoenixData?id={1}", String.class,id).getBody();
	}
	
	public String phoenixDataFallback() {
		return "error";
	}
}
