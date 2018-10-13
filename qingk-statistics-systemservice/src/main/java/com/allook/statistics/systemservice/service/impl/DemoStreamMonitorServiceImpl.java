/**
 * 
 */
package com.allook.statistics.systemservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allook.statistics.elasticsearch.framework.ESQueryUtil;
import com.allook.statistics.elasticsearch.framework.Querybody;
import com.allook.statistics.systemservice.dao.DemoStreamMonitorRepository;
import com.allook.statistics.systemservice.enity.DemoStreamMonitor;
import com.allook.statistics.systemservice.service.DemoStreamMonitorService;

/** 
 * @ClassName: StreamMonitorServiceImpl 
 * @Description: TODO
 * @author: pengyu
 * @date: 2018年9月27日 上午9:51:45  
 */
@Service("demoStreamMonitorService")
public class DemoStreamMonitorServiceImpl implements DemoStreamMonitorService {
	
	@Autowired
	private DemoStreamMonitorRepository demoStreamMonitorRepository; 

	
	@Override
	public List<DemoStreamMonitor> listDatas(Querybody querybody) {
		// TODO Auto-generated method stub		
		return ESQueryUtil.listData(querybody, demoStreamMonitorRepository);
	}

}
