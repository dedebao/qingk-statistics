/**
 * 
 */
package com.allook.statistics.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * @ClassName: AppInfoService 
 * @Description: TODO
 * @author: pengyu
 * @date: 2018年10月11日 下午5:13:07  
 */
@FeignClient("systemservice")
public interface AppInfoService {
	
	@RequestMapping("/getAppInfo")
	String getAppInfo(String appkey);
}
