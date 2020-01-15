package com.ctgu.springbootbase.config.dao;

import java.beans.PropertyVetoException;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**  
* @Package com.ctgu.springbootbase.config.dao  
*
* @Description:  配置datasource到ioc容器，对应spring-dao.xml
*
*/
@Configuration
@MapperScan("com.ctgu.springbootbase.dao")
public class DataSourceConfiguration {
	@Value("${jdbc.driver}")
	private String jdbcDriver;
	@Value("${jdbc.url}")
	private String jdbcUrl;
	@Value("${jdbc.username}")
	private String jdbcUsername;
	@Value("${jdbc.password}")
	private String jdbcPassword;

	/**
	 * 生成与spring-dao.xml对应的bean dataSource
	 * @throws PropertyVetoException 
	 */
	@Bean(name="dataSource")
	public ComboPooledDataSource createDataSource() throws PropertyVetoException {
		//生成datasource实例
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		//以下跟配置文件一样设置
		//驱动
		dataSource.setDriverClass(jdbcDriver);
		//数据库链接URL
		dataSource.setJdbcUrl(jdbcUrl);
		//设置用户名、密码
		dataSource.setUser(jdbcUsername);
		dataSource.setPassword(jdbcPassword);
		
		//连接池最小线程数
		dataSource.setMinPoolSize(10);
		//连接池最大线程数
		dataSource.setMaxPoolSize(30);
		//关闭连接后不自动commit
		dataSource.setAutoCommitOnClose(false);
		//链接超时事件
		dataSource.setCheckoutTimeout(10000);
		//链接失败重试次数
		dataSource.setAcquireIncrement(2);
		//最大空闲时间
		dataSource.setMaxIdleTime(60);
		//每60秒检查一次连接池中空闲链接
		dataSource.setIdleConnectionTestPeriod(60);
		return dataSource;
	}
}
