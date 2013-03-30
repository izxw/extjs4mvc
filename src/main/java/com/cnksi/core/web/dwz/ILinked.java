
package com.cnksi.core.web.dwz;

/**
 * Dwz树型导航菜单
 * 
 * @author dell
 *
 */
public interface ILinked
{

	/**
	 * 获得Link ID
	 * 
	 * 一般是对象的数据库主键ID
	 * 
	 * @return
	 */
	Object getLinkid();

	/**
	 * 获得上级Link
	 * 
	 * @return
	 */
	ILinked getPLink();

	/**
	 * 获得Link Name
	 * 
	 * @return
	 */
	String getLinkname();

	/**
	 * 获得URI地址
	 * 
	 * @return
	 */
	String getLinkURI();

	/**
	 * DWZ 导航target,默认为navTab
	 * 
	 * @return
	 */
	String getTarget();

	/**
	 * DWZ 导航REL,做为navTab的ID
	 * 
	 * @return
	 */
	String getRel();
}
