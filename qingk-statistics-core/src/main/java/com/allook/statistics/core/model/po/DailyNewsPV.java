/**
 * 
 */
package com.allook.statistics.core.model.po;

import java.io.Serializable;

import com.allook.statistics.core.model.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 
 * @ClassName: DailyNewsPV 
 * @Description: TODO
 * @author: pengyu
 * @date: 2018年10月12日 下午4:31:15  
 */
@NoArgsConstructor
@Getter
@Setter
public class DailyNewsPV implements Serializable,Model {
	
	private String id;
	
	private Integer s_date;
	
	private String microblog_key;
	
	private String microblog_type;
	
	private String app_component_key;
	
	private String app_information_key;
	
	private Integer pv;
	
	private Integer uv;
	
	
}
