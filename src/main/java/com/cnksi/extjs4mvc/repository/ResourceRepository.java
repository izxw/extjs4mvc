package com.cnksi.extjs4mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.cnksi.core.repository.BaseRepository;
import com.cnksi.extjs4mvc.domain.Resource;

@Component
public interface ResourceRepository extends BaseRepository<Resource, Long>
{
	/**
	 * 根据用户ID 获取用户拥有的菜单树
	 * @param id 用户ID
	 * @return
	 */
	@Query(value = "SELECT * FROM resource WHERE type='menu' and id IN (SELECT resid FROM privilege WHERE id IN( SELECT privid FROM role_privilege WHERE rid IN (SELECT rid FROM role_user WHERE uid = ?)))", nativeQuery = true)
	List<Resource> selectMenuByUser(Long id);

	/**
	 * 根据用户ID 获取用户拥有的操作Operation
	 * @param id 用户ID
	 * @return
	 */
	@Query(value = "SELECT * FROM resource WHERE type='operation' and id IN (SELECT resid FROM privilege WHERE id IN( SELECT privid FROM role_privilege WHERE rid IN (SELECT rid FROM role_user WHERE uid = ?)))", nativeQuery = true)
	List<Resource> selectOperationByUser(Long id);

}
