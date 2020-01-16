/*
 * @Author: Fantasy
 * @Date: 2020-01-13 09:10:50
 * @LastEditors: Fantasy
 * @LastEditTime: 2020-01-15 14:48:22
 * @Descripttion: 
 * @Email: 776474961@qq.com
 */
package $${basePackage}.service.base;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**  
* @Title: BaseService.java  
*
* @Package $${basePackage}.service.base  
*
* @Description: 定义基本service接口
*
* @author Fantasy  
*
* @date 2019年3月3日  
*
* @version V1.0  
*/
@Transactional
public interface BaseService<O> {
	public boolean doAdd(O o);
	public boolean doDel(O o);
	public boolean doUpd(O o);
	public List<O> doFind(O o);
	public Integer doFindCount(O o);
}
