package $${basePackage}.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import $${basePackage}.mapper.$${TableName}Mapper;
import $${basePackage}.entity.$${TableName};
import $${basePackage}.service.$${TableName}Serv;

/**
 * @ClassName $${TableName}
 * @Description: TODO
 * @Author Fantasy
 * @Date 2020/2/10
 * @Version V1.0
 **/
 
@Service("$${TableName}Serv")
public class $${TableName}ServImpl implements $${TableName}Serv {

	@Autowired
	private $${TableName}Mapper $${tableName}Mapper;

	@Override
	public boolean doAdd($${TableName} o) {
		if ($${tableName}Mapper.insert(o) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean doDel($${TableName} o) {
		if ($${tableName}Mapper.deleteById(o.getId()) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean doUpd($${TableName} o) {
		if ($${tableName}Mapper.updateById(o) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<$${TableName}> doSel($${TableName} o) {
		return $${tableName}Mapper.doSelectBySome(o);
	}

}
