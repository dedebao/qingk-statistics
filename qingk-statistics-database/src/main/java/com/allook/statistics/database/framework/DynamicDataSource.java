/**
 * 
 */
package com.allook.statistics.database.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/** 
 * @ClassName: DynamicDataSource 
 * @Description: TODO
 * @author: pengyu
 * @date: 2018年10月8日 下午3:40:25  
 */

public class DynamicDataSource extends AbstractRoutingDataSource {

	private static final Logger log = LoggerFactory.getLogger(DynamicDataSource.class);
	
	@Override
	protected Object determineCurrentLookupKey() {
		log.info("动态切换数据源为{}", DatabaseContextHolder.getDB());
        return DatabaseContextHolder.getDB();
	}
	
}
