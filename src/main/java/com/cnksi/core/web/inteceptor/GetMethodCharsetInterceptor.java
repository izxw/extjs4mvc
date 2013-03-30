
package com.cnksi.core.web.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class GetMethodCharsetInterceptor extends HandlerInterceptorAdapter
{

	private String targetEncode = "UTF-8";

	/**
	 * SpringMVC过滤不到Get方法的字符，因此加此Interceptor。设定字符集
	 * 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{

		if (request.getMethod().toLowerCase().equals("get"))
		{
			request.setCharacterEncoding(targetEncode);
		}

		return super.preHandle(request, response, handler);
	}

	public String getTargetEncode()
	{

		return targetEncode;
	}

	public void setTargetEncode(String targetEncode)
	{

		if (targetEncode != null && targetEncode.trim().length() > 0)
		{
			this.targetEncode = targetEncode;
		}
	}

}
