/**
 * 
 */
package com.allook.statistics.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.allook.statistics.feign.service.AppInfoService;

/** 
 * @ClassName: ConsumerController 
 * @Description: TODO
 * @author: pengyu
 * @date: 2018年10月11日 下午5:30:56  
 */
@RestController
public class ConsumerController {
	
	@Autowired
	AppInfoService appInfoService;
	
	@RequestMapping(value="/customerservice-feign/getAppInfo",method=RequestMethod.GET)
	public String getAppInfo(String appkey) {
			String appInfo=appInfoService.getAppInfo(appkey);
			return appInfo;
	}
	
}
