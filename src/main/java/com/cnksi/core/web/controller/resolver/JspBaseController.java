package com.cnksi.core.web.controller.resolver;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cnksi.core.web.controller.BaseController;
import com.cnksi.core.web.utils.Pagination;

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
public abstract class JspBaseController<T, ID extends Serializable> extends BaseController<T, ID>
{
	public JspBaseController()
	{
		super();
		viewFolder = String.format("/jsp/%s", classSimpleName);
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) throws Exception
	{
		T t = entityClass.newInstance();
		model.addAttribute("record", t);
		model.addAttribute("action", "create");
		return String.format("%s/form", viewFolder);
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(HttpServletRequest request, T newRecord, RedirectAttributes redirectAttributes)
	{

		baseService.save(newRecord);

		logger.info(newRecord.toString());

		redirectAttributes.addFlashAttribute("message", "创建成功");

		return String.format("redirect:/%s/list", classSimpleName);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Pagination pagination)
	{
		Page<T> records = baseService.findAll(pagination.getPageable());

		model.addAttribute("records", records);

		logger.info(records.toString());

		return String.format("/%s/list", viewFolder);
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") ID id, Model model)
	{
		model.addAttribute("record", baseService.findOne(id));
		model.addAttribute("action", "update");
		return String.format("/%s/form", viewFolder);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute("preload") T newRecord, RedirectAttributes redirectAttributes)
	{
		baseService.save(newRecord);
		redirectAttributes.addFlashAttribute("message", "更新任务成功");
		return String.format("redirect:/%s/list", classSimpleName);
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") ID id, RedirectAttributes redirectAttributes)
	{
		baseService.delete(id);
		redirectAttributes.addFlashAttribute("message", "删除任务成功");
		return String.format("redirect:/%s/list", classSimpleName);
	}

	/**
	 * 使用@ModelAttribute, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出Task对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此本方法在该方法中执行.
	 */
	@ModelAttribute("preloadTask")
	public T getEntity(@RequestParam(value = "id", required = false) ID id)
	{
		if (id != null)
		{
			return baseService.findOne(id);
		}
		return null;
	}

}
