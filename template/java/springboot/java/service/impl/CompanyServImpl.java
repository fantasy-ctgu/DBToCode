package com.ctgu.springbootbase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctgu.springbootbase.dao.CompanyDao;
import com.ctgu.springbootbase.dto.CompanyDto;
import com.ctgu.springbootbase.entity.Company;
import com.ctgu.springbootbase.service.CompanyServ;

/**
 * @Title: CompanyServImpl.java
 *
 * @Package com.ctgu.springbootbase.service.impl
 *
 * @Description: TODO
 *
 * @author Fantasy
 *
 * @date 2019年3月3日
 *
 * @version V1.0
 */
@Service("companyServ")
public class CompanyServImpl implements CompanyServ {

	@Autowired
	private CompanyDao companyDao;

	@Override
	public boolean doAdd(Company o) {
		if (companyDao.doInsert(o) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean doDel(Company o) {
		if (companyDao.doDelete(o) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean doUpd(Company o) {
		if (companyDao.doUpdate(o) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Company> doFind(Company o) {
		return companyDao.doSelect(o);
	}

	@Override
	public List<CompanyDto> doFindDto(Company company) {
		return companyDao.selectDtoBySome(company);
	}

	@Override
	public Integer doFindCount(Company o) {
		return companyDao.doSelectCount(o);
	}

}
