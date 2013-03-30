package com.cnksi.core.web.dwz.controller;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.cnksi.core.exception.BaseException;
import com.cnksi.core.tools.mapper.JsonMapper;
import com.cnksi.core.tools.poi.PoiExportExcel;
import com.cnksi.core.tools.poi.annotation.DescriptionInfo;
import com.cnksi.core.web.WebHelper;
import com.cnksi.core.web.controller.BaseController;
import com.cnksi.core.web.utils.Pagination;
import com.cnksi.core.web.utils.Servlets;

public abstract class DwzAbstractBaseController<T, ID extends Serializable> extends BaseController<T, ID>
{
	protected Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public DwzAbstractBaseController()
	{
		super();

		try
		{
			viewFolder = String.format("/%s", classSimpleName);

			entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

		} catch (Exception ex)
		{
			throw new BaseException("DwzAbstractBaseController has Error " + ex.getMessage());
		}

	}

	public String json(Model model, Object datas)
	{
		String json = JsonMapper.nonEmptyMapper().toJson(datas);

		model.addAttribute("json", json);

		return "/json";
	}

	public String createForm(Model model)
	{
		try
		{
			T t = entityClass.newInstance();
			model.addAttribute("record", t);
			model.addAttribute("action", "create");
			return String.format("%s/form", viewFolder);
		} catch (Exception ex)
		{
			throw new BaseException("Error on DwzAbstractBaseController createForm");
		}
	}

	public String create(T newRecord, Model model)
	{

		baseService.save(newRecord);

		return json(model, WebHelper.returnSuccessMapInfo(LIST_REL));
	}

	public String list(Model model, Pagination pagination, ServletRequest request)
	{
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

		Page<T> records = baseService.findAll(pagination.getPageable(), searchParams);

		model.addAttribute("records", records);

		model.addAttribute("pagination", pagination);

		return String.format("%s/list", viewFolder);
	}

	public String updateForm(@PathVariable("id") ID id, Model model)
	{
		model.addAttribute("record", baseService.findOne(id));
		model.addAttribute("action", "update");
		return String.format("%s/form", viewFolder);
	}

	public String update(@ModelAttribute("preload") T newRecord, Model model)
	{
		baseService.save(newRecord);

		return json(model, WebHelper.returnSuccessMapInfo(LIST_REL));
	}

	public String delete(ID id, ID[] ids, Model model)
	{

		if (id != null)
		{
			baseService.delete(id);
		} else if (ids != null)
		{
			for (ID _id : ids)
			{
				baseService.delete(_id);
			}
		}

		return json(model, WebHelper.returnRefreshMapInfo(LIST_REL));
	}

	/**
	 * 导出Excel
	 * @param response
	 * @param datas 导出数据列表
	 * @param xlsTitle Excel表头文字
	 */
	public void export(HttpServletResponse response, List<T> datas, String xlsTitle)
	{
		List<DescriptionInfo> descriptionInfoList = PoiExportExcel.getInstance().parseField(entityClass, "a");

		PoiExportExcel.getInstance().downloadFile(response, entityClass.getSimpleName().toLowerCase() + "-list.xls", descriptionInfoList, datas, xlsTitle);
	}

	/**
	 * 使用@ModelAttribute, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此本方法在该方法中执行.
	 */
	@ModelAttribute("preload")
	public T getEntity(@RequestParam(value = "id", required = false) ID id)
	{
		if (id != null)
		{
			return baseService.findOne(id);
		}
		return null;
	}
}
