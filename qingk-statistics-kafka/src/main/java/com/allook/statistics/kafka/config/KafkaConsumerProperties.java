package com.allook.statistics.kafka.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @since v1.0.0
 * @author zhuguoxiao<br>
 *         Created on 2018年5月5日
 */
@ConfigurationProperties(prefix = "statistics.kafka.consumer")
public class KafkaConsumerProperties {

	private String zookeeperConnect;
	private String zookeeperSessionTimeout;
	private String autoCommitEnable;
	private String autoCommitInterval;
	private String autoOffsetReset;
	private String rebalanceBackoffMs;
	private String rebalanceMaxRetries;

	private Map<String, Group> groups;

	public String getZookeeperConnect() {
		return zookeeperConnect;
	}

	public void setZookeeperConnect(String zookeeperConnect) {
		this.zookeeperConnect = zookeeperConnect;
	}

	public String getZookeeperSessionTimeout() {
		return zookeeperSessionTimeout;
	}

	public void setZookeeperSessionTimeout(String zookeeperSessionTimeout) {
		this.zookeeperSessionTimeout = zookeeperSessionTimeout;
	}

	public String getAutoCommitEnable() {
		return autoCommitEnable;
	}

	public void setAutoCommitEnable(String autoCommitEnable) {
		this.autoCommitEnable = autoCommitEnable;
	}

	public String getAutoCommitInterval() {
		return autoCommitInterval;
	}

	public void setAutoCommitInterval(String autoCommitInterval) {
		this.autoCommitInterval = autoCommitInterval;
	}

	public String getAutoOffsetReset() {
		return autoOffsetReset;
	}

	public void setAutoOffsetReset(String autoOffsetReset) {
		this.autoOffsetReset = autoOffsetReset;
	}

	public String getRebalanceBackoffMs() {
		return rebalanceBackoffMs;
	}

	public void setRebalanceBackoffMs(String rebalanceBackoffMs) {
		this.rebalanceBackoffMs = rebalanceBackoffMs;
	}

	public String getRebalanceMaxRetries() {
		return rebalanceMaxRetries;
	}

	public void setRebalanceMaxRetries(String rebalanceMaxRetries) {
		this.rebalanceMaxRetries = rebalanceMaxRetries;
	}

	public Map<String, Group> getGroups() {
		return groups;
	}

	public void setGroups(Map<String, Group> groups) {
		this.groups = groups;
	}

	public static class Group {
		private String topic;
		private String groupId;
		private Integer concurrency;

		public String getTopic() {
			return topic;
		}

		public void setTopic(String topic) {
			this.topic = topic;
		}

		public String getGroupId() {
			return groupId;
		}

		public void setGroupId(String groupId) {
			this.groupId = groupId;
		}

		public Integer getConcurrency() {
			return concurrency;
		}

		public void setConcurrency(Integer concurrency) {
			this.concurrency = concurrency;
		}

		@Override
		public String toString() {
			return "Group [topic=" + topic + ", groupId=" + groupId + ", concurrency=" + concurrency + "]";
		}

	}

}
