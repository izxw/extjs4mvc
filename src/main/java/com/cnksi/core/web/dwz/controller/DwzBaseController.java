package com.cnksi.core.web.dwz.controller;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cnksi.core.web.utils.Pagination;
import com.cnksi.core.web.utils.Servlets;

/**
 * 管理T的Controller, 使用Restful风格的Urls:
 * 
 * List page     : GET /{t}/
 * Create page   : GET /{t}/create
 * Create action : POST /{t}/create
 * Update page   : GET /{t}/update/{id}
 * Update action : POST /{t}/update
 * Delete action : GET /{t}/delete/{id}
 * 
 * @author Joe
 */
public abstract class DwzBaseController<T, ID extends Serializable> extends DwzAbstractBaseController<T, ID>
{

	public DwzBaseController()
	{
		super();
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model)
	{
		return super.createForm(model);
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid T newRecord, Model model)
	{
		return super.create(newRecord, model);
	}

	@RequestMapping(value = "/list")
	public String list(Model model, Pagination pagination, ServletRequest request)
	{
		return super.list(model, pagination, request);
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") ID id, Model model)
	{
		return super.updateForm(id, model);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute("preload") T newRecord, Model model)
	{
		return super.update(newRecord, model);
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") ID id, @RequestParam(required = false, value = "ids") ID[] ids, Model model)
	{
		return super.delete(id, ids, model);
	}

	@RequestMapping(value = "export")
	public void export(HttpServletResponse response, ServletRequest request, Pagination pagination, Model model)
	{
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_", true);

		Page<T> records = baseService.findAll(pagination.getPageable(), searchParams);

		super.export(response, records.getContent(), entityClass.getSimpleName() + "列表");
	}

}
