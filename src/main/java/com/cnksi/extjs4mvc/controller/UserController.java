package com.cnksi.extjs4mvc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnksi.core.tools.mapper.JsonMapper;
import com.cnksi.core.web.controller.resolver.ExtBaseController;
import com.cnksi.core.web.extjs.ExtPage;
import com.cnksi.extjs4mvc.domain.Org;
import com.cnksi.extjs4mvc.domain.User;
import com.cnksi.extjs4mvc.service.UserService;

@Controller
public class UserController extends ExtBaseController<Org, Long>
{
	@Autowired
	private UserService userService;

	@RequestMapping("/user/list")
	public void getTree(HttpServletResponse response, ExtPage page) throws IOException
	{
		Page<User> entities = userService.findAll(page.getPageable());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("users", entities.getContent());
		result.put("success", true);
		result.put("total", entities.getTotalElements());
		toWrite(JsonMapper.nonEmptyMapper().toJson(result), response);
	}

}
