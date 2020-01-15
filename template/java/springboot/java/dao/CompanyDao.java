package com.ctgu.springbootbase.dao;

import java.util.List;

import com.ctgu.springbootbase.dao.base.BaseDao;
import com.ctgu.springbootbase.dto.CompanyDto;
import com.ctgu.springbootbase.entity.Company;

/**  
* @Title: CompanyDao.java  
*
* @Package com.ctgu.springbootbase.dao  
*
* @Description: TODO
*
* @author Fantasy  
*
* @date 2019年3月3日  
*
* @version V1.0  
*/
public interface CompanyDao extends BaseDao<Company>{
	List<CompanyDto> selectDtoBySome(Company company);
}
