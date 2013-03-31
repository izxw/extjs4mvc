package com.cnksi.extjs4mvc.service;

import java.util.List;

import com.cnksi.core.service.BaseService;
import com.cnksi.extjs4mvc.domain.Resource;

public interface ResourceService extends BaseService<Resource, Long>
{
	
	/**
	 * 根据用户ID 获取用户拥有的菜单树
	 * @param id 用户ID
	 * @return
	 */
	List<Resource> selectMenuByUser(Long id);

	/**
	 * 根据用户ID 获取用户拥有的操作Operation
	 * @param id 用户ID
	 * @return
	 */
	List<Resource> selectOperationByUser(Long id);
}
