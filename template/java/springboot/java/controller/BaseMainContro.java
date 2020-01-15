package com.ctgu.springbootbase.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * @Title: BaseMainContro.java
 *
 * @Package com.ctgu.springbootbase.controller
 *
 * @Description: TODO
 *
 * @author Fantasy
 *
 * @date 2019年5月5日
 *
 * @version V1.0
 */
@Controller
public class BaseMainContro {

	@Resource
	@Qualifier("captchaProducer")
	private Producer captchaProducer;

	@Autowired
	private HttpSession session;

	@RequestMapping("/")
	public String Index1() {
		return "redirect:/index";
	}

	@RequestMapping("/index")
	public String Index2() {
		return "/index";
	}

	@RequestMapping("/getCaptchaCode")
	public void getCaptcharCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 设置相关相应头
		response.setDateHeader("Expires", 0);// 禁止浏览器进行缓存
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");

		// 生成验证码并保存到session中
		String capText = captchaProducer.createText();
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

		// 输出图片到网页中
		BufferedImage bi = captchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);

		// 输出流 ： 刷新 + 关闭
		out.flush();
		out.close();
	}
}
