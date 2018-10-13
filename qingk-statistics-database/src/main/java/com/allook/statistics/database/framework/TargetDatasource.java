/**
 * 
 */
package com.allook.statistics.database.framework;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

/** 
 * @ClassName: DataSource 
 * @Description: 指定数据源注解
 * @author: pengyu
 * @date: 2018年10月8日 下午3:45:40  
 */
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDatasource {
	DatasourceType value() default DatasourceType.mysqldb;
}
