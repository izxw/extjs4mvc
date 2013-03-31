package com.cnksi.extjs4mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnksi.core.tools.mapper.JsonMapper;
import com.cnksi.core.tools.utils.Collections3;
import com.cnksi.core.web.controller.resolver.ExtBaseController;
import com.cnksi.extjs4mvc.domain.Privilege;
import com.cnksi.extjs4mvc.domain.Resource;
import com.cnksi.extjs4mvc.domain.User;
import com.cnksi.extjs4mvc.service.ResourceService;

@Controller
public class PrivilegeController extends ExtBaseController<Privilege, Long>
{
	@Autowired
	private ResourceService resourceService;

	/**
	 * 根据当前登录用户的菜单树
	 */
	@RequestMapping("/menu/tree")
	public void menuTree(HttpServletResponse response)
	{
		User user = getCurrentUser();

		List<Resource> resourceList = resourceService.selectMenuByUser(user.getId());
		String json = JsonMapper.nonEmptyMapper().toJson(getJsonTree(resourceList, Resource.class));
		System.out.println(json);
		toWrite(json, response);
	}

	/**
	 * 根据当前的用户获得操作权限
	 */
	@RequestMapping("/operation")
	public void operationTree(HttpServletResponse response)
	{
		User user = getCurrentUser();

		List<Resource> resourceList = resourceService.selectOperationByUser(user.getId());
		List<String> valueList = Collections3.extractToList(resourceList, "value");
		Map<String,Object> result =new HashMap<String,Object>();
		result.put("success", true);
		result.put("operations", valueList);
		String json = JsonMapper.nonEmptyMapper().toJson(result);
		System.out.println(json);
		toWrite(json, response);
	}
}
