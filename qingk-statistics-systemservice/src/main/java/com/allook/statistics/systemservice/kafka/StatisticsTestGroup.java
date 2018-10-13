package com.allook.statistics.systemservice.kafka;

import javax.annotation.PreDestroy;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.allook.statistics.kafka.consumer.ConsumerGroup;
import com.allook.statistics.kafka.consumer.ConsumerThread;

import kafka.consumer.KafkaStream;

/**
 * 
 * @since v1.0.0
 * @author pengyu<br>
 *         Created on 2018年5月8
 */
@Component
@ConditionalOnProperty(prefix = "statistics.kafka.consumer.groups.StatisticsTestGroup" /* 该实现类的类名 */, name = "topic")
public class StatisticsTestGroup extends ConsumerGroup {


	@Override
	public ConsumerThread newConsumerThread(KafkaStream<byte[], byte[]> stream, String groupId, String topic,
			int threadNumber) {
		return new LiveWorkThread(stream, groupId, topic, threadNumber);
	}

	// 该消费者组实例销毁前关闭kafka连接及线程池
	@PreDestroy
	@Override
	public void shutdown() {
		super.shutdown();
	}

	public class LiveWorkThread extends ConsumerThread {

		public LiveWorkThread(KafkaStream<byte[], byte[]> stream, String groupId, String topic, int threadNumber) {
			super(stream, groupId, topic, threadNumber);
		}

		@Override
		public void execute(String message) throws Exception {
			System.out.println(message);
			LOGGER.info("kafka消费者接收到消息:" + message);			
		}

		
	}

}
