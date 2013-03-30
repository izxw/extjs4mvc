package com.cnksi.extjs4mvc.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cnksi.core.repository.BaseRepository;
import com.cnksi.core.service.impl.BaseServiceImpl;
import com.cnksi.extjs4mvc.domain.Resource;
import com.cnksi.extjs4mvc.repository.ResourceRepository;
import com.cnksi.extjs4mvc.service.ResourceService;

@Component
public class ResourceServiceImpl extends BaseServiceImpl<Resource, Long> implements ResourceService
{
	private ResourceRepository resourceRepository;

	@Override
	@javax.annotation.Resource(name = "resourceRepository")
	public void setBaseDao(BaseRepository<Resource, Long> resourceRepository)
	{
		super.baseDao = resourceRepository;
		this.resourceRepository = (ResourceRepository) resourceRepository;
	}

	@Override
	public List<Resource> selectMenuByUser(Long id)
	{
		return resourceRepository.selectMenuByUser(id);
	}

}
