package com.allook.statistics.kafka.consumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.allook.statistics.kafka.config.KafkaConsumerProperties;
import com.allook.statistics.kafka.config.KafkaConsumerProperties.Group;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

/**
 * @since v1.0.0
 * @author zhuguoxiao<br>
 *         Created on 2018年5月3日
 */
public abstract class ConsumerGroup {
	private static final Integer EXECUTOR_SHUTDOWN_TIMEOUT_SECONDS = 10;
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerGroup.class);

	/** 线程池 **/
	private ExecutorService executor;
	/** 消费者连接 **/
	private ConsumerConnector connector;

	/**
	 * 并发执行消费者线程
	 * 
	 * @param group
	 * @param kafkaProp
	 * @since v1.0.0
	 * @author zhuguoxiao<br>
	 *         Created on 2018年5月7日
	 */
	public void run(Group group, KafkaConsumerProperties kafkaProp) {
		String topic = group.getTopic();
		String groupId = group.getGroupId();
		Integer concurrency = group.getConcurrency();

		if (StringUtils.isEmpty(topic)) {
			LOGGER.error("未指定Topic.");
			return;
		}
		if (StringUtils.isEmpty(groupId)) {
			groupId = topic + "Group";
		}
		if (null == concurrency || concurrency <= 0) {
			concurrency = 1;
		}

		this.connector = Consumer.createJavaConsumerConnector(createConsumerConfig(groupId, kafkaProp));
		this.executor = Executors.newFixedThreadPool(concurrency);

		// 创建并发的consumers，即需要几个线程来读取消息
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic, concurrency);

		// 创建消息流streams，每个线程对应一个KafkaStream
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = connector.createMessageStreams(topicCountMap);
		List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

		// 根据消息流创建消费者线程
		int threadNumber = 0;
		for (final KafkaStream<byte[], byte[]> stream : streams) {
			// 开启线程
			executor.submit(newConsumerThread(stream, groupId, topic, threadNumber));
			threadNumber++;
		}
	}

	private ConsumerConfig createConsumerConfig(String groupId, KafkaConsumerProperties kafkaProp) {
		Properties props = new Properties();
		props.put("zookeeper.connect", kafkaProp.getZookeeperConnect());
		props.put("zookeeper.session.timeout.ms", kafkaProp.getZookeeperSessionTimeout());
		props.put("auto.commit.enable", kafkaProp.getAutoCommitEnable());
		props.put("auto.commit.interval.ms", kafkaProp.getAutoCommitInterval());
		props.put("auto.offset.reset", kafkaProp.getAutoOffsetReset());
		props.put("rebalance.backoff.ms", kafkaProp.getRebalanceBackoffMs());
		props.put("rebalance.max.retries", kafkaProp.getRebalanceMaxRetries());
		props.put("group.id", groupId);
		return new ConsumerConfig(props);
	}

	/**
	 * 消费者线程工厂方法
	 * 
	 * @param stream       kafka消息流
	 * @param groupId      组ID
	 * @param topic        主题
	 * @param threadNumber 线程号
	 * @return 自定义的消费者线程
	 * @since v1.0.0
	 * @author zhuguoxiao<br>
	 *         Created on 2018年5月7日
	 */
	public abstract ConsumerThread newConsumerThread(KafkaStream<byte[], byte[]> stream, String groupId, String topic,
			int threadNumber);

	/**
	 * 停机关闭kafka连接和线程池
	 */
	public void shutdown() {
		if (connector != null) {
			connector.shutdown();
		}
		if (executor != null) {
			executor.shutdown();
		}
		try {
			if (!executor.awaitTermination(EXECUTOR_SHUTDOWN_TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
				LOGGER.warn("消费者组:{}, 关闭线程池等待{}秒后超时.", this.getClass().getSimpleName(),
						EXECUTOR_SHUTDOWN_TIMEOUT_SECONDS);
			}
		} catch (InterruptedException e) {
			LOGGER.warn("消费者组:{}, 关闭线程池过程中被中断.", e);
		}
	}

}
