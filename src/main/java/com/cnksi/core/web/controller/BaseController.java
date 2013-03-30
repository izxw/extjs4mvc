package com.cnksi.core.web.controller;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnksi.core.exception.BaseException;
import com.cnksi.core.service.BaseService;

public abstract class BaseController<T, ID extends Serializable>
{
	protected Class<T> entityClass;

	protected String viewFolder;

	protected String classSimpleName;

	protected BaseService<T, ID> baseService;

	protected Logger logger;

	protected String LIST_REL = "list";

	protected String FORM_REL = "form";

	//public abstract void setBaseService(BaseService<T, ID> baseService);

	@SuppressWarnings("unchecked")
	public BaseController()
	{

		try
		{
			entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

			logger = LoggerFactory.getLogger(BaseController.class);

			classSimpleName = entityClass.getSimpleName().toLowerCase(Locale.US);

			LIST_REL = String.format("%s-list", classSimpleName);

			FORM_REL = String.format("%s-form", classSimpleName);

		} catch (Exception ex)
		{
			throw new BaseException("BaseController Construct Error");
		}
	}



}
