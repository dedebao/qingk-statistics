package com.allook.statistics.kafka.producer;

import java.util.Properties;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.allook.statistics.kafka.config.KafkaProducerProperties;

//import com.allook.statistics.kafka.config.KafkaProducerProperties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

@Configuration
@EnableConfigurationProperties(KafkaProducerProperties.class)
@ConditionalOnProperty(prefix = "statistics.kafka.producer", name = "zookeeperConnect")
public class KafkaProducer {
		
    private Producer<String, Object> producer;
        
    public KafkaProducer (KafkaProducerProperties kafkaProducerProperties) {
    	Properties props = new Properties();
		props.put("zookeeper.connect",
				kafkaProducerProperties.getZookeeperConnect());
		props.put("metadata.broker.list",
				kafkaProducerProperties.getKafkaConnect());
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("key.serializer.class", "kafka.serializer.StringEncoder");
		props.put("request.required.acks", "1");

		// 决定消息是否应在一个后台线程异步发送,合法的值为async，表示异步发送；sync表示同步发送，默认值sync
		props.put("producer.type", "async");

		// 当使用异步模式时，缓冲数据的最大时间 默认值：5000
		props.put("queue.buffering.max.ms", "100");

		// 在异步模式下，producer端允许buffer的最大消息数量 默认值：10000
		props.put("queue.buffering.max.messages", "10000");

		// 值为-1 则 无阻塞超时限制，消息不会被抛弃；如果值为0 则立即清空队列，消息被抛弃 默认值：-1
		props.put("queue.enqueue.timeout.ms", "-1");

		// 在异步模式下，一个batch发送的消息数量 默认值：200
		props.put("batch.num.messages", "200");

		ProducerConfig config = new ProducerConfig(props);
    	this.producer = new Producer<>(config);
    }
    
    public void send(String topic, String key, String message) {
        KeyedMessage<String, Object> data = new KeyedMessage<>(topic, key, message);
        producer.send(data);
    }
}