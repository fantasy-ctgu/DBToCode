/*
 * @Author: Fantasy
 * @Date: 2020-01-13 09:10:50
 * @LastEditors: Fantasy
 * @LastEditTime: 2020-01-15 14:33:25
 * @Descripttion: 
 * @Email: 776474961@qq.com
 */
package $${basePackage}.config.dao;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**  
* @Title: SessionFactoryConfiguration.java  
*
* @Package com.ctgu.springbootbase.config.dao  
*
* @Description: 配置sessionfactory，对应spring-dao
*/
@Configuration
public class SessionFactoryConfiguration {
	//mybatis-config.xml配置文件的路径
	private static String mybatisConfigFile;
	@Value("${mybatis_config_file}")
	public void setMybatisConfigFile(String mybatisConfigFile) {
		SessionFactoryConfiguration.mybatisConfigFile = mybatisConfigFile;
	}
	
	//mybatis mapper文件路径
	private static String mapperPath;
	@Value("${mapper_path}")
	public void setMapperPath(String mapperPath) {
		SessionFactoryConfiguration.mapperPath = mapperPath;
	}
	
	//实体类所在的package
	@Value("${type_alias_package}")
	private String typeAliasPackage;

	@Autowired
	private DataSource dataSource;
	



	@Bean(name="sqlSessionFactory")
	public SqlSessionFactoryBean createSqlSessionFactory() throws IOException {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		//设置mybatis configuration扫描路径
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigFile));
		//添加mapper扫描路径
		PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
		String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath;
		sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));
		//设置datasource
		sqlSessionFactoryBean.setDataSource(dataSource);
		//设置typeAlias包扫描路径
		sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasPackage);
		return sqlSessionFactoryBean;
	}
}
