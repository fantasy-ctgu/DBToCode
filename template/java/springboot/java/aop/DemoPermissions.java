package com.ctgu.springbootbase.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ctgu.springbootbase.entity.Company;

/**  
* @Title: TestAop.java  
*
* @Package com.ctgu.springbootbase.aop  
*
* @Description: TODO
*
* @author Fantasy  
*
* @date 2019年4月14日  
*
* @version V1.0  
*/
@Aspect
@Component
public class CompanyPermissions {
	@Pointcut("execution(* com.ctgu.springbootbase.controller.company.*.*(..))")
	public void permissionPointcut() {
	}
	
	@Around("permissionPointcut()")
	public Object permissionPointcutBefore(ProceedingJoinPoint pjp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Company company =  (Company) request.getSession().getAttribute("company");		
		if( company == null ){
			HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
			String path = request.getServletContext().getContextPath() + "/login";
			response.sendRedirect(path);
			return null;
		}
		else{
			return pjp.proceed();
		}
	}
}
