package com.cnksi.extjs4mvc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cnksi.core.repository.BaseRepository;
import com.cnksi.core.service.impl.BaseServiceImpl;
import com.cnksi.extjs4mvc.domain.Org;
import com.cnksi.extjs4mvc.repository.OrgRepository;
import com.cnksi.extjs4mvc.service.OrgService;

@Component
public class OrgServiceImpl extends BaseServiceImpl<Org, Long> implements OrgService
{
	private OrgRepository orgRepository;

	@Override
	@Resource(name = "orgRepository")
	public void setBaseDao(BaseRepository<Org, Long> orgRepository)
	{
		super.baseDao = orgRepository;

		this.orgRepository = (OrgRepository) orgRepository;
	}

	@Override
	public List<Org> selectTopOrg()
	{
		return orgRepository.selectTopOrg();
	}

}
