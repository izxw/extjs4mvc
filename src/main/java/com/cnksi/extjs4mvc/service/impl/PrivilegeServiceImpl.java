package com.cnksi.extjs4mvc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cnksi.core.repository.BaseRepository;
import com.cnksi.core.service.impl.BaseServiceImpl;
import com.cnksi.extjs4mvc.domain.Privilege;
import com.cnksi.extjs4mvc.service.PrivilegeService;

@Component
public class PrivilegeServiceImpl extends BaseServiceImpl<Privilege, Long> implements PrivilegeService
{
	@Override
	@Resource(name = "privilegeRepository")
	public void setBaseDao(BaseRepository<Privilege, Long> privilegeRepository)
	{
		super.baseDao = privilegeRepository;

	}

}
