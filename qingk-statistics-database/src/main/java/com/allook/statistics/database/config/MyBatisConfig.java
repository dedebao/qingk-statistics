/**
 * 
 */
package com.allook.statistics.database.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.allook.statistics.database.framework.DatasourceType;
import com.allook.statistics.database.framework.DynamicDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @ClassName: MyBatisConfig
 * @Description: TODO
 * @author: pengyu
 * @date: 2018年9月25日 下午4:47:27
 */
@Configuration
public class MyBatisConfig {

	@Autowired
	private MysqlConfig mysqldb;

	@Autowired
	private PhoenixConfig phoenixdb;
	
	@Value("qingk.enitypackage")
	private String enitypackage;

	@Bean
	public DataSource mysqldatasource() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(mysqldb.getDriverClassName());
		config.setJdbcUrl(mysqldb.getUrl());
		config.setUsername(mysqldb.getUsername());
		config.setPassword(mysqldb.getPassword());
		config.addDataSourceProperty("useServerPrepStmts", true);
		config.addDataSourceProperty("cachePrepStmts", true);
		config.addDataSourceProperty("prepStmtCacheSize", 1000);
		config.addDataSourceProperty("prepStmtCacheSqlLimit", 500);
		config.addDataSourceProperty("useUnicode", true);
		config.addDataSourceProperty("characterEncoding", "UTF-8");
		config.setAutoCommit(true);
		config.setReadOnly(false);
		config.setConnectionTimeout(30000);
		config.setIdleTimeout(30000);
		config.setMaxLifetime(1800000);
		config.setMaximumPoolSize(Integer.parseInt(mysqldb.getMaximumPoolSize()));
		config.setMinimumIdle(Integer.parseInt(mysqldb.getMinimumIdle()));
		config.setPoolName(DatasourceType.mysqldb.toString());
		return new HikariDataSource(config);
	}

	@Bean
	public DataSource phoenixdatasource() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(phoenixdb.getDriverClassName());
		config.setJdbcUrl(phoenixdb.getUrl());
		config.setUsername(phoenixdb.getUsername());
		config.setPassword(phoenixdb.getPassword());
		config.addDataSourceProperty("useServerPrepStmts", true);
		config.addDataSourceProperty("cachePrepStmts", true);
		config.addDataSourceProperty("prepStmtCacheSize", 1000);
		config.addDataSourceProperty("prepStmtCacheSqlLimit", 500);
		config.addDataSourceProperty("useUnicode", true);
		config.addDataSourceProperty("characterEncoding", "UTF-8");
		config.setAutoCommit(true);
		config.setReadOnly(false);
		config.setConnectionTimeout(30000);
		config.setIdleTimeout(30000);
		config.setMaxLifetime(1800000);
		config.setMaximumPoolSize(Integer.parseInt(phoenixdb.getMaximumPoolSize()));
		config.setMinimumIdle(Integer.parseInt(phoenixdb.getMinimumIdle()));
		config.setPoolName(DatasourceType.phoenixdb.toString());
		return new HikariDataSource(config);
	}

	@Bean
	@Primary
	public DynamicDataSource dataSource(@Qualifier("mysqldatasource") DataSource mysqldatasource,
			@Qualifier("phoenixdatasource") DataSource phoenixdatasource) {
		DynamicDataSource dynamicDataSource = new DynamicDataSource();

		// 默认数据源mysql
		dynamicDataSource.setDefaultTargetDataSource(mysqldatasource);

		// 配置多数据源
		Map<Object, Object> dsMap = new HashMap<Object, Object>(2);
		dsMap.put(DatasourceType.phoenixdb, phoenixdatasource);
		dsMap.put(DatasourceType.mysqldb, mysqldatasource);

		dynamicDataSource.setTargetDataSources(dsMap);

		return dynamicDataSource;

	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(@Qualifier("mysqldatasource") DataSource mysqldatasource,
			@Qualifier("phoenixdatasource") DataSource phoenixdatasource) throws Exception {
		SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
		fb.setDataSource(this.dataSource(mysqldatasource, phoenixdatasource));
		fb.setTypeAliasesPackage(enitypackage);
		return fb.getObject();
	}

}
