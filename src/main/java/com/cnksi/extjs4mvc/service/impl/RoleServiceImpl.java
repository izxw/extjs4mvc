package com.cnksi.extjs4mvc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cnksi.core.repository.BaseRepository;
import com.cnksi.core.service.impl.BaseServiceImpl;
import com.cnksi.extjs4mvc.domain.Role;
import com.cnksi.extjs4mvc.service.RoleService;

@Component
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService
{
	@Override
	@Resource(name = "roleRepository")
	public void setBaseDao(BaseRepository<Role, Long> roleRepository)
	{
		super.baseDao = roleRepository;
	}

}
