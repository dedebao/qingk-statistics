/**
 * 
 */
package com.allook.statistics.database.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @ClassName: DatabaseContextHolder 
 * @Description:数据源选择器
 * @author: pengyu
 * @date: 2018年9月21日 下午4:31:43  
 */

public class DatabaseContextHolder {
	
	public static final Logger log = LoggerFactory.getLogger(DatabaseContextHolder.class);


    private static final ThreadLocal<DatasourceType> contextHolder = new ThreadLocal<>();
    
    // 设置数据源名
    public static void setDB(DatasourceType dbType) {
        log.info("选择数据源:{}", dbType);
        contextHolder.set(dbType);
    }

    // 获取数据源名
    public static DatasourceType getDB() {
        return contextHolder.get();
    }

    // 清除数据源名
    public static void clearDB() {
        contextHolder.remove();
    }

}
