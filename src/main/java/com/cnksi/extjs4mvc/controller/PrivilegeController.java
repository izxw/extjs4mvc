package com.cnksi.extjs4mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnksi.core.tools.mapper.JsonMapper;
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
	 * 根据登录的用户获得资源菜单树
	 */
	@RequestMapping("/menu/tree")
	public void resTree(HttpServletResponse response)
	{
		User user = getCurrentUser();

		List<Resource> resourceList = resourceService.selectMenuByUser(user.getId());
		String json = JsonMapper.nonEmptyMapper().toJson(getJsonTree(resourceList, Resource.class));
		System.out.println(json);
		toWrite(json, response);
	}
}
