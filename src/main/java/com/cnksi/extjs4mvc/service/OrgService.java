package com.cnksi.extjs4mvc.service;

import java.util.List;

import com.cnksi.core.service.BaseService;
import com.cnksi.extjs4mvc.domain.Org;

public interface OrgService extends BaseService<Org, Long>
{
	public List<Org> selectTopOrg();
}
