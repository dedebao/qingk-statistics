/**
 * 
 */
package com.allook.statistics.systemservice.service;

import java.util.List;

import com.allook.statistics.elasticsearch.framework.Querybody;
import com.allook.statistics.systemservice.enity.DemoStreamMonitor;


/** 
 * @ClassName: StreamMonitorService 
 * @Description: TODO
 * @author: pengyu
 * @date: 2018年9月27日 上午9:50:33  
 */

public interface DemoStreamMonitorService {
	
	List<DemoStreamMonitor> listDatas(Querybody querybody);

}
