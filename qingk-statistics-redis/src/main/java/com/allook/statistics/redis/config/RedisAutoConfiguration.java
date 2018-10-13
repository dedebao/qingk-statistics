package com.allook.statistics.redis.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 自定义redis自动配置
 * 
 * @since v1.0.0
 * @author zhuguoxiao<br>
 *         Created on 2018年5月4日
 */
@Configuration
// 这个类要注册到容器中，在项目上下文中必须等找到RedisTemplate.class,Jedis.class，没有这两个类就不进行配置。
@ConditionalOnClass({ RedisTemplate.class, Jedis.class })
// 使@ConfigurationProperties注解生效
@EnableConfigurationProperties(RedisProperties.class)
// application.properties中custom.redis.host必须要有，没有就不进行配置。
@ConditionalOnProperty(prefix = "qingk.redis", name = "host")
// 当满足以上所有条件时，才会将该类及其下所有的@Bean注册到spring容器中
public class RedisAutoConfiguration {

    /**
     * jedis连接池
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig(RedisProperties props) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(props.getMaxTotal());
        config.setMaxIdle(props.getMaxIdle());
        config.setMinIdle(props.getMinIdle());
        config.setMaxWaitMillis(props.getMaxWait());
        config.setTestOnBorrow(props.isTestOnBorrow());
        return config;
    }

    /**
     * jedis连接工厂
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig config, RedisProperties props) {
        JedisConnectionFactory factory = new JedisConnectionFactory(config);
        factory.setHostName(props.getHost());
        factory.setPort(props.getPort());
        factory.setDatabase(props.getDatabase());
        if (props.getTimeout() > 0) {
            factory.setTimeout(props.getTimeout());
        }
        return factory;
    }

    /**
     * redis操作模板，键：String，值：Object
     */
    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public ObjectRedisTemplate redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        ObjectRedisTemplate template = new ObjectRedisTemplate(jedisConnectionFactory);
        // 开启事务
//        template.setEnableTransactionSupport(true);
        return template;
    }

}
