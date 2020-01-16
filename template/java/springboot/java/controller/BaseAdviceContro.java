/*
 * @Author: Fantasy
 * @Date: 2020-01-13 09:10:50
 * @LastEditors  : Fantasy
 * @LastEditTime : 2020-01-15 14:44:14
 * @Descripttion: 
 * @Email: 776474961@qq.com
 */
package $${basePackage}.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**  
* @Title: BaseController.java  
*
* @Package ${basePackage}.controller  
*
* @Description: 插入全局变量pwd
*
* @author Fantasy  
*
* @date 2019年1月14日  
*
* @version V1.0  
*/
@ControllerAdvice
public class BaseAdviceContro {

	@Value("${project.pwd}")
	private String pwd;
	
	@ModelAttribute(name="pwd")
	public String pwd() {
		return pwd;
	}
}
