package com.allook.statistics.redis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @since v1.0.0
 * @author zhuguoxiao<br>
 *         Created on 2018年5月4日
 */
@ConfigurationProperties(prefix = "qingk.redis")
public class RedisProperties {
    /**
     * redis地址，默认为localhost。
     */
    private String host = "localhost";
    /**
     * redis端口号，默认为6379。
     */
    private int port = 6379;
    /**
     * 最大空闲线程数，默认为8。
     */
    private int maxIdle = 8;
    /**
     * 最小空闲线程数，默认为0。
     */
    private int minIdle = 0;
    /**
     * 最大连接数，默认为8.
     */
    private int maxTotal = 8;
    /**
     * 最大等待时间，默认为-1。
     */
    private long maxWait = -1;
    /**
     * 每次获取连接时是否测试可用，默认为true。
     */
    private boolean testOnBorrow = true;
    /**
     * 超时时间，默认3秒。
     */
    private int timeout = 3000;
    
    private int database;

    public int getDatabase() {
		return database;
	}

	public void setDatabase(int database) {
		this.database = database;
	}

	public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public long getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(long maxWait) {
        this.maxWait = maxWait;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

}
