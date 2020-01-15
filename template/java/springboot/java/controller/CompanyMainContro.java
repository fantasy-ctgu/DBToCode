package com.ctgu.springbootbase.controller;

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

import com.ctgu.springbootbase.dto.CompanyDto;
import com.ctgu.springbootbase.entity.Company;
import com.ctgu.springbootbase.service.CompanyServ;
import com.ctgu.springbootbase.utils.HttpUtil;
import com.ctgu.springbootbase.utils.UploadUtil;

/**
 * @Title: CompanyMainContro.java
 *
 * @Package com.ctgu.springbootbase.controller.company
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
public class CompanyMainContro {

	@Autowired
	private HttpSession session;

	@Autowired
	@Qualifier("companyServ")
	private CompanyServ companyServ;


	@RequestMapping("/register")
	public String register() {
		return "/company/register";
	}
	
	@RequestMapping("/registerInfo")
	public String registerInfo(Company company,HttpServletResponse response) {
		if(companyServ.doAdd(company)) {
			return "redirect:/login";
		}
		HttpUtil.sendStrToResp(response, "注册失败，用户已存在");
		return "";
	}
	
	/**
	 * 登陆界面
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {
		return "/login";
	}
	
	/**
	 * 登陆信息接口
	 * @param company
	 * @param request
	 * @return
	 */
	@RequestMapping("/loginInfo")
	public String loginInfo(Company company,HttpServletRequest request) {
		List<Company> companies = companyServ.doFind(company);
		if(companies.isEmpty()) {	// 没有该用户
			request.setAttribute("companyLoginError", "用户名或密码错误");
			return "/login";
		}else {	//	登陆成功
			Company company2 = companies.get(0);
			// 更新登陆IP、时间
			company2.setLastIp(HttpUtil.getClientIp(request));
			company2.setLastLogin(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((System.currentTimeMillis())));
			companyServ.doUpd(company2);
			
			session.setAttribute("company", companies.get(0));
			
			if (session.getAttribute("applicant") != null) {
				session.removeAttribute("applicant");
			}
			
			return "redirect:/company/main";
		}
	}
	/**
	 * 修改信息界面
	 * @return
	 */
	@RequestMapping("/updCompany")
	public String companyInfo() {
		return "/company/updCompany";
	}

	/**
	 * 修改信息接口
	 * @param company
	 * @param file
	 * @param response
	 * @return
	 */
	@RequestMapping("/updCompanyInfo")
	public String updCompanyInfo(Company company, @RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletResponse response) {
		//	判断是否修改头像
		if (null != file && !file.isEmpty()) {
			company.setAvatar(UploadUtil.generateNormalImg(file));
		}
		Company company1 = (Company) session.getAttribute("company");
		company.setId(company1.getId());
		company.setPerfect(1);
		
		if (companyServ.doUpd(company)) {	//	修改成功
			return "/company/main";
		}
		HttpUtil.sendStrToResp(response, "修改失败，请联系管理员");
		return "";
	}

	@ResponseBody
	@RequestMapping("/getCompanyDto")
	public List<CompanyDto> getCompanyDto(){
		return companyServ.doFindDto(new Company());
	}
}
