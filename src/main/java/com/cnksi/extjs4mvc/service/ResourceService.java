package com.cnksi.extjs4mvc.service;

import java.util.List;

import com.cnksi.core.service.BaseService;
import com.cnksi.extjs4mvc.domain.Resource;

public interface ResourceService extends BaseService<Resource, Long>
{

	List<Resource> selectMenuByUser(Long id);
	
}
