/*
 * @Author: Fantasy
 * @Date: 2020-01-13 09:10:50
 * @LastEditors: Fantasy
 * @LastEditTime: 2020-01-15 14:54:52
 * @Descripttion: 
 * @Email: 776474961@qq.com
 */
package $${basePackage};

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootBaseApplication.class);
	}

}

