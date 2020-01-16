/*
 * @Author: Fantasy
 * @Date: 2020-01-13 09:10:50
 * @LastEditors  : Fantasy
 * @LastEditTime : 2020-01-15 14:43:49
 * @Descripttion: 
 * @Email: 776474961@qq.com
 */
package $${basePackage}.dao.base;
/**  
* @Title: BaseDao.java  
*
* @Package ${basePackage}.dao.base  
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
