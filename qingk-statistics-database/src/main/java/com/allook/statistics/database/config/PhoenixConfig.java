/**
 * 
 */
package com.allook.statistics.database.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: MysqlDatabaseProperties
 * @Description: TODO
 * @author: pengyu
 * @date: 2018年9月21日 下午5:04:58
 */
@Component
@ConfigurationProperties(prefix = "qingk.phoenix")
@Getter
@Setter
public class PhoenixConfig {
	private String driverClassName;
	private String Url;
	private String username;
	private String password;
	private String autoCommit;
	private String maximumPoolSize;
	private String minimumIdle;
	private String idleTimeout;
	private String readOnly;
	private String connectionTimeout;
	private String maxLifetime;
}
