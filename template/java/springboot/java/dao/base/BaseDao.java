package com.ctgu.springbootbase.dao.base;
/**  
* @Title: BaseDao.java  
*
* @Package com.ctgu.springbootbase.dao.base  
*
* @Description: TODO
*
* @author Fantasy  
*
* @date 2019年3月3日  
*
* @version V1.0  
*/

import java.util.List;

public interface BaseDao<O> {
	public int doInsert(O o);
	public int doDelete(O o);
	public int doUpdate(O o);
	public List<O> doSelect(O o);
	public int doSelectCount(O o);
}
