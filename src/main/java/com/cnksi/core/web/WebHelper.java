package com.cnksi.core.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;

import com.cnksi.core.domain.BaseUser;

public class WebHelper
{

	/**
	 * dwz 返回客户端 成功JSON 信息,一般用于关闭Dialog，并刷新LIST
	 * 
	 * @param refreshTabId 客户端刷新ID
	 * @param statusCode 状态码200(默认),300
	 * @param msg 提示信息(操作成功)
	 * @param callbackType 客户端回调方法 forward or closeCurrent
	 * @param 如果callbackType = forward,则客户端定向到forwardUrl
	 * @return
	 */
	public static Map<String, String> returnSuccessMapInfo(String refreshTabId)
	{

		Map<String, String> resultMap = new HashMap<String, String>();

		resultMap.put("statusCode", "200");

		resultMap.put("message", "操作成功");

		resultMap.put("navTabId", refreshTabId);

		resultMap.put("forwardUrl", "");

		resultMap.put("callbackType", "closeCurrent");

		return resultMap;
	}

	/**
	 * dwz 返回客户端 Refresh JSON 信息,一般用于删除数据后刷新LIST
	 * 
	 * @param refreshTabId 客户端刷新ID
	 * @param statusCode 状态码200(默认),300
	 * @param msg 提示信息(操作成功)
	 * @param callbackType 客户端回调方法 forward or closeCurrent
	 * @param 如果callbackType = forward,则客户端定向到forwardUrl
	 * @return
	 */
	public static Map<String, String> returnRefreshMapInfo(String refreshTabId)
	{

		Map<String, String> resultMap = new HashMap<String, String>();

		resultMap.put("statusCode", "200");

		resultMap.put("message", "操作成功");

		resultMap.put("navTabId", refreshTabId);

		resultMap.put("forwardUrl", "");

		resultMap.put("callbackType", "");

		return resultMap;
	}

	/**
	 * dwz 返回客户端 Refresh JSON 信息,一般用于删除数据后刷新LIST
	 * 
	 * @param refreshTabId 客户端刷新ID
	 * @param statusCode 状态码200(默认),300
	 * @param msg 提示信息(操作成功)
	 * @param callbackType 客户端回调方法 forward or closeCurrent
	 * @param 如果callbackType = forward,则客户端定向到forwardUrl
	 * @return
	 */
	public static Map<String, String> returnForwardMapInfo(String forwardUrl)
	{

		Map<String, String> resultMap = new HashMap<String, String>();

		resultMap.put("statusCode", "200");

		resultMap.put("message", "操作成功");

		resultMap.put("navTabId", "");

		resultMap.put("forwardUrl", forwardUrl);

		resultMap.put("callbackType", "forward");

		return resultMap;
	}

	/**
	 * dwz 返回客户端 Refresh JSON 信息,一般用于删除数据后刷新LIST
	 * 
	 * @param refreshTabId 客户端刷新ID
	 * @param statusCode 状态码200(默认),300
	 * @param msg 提示信息(操作成功)
	 * @param callbackType 客户端回调方法 forward or closeCurrent
	 * @param 如果callbackType = forward,则客户端定向到forwardUrl
	 * @return
	 */
	public static Map<String, String> returnErrorMapInfo(String msg)
	{

		Map<String, String> resultMap = new HashMap<String, String>();

		resultMap.put("statusCode", "300");

		resultMap.put("message", msg);

		resultMap.put("navTabId", "");

		return resultMap;
	}

	/**
	 * 取出Shiro中的当前用户LoginName.
	 */
	public static BaseUser getCurrentUser()
	{

		BaseUser user = (BaseUser) SecurityUtils.getSubject().getPrincipal();

		return user;
	}

}
