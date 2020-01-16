/*
 * @Author: Fantasy
 * @Date: 2020-01-13 09:10:49
 * @LastEditors  : Fantasy
 * @LastEditTime : 2020-01-16 10:26:47
 * @Descripttion: 
 * @Email: 776474961@qq.com
 */
package $${basePackage}.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Aspect
@Component
public class CompanyPermissions {
	@Pointcut("execution(* $${basePackage}.controller.company.*.*(..))")
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
