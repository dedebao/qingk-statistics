/**
 * 
 */
package com.allook.statistics.systemservice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.allook.statistics.core.model.po.DailyNewsPV;
import com.allook.statistics.systemservice.enity.DemoAppInfo;

/** 
 * @ClassName: DailyNewPVMapper 
 * @Description: TODO
 * @author: pengyu
 * @date: 2018年10月12日 下午4:44:16  
 */
@Mapper
public interface DailyNewPVMapper {
	
	@Select("<script>"
			+ "SELECT "
			+ "ID,S_DATE,MICROBLOG_KEY,MICROBLOG_TYPE,APP_COMPONENT_KEY,APP_INFORMATION_KEY,PV,UV"
			+ "FROM qkstatistics.DAILY_NEWS_PV  "
			+ "WHERE APP_INFORMATION_KEY = #{demandId} "
			+ "ORDER BY a.create_date ASC "
			+ "<if test='startNo!=null and pageSize != null '>"
			+ "LIMIT #{startNo},#{pageSize}"
			+ "</if>"
			+ "</script>")
	List<DailyNewsPV> ListDailyNewsPV(DailyNewsPV pv,@Param("startNo") Integer pageNo, @Param("pageSize") Integer pageSize);

}
