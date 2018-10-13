package com.allook.statistics.redis.config;

import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * <code>RedisTemplate&lt;Object, Object&gt;</code>的扩展
 * 
 * @since v1.0.0
 * @author zhuguoxiao<br>
 *         Created on 2018年5月8日
 */
public class ObjectRedisTemplate extends RedisTemplate<String, Object> {

    /**
     * 构造一个<code>RedisTemplate&lt;Object, Object&gt;</code>的实例。
     * <p>
     * 仍然需要调用{@link #setConnectionFactory(RedisConnectionFactory)}和{@link #afterPropertiesSet()}。
     */
    public ObjectRedisTemplate() {
        // (1) GenericJackson2JsonRedisSerializer: 可以同时支持多种不同类型的domain对象序列化和反序列化
        // (2) GenericToStringSerializer: 可以将任何对象泛化为字符串并序列化
        // (3) Jackson2JsonRedisSerializer: 使用Jackson库将对象序列化为JSON字符串; 优点是速度快,序列化后的字符串短小精悍,缺点是需要给每一种domain对象都配置一个Serializer
        // (4) JacksonJsonRedisSerializer: 序列化object对象为json字符串
        // (5) JdkSerializationRedisSerializer:
        // 序列化java对象,被序列化的对象必须实现Serializable接口;优点是反序列化时无需提供类型信息(class),缺点是序列化后的结果非常庞大,是JSON格式的5倍左右,这样就会消耗redis服务器的大量内存
        // (6) OxmSerializer:
        // (7) StringRedisSerializer: 简单的字符串序列化
        // 使用GenericJackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        //RedisSerializer<Object> objectSerializer = new GenericJackson2JsonRedisSerializer();
        setKeySerializer(stringSerializer);
        setValueSerializer(stringSerializer);
        setHashKeySerializer(stringSerializer);
        setHashValueSerializer(stringSerializer);
    }

    /**
     * 构造一个新的<code>RedisTemplate&lt;Object, Object&gt;</code>的实例以便使用。
     * 
     * @param connectionFactory
     *            Rredis连接工厂
     */
    public ObjectRedisTemplate(RedisConnectionFactory connectionFactory) {
        this();
        setConnectionFactory(connectionFactory);
        afterPropertiesSet();
    }

    protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
        return new DefaultStringRedisConnection(connection);
    }

}
