/**
 * 
 */
package com.allook.statistics.systemservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.allook.statistics.database.framework.DatasourceType;
import com.allook.statistics.database.framework.TargetDatasource;
import com.allook.statistics.systemservice.enity.DemoAppInfo;
import com.allook.statistics.systemservice.mapper.DemoAppInfoMapper;
import com.allook.statistics.systemservice.service.DemoAppInfoService;


/**
 * @ClassName: AppInfoServiceImpl
 * @Description: TODO
 * @author: pengyu
 * @date: 2018年8月2日 上午10:22:45
 */
@Service("demoAppInfoService")
public class DemoAppInfoServiceImpl implements DemoAppInfoService {

	@Autowired
	DemoAppInfoMapper demoAppInfoMapper;

	@Override
	@TargetDatasource(DatasourceType.mysqldb)
	public DemoAppInfo getAppInfoByAppKey(String app_information_key) {
		// TODO Auto-generated method stub
		return demoAppInfoMapper.getOneByAppKey(app_information_key);
	}

}
