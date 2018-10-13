/**
 * 
 */
package com.allook.statistics.kafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/** 
 * @ClassName: KafkaProducerProperties 
 * @Description: TODO
 * @author: pengyu
 * @date: 2018年9月27日 下午3:00:15  
 */
@ConfigurationProperties(prefix = "statistics.kafka.producer")
public class KafkaProducerProperties {
	
	private String zookeeperConnect;
	
	private String kafkaConnect;

	public String getZookeeperConnect() {
		return zookeeperConnect;
	}

	public void setZookeeperConnect(String zookeeperConnect) {
		this.zookeeperConnect = zookeeperConnect;
	}

	public String getKafkaConnect() {
		return kafkaConnect;
	}

	public void setKafkaConnect(String kafkaConnect) {
		this.kafkaConnect = kafkaConnect;
	}

}