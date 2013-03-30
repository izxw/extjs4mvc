package com.cnksi.core.web.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.ShiroException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ExceptionHandlerResolver implements HandlerExceptionResolver
{

	private String errorPage = "error.jsp";

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	{

		Map<String, String> model = new HashMap<String, String>();

		//hibernate validator验证错误信息
		if (ex instanceof BindException)
		{

			BindingResult br = (BindingResult) ex;

			List<ObjectError> errors = br.getAllErrors();

			StringBuilder sbuilder = new StringBuilder();

			for (ObjectError error : errors)
			{
				sbuilder.append(error.getDefaultMessage()).append("<br/>");
			}

			model.put("errors", sbuilder.toString());

		} else if (ex instanceof ShiroException)
		{
			model.put("errors", "对不起，您没有执行此操作的权限");
		} else
		{
			model.put("errors", ex.getLocalizedMessage());
		}

		return new ModelAndView(errorPage.replace(".jsp", ""), model);
	}

	public String getErrorPage()
	{
		return errorPage;
	}

	public void setErrorPage(String errorPage)
	{
		this.errorPage = errorPage;
	}

}
