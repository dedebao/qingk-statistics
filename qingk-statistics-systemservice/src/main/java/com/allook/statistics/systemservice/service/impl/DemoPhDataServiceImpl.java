/**
 * 
 */
package com.allook.statistics.systemservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allook.statistics.database.framework.DatasourceType;
import com.allook.statistics.database.framework.TargetDatasource;
import com.allook.statistics.systemservice.enity.DemoPhData;
import com.allook.statistics.systemservice.mapper.DemoPhDataMapper;
import com.allook.statistics.systemservice.service.DemoPhDataService;



/** 
 * @ClassName: DemoDataServiceImpl 
 * @Description: TODO
 * @author: pengyu
 * @date: 2018年9月20日 下午4:54:04  
 */
@Service("demoPhDataService")
public class DemoPhDataServiceImpl implements DemoPhDataService {

	@Autowired
	DemoPhDataMapper demoPhmapper;
	
	@Override
	@TargetDatasource(DatasourceType.phoenixdb)
	public DemoPhData getDataById(String id) {
		// TODO Auto-generated method stub
		return demoPhmapper.getDeomById(id);
	}

}
