/**
 * 
 */
package com.allook.statistics.systemservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.allook.statistics.systemservice.enity.DemoPhData;



/** 
 * @ClassName: DemoDataMapper 
 * @Description: TODO
 * @author: pengyu
 * @date: 2018年9月20日 上午11:30:57  
 */
@Mapper
public interface DemoPhDataMapper {
	
	@Select("select * from HBASE_TEST where s1=#{s1}")
	DemoPhData getDeomById(String s1);

}
