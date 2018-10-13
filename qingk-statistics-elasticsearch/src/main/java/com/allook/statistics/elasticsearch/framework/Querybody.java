/**
 * 
 */
package com.allook.statistics.elasticsearch.framework;

import java.util.List;
import java.util.Map;


import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: Querybody
 * @Description: TODO
 * @author: pengyu
 * @date: 2018年9月26日 下午4:36:34
 */
@Getter
@Setter
public class Querybody  {

	private Map<String, String> termQuery;// term精确查询条件

	private String rangeQueryColumn;// range查询的列

	private String rangeQueryFrom;// range查询起始值

	private String rangeQueryTo;// range查询终止值

	private String inQueryColumn;// in查询的列

	private List<String> inQueryColumns;// in查询的条件（列值）

	private String notInQueryColumn;// not in 查询的条件

	private List<String> notInQueryColumns;// not in查询的列值

	private Integer pageFrom;// 分页起始

	private Integer pageTo;// 分页终止

	private String pageColumn;// 分页条件

	private String orderRule;// 排序规则

}
