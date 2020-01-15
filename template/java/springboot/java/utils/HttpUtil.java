package com.ctgu.springbootbase.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title: HttpUtil.java
 *
 * @Package com.ctgu.springbootbase.utils
 *
 * @Description: TODO
 *
 * @author Fantasy
 *
 * @date 2019年3月3日
 *
 * @version V1.0
 */
public class HttpUtil {

	/**
	 * 获取用户真实IP地址（忽略代理软件IP）
	 * 
	 * @param request
	 * @return 真实IP
	 */
	public static String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		System.out.println("x-forwarded-for ip: " + ip);
		if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			if (ip.indexOf(",") != -1) {
				ip = ip.split(",")[0];
			}
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			System.out.println("Proxy-Client-IP ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			System.out.println("WL-Proxy-Client-IP ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			System.out.println("HTTP_CLIENT_IP ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			System.out.println("HTTP_X_FORWARDED_FOR ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
			System.out.println("X-Real-IP ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			System.out.println("getRemoteAddr ip: " + ip);
		}
		System.out.println("客户端ip: " + ip);
		return ip;
	}
	
	/**
	 * 向response里写入字符串
	 * @param response response对象
	 * @param content 字符串内容
	 * @return 成功返回true
	 */
	public static boolean sendStrToResp(HttpServletResponse response, String content) {
		OutputStream oStream;
		try {
			oStream = response.getOutputStream();
			response.setHeader("content-type", "text/html;charset=UTF-8");
			oStream.write(content.getBytes("UTF-8"));
			oStream.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 向response写入文件
	 * @param response response对象
	 * @param path 文件绝对路径
	 * @param filename 文件名
	 * @return 成功返回true
	 */
	public static boolean sendFileToResp(HttpServletResponse response, String path, String filename) {
		InputStream is;
		try {
			is = new BufferedInputStream(new FileInputStream(new File(path)));
			response.addHeader("Content-Disposition", "attachment;filename=" + filename);
			response.setContentType("multipart/form-data");
			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
			int len = 0;
			while ((len = is.read()) != -1) {
				out.write(len);
				out.flush();
			}
			out.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
