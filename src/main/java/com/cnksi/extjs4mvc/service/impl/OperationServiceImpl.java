package com.cnksi.extjs4mvc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cnksi.core.repository.BaseRepository;
import com.cnksi.core.service.impl.BaseServiceImpl;
import com.cnksi.extjs4mvc.domain.Operation;
import com.cnksi.extjs4mvc.service.OperationService;

@Component
public class OperationServiceImpl extends BaseServiceImpl<Operation, Long> implements OperationService
{
	@Override
	@Resource(name = "operationRepository")
	public void setBaseDao(BaseRepository<Operation, Long> operationRepository)
	{
		super.baseDao = operationRepository;
	}

}
