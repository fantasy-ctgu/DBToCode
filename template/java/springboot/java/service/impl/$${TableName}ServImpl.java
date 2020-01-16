/*
 * @Author: Fantasy
 * @Date: 2020-01-13 09:10:50
 * @LastEditors  : Fantasy
 * @LastEditTime : 2020-01-15 14:52:57
 * @Descripttion: 
 * @Email: 776474961@qq.com
 */
package $${basePackage}.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctgu.springbootbase.dao.$${TableName}Dao;
import com.ctgu.springbootbase.entity.$${TableName};
import com.ctgu.springbootbase.service.$${TableName}Serv;

/**
 * @Title: $${TableName}ServImpl.java
 *
 * @Description: TODO
 *
 * @author Fantasy
 *
 * @date 2019年3月3日
 *
 * @version V1.0
 */
@Service("$${TableName}Serv")
public class $${TableName}ServImpl implements $${TableName}Serv {

	@Autowired
	private $${TableName}Dao $${TableName}Dao;

	@Override
	public boolean doAdd($${TableName} o) {
		if ($${TableName}Dao.doInsert(o) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean doDel($${TableName} o) {
		if ($${TableName}Dao.doDelete(o) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean doUpd($${TableName} o) {
		if ($${TableName}Dao.doUpdate(o) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<$${TableName}> doFind($${TableName} o) {
		return $${TableName}Dao.doSelect(o);
	}

	@Override
	public Integer doFindCount($${TableName} o) {
		return $${TableName}Dao.doSelectCount(o);
	}

}
