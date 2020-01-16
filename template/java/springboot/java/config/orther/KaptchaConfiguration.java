/*
 * @Author: Fantasy
 * @Date: 2020-01-13 09:10:50
 * @LastEditors  : Fantasy
 * @LastEditTime : 2020-01-16 10:28:09
 * @Descripttion: 
 * @Email: 776474961@qq.com
 */
package $${basePackage}.config.orther;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;

/**
 * @Title: KaptchaConfiguration.java
 *
 * @Package $${basePackage}.config.dao
 *
 * @Description: 验证码
 *
 * @author Fantasy
 *
 * @date 2019年1月15日
 *
 * @version V1.0
 */
@Configuration
public class KaptchaConfiguration {

	@Bean(name="captchaProducer")
	public Producer KaptchaProducer() {
		Properties kaptchaProperties = new Properties();
		kaptchaProperties.put("kaptcha.border", "yes");
		kaptchaProperties.put("kaptcha.border.color", "105,179,90");
		kaptchaProperties.put("kaptcha.textproducer.char.length", "4");
		kaptchaProperties.put("kaptcha.image.height", "45");
		kaptchaProperties.put("kaptcha.image.width", "125");
		kaptchaProperties.put("kaptcha.textproducer.font.color", "blue");
		kaptchaProperties.put("kaptcha.textproducer.font.size", "35");
		kaptchaProperties.put("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
		Config config = new Config(kaptchaProperties);
		return config.getProducerImpl();
	}
}
