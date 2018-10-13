/**
 * 
 */
package com.allook.statistics.systemservice.enity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: StreamMonitorInfo
 * @Description: 推流状态监控实体
 * @author: pengyu
 * @date: 2018年8月14日 下午3:11:50
 */
@Document(indexName = "streammonitor", type = "streaminfo", useServerConfiguration = true)
@Getter
@Setter
public class DemoStreamMonitor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String activitykey;

	private String streamname;

	private Date livetime;

	private Integer status;

}
