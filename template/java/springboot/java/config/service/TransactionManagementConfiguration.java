package com.ctgu.springbootbase.config.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**  
* @Title: TransactionManagementConfiguration.java  
*
* @Package com.ctgu.springbootbase.config.service  
*
* @Description: 对应spring-service里的transactionManager
* 继承TransactionManagementConfigurer是因为开启annotation-driver
*
*/
@Configuration
//使用注解@EnableTransactionManagement开启事务支持后
//在Service层方法上添加注解@Transactional便可使用
@EnableTransactionManagement
public class TransactionManagementConfiguration implements TransactionManagementConfigurer{

	@Autowired
	//注入DataSourceConfiguration里边的dataSource，通过createDataSource()获取
	private DataSource dataSource;

	//注入DataSourceConfiguration里的dataSource，通过createDATaSource()获取
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}
	
}
