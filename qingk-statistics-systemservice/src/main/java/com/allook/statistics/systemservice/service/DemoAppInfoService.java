/**
 * 
 */
package com.allook.statistics.systemservice.service;

import com.allook.statistics.systemservice.enity.DemoAppInfo;

/**
 * @ClassName: AppInfoService
 * @Description: TODO
 * @author: pengyu
 * @date: 2018年8月2日 上午10:02:32
 */
public interface DemoAppInfoService {
	DemoAppInfo getAppInfoByAppKey(String app_information_key);
}
