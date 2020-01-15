package com.ctgu.springbootbase.dto;

import java.util.List;

import com.ctgu.springbootbase.entity.Company;
import com.ctgu.springbootbase.entity.Positions;

/**
 * @Title: CompanyDto.java
 *
 * @Package com.ctgu.springbootbase.dto
 *
 * @Description: 企业dto：一个企业对应多个岗位信息
 *
 * @author Fantasy
 *
 * @date 2019年3月10日
 *
 * @version V1.0
 */
public class CompanyDto extends Company {
	
	private List<Positions> positionss;

	public List<Positions> getPositions() {
		return positionss;
	}

	public void setPositions(List<Positions> positions) {
		this.positionss = positions;
	}
}
