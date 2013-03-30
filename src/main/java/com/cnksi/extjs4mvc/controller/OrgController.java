package com.cnksi.extjs4mvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnksi.core.tools.mapper.JsonMapper;
import com.cnksi.core.web.controller.resolver.ExtBaseController;
import com.cnksi.extjs4mvc.domain.Org;
import com.cnksi.extjs4mvc.service.OrgService;

@RequestMapping("/org")
@Controller
public class OrgController extends ExtBaseController<Org, Long>
{
	@Autowired
	private OrgService orgService;

	@RequestMapping("/tree")
	public void getTree(HttpServletResponse response) throws IOException
	{
		List<Org> orgList = orgService.selectTopOrg();
		toWrite(JsonMapper.nonEmptyMapper().toJson(getJsonTree(orgList)), response);
	}

}
