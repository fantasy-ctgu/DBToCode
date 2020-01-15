package com.ctgu.springbootbase.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**  
* @Title: BaseController.java  
*
* @Package com.ctgu.springbootbase.controller  
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
