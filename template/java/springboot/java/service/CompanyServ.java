package com.ctgu.springbootbase.service;

import java.util.List;

import com.ctgu.springbootbase.dto.CompanyDto;
import com.ctgu.springbootbase.entity.Company;
import com.ctgu.springbootbase.service.base.BaseService;

/**  
* @Title: CompanyServ.java  
*
* @Package com.ctgu.springbootbase.service  
*
* @Description: TODO
*
* @author Fantasy  
*
* @date 2019年3月3日  
*
* @version V1.0  
*/
public interface CompanyServ extends BaseService<Company> {
	/**
	 * 查找company dto
	 * @param company
	 * @return
	 */
	public List<CompanyDto> doFindDto(Company company);
	
}
