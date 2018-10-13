package com.allook.statistics.kafka.consumer;

import java.util.Collection;
import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.allook.statistics.kafka.config.KafkaConsumerProperties;
import com.allook.statistics.kafka.config.KafkaConsumerProperties.Group;
import com.allook.statistics.kafka.config.SpringContextUtils;

/**
 * 自定义kafka自动配置
 * 
 * @since v1.0.0
 * @author zhuguoxiao<br>
 *         Created on 2018年5月5日
 */
@Configuration
// 使@ConfigurationProperties注解生效
@EnableConfigurationProperties(KafkaConsumerProperties.class)
// application.properties中custom.kafka.consumer必须要有，没有就不进行配置。
@ConditionalOnProperty(prefix = "statistics.kafka.consumer", name = "zookeeperConnect")
public class KafkaConsumerAutoConfiguration {

	@Bean("autoSubmitConsumerThreads")
	public boolean autoSubmitConsumerThreads(KafkaConsumerProperties properties) {
		Map<String, ConsumerGroup> consumerGroupsMap = SpringContextUtils.getBeansByClass(ConsumerGroup.class);

		Collection<ConsumerGroup> consumerGruops = consumerGroupsMap.values();

		for (ConsumerGroup consumerGroup : consumerGruops) {
			Group group = properties.getGroups().get(consumerGroup.getClass().getSimpleName());
			consumerGroup.run(group, properties);
		}

		return true;
	}

}
