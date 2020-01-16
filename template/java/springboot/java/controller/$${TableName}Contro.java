/*
 * @Author: Fantasy
 * @Date: 2020-01-13 09:10:50
 * @LastEditors: Fantasy
 * @LastEditTime: 2020-01-15 14:39:57
 * @Descripttion: 
 * @Email: 776474961@qq.com
 */
package $${basePackage}.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Title: $${TableName}Contro.java
 *
 * @Package $${basePackage}.controller.company
 *
 * @Description: 其它控制器
 *
 * @author Fantasy
 *
 * @date 2019年4月2日
 *
 * @version V1.0
 */
@Controller
public class $${basePackage}Contro {

	@Autowired
	private HttpSession session;

	@Autowired
	@Qualifier("$${tableName}Serv")
	private $${TableName}Serv $${tableName}Serv;

	
}
