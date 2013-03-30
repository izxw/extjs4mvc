package com.cnksi.extjs4mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.cnksi.core.repository.BaseRepository;
import com.cnksi.extjs4mvc.domain.Resource;

@Component
public interface ResourceRepository extends BaseRepository<Resource, Long>
{
	@Query(value="SELECT * FROM resource WHERE type='menu' and id IN (SELECT resid FROM privilege WHERE id IN( SELECT privid FROM role_privilege WHERE rid IN (SELECT rid FROM role_user WHERE uid = ?)))",nativeQuery=true)
	List<Resource> selectMenuByUser(Long id);
}
