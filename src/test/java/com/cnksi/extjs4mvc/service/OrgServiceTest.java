package com.cnksi.extjs4mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.cnksi.core.domain.extjs.JSONTreeNode;
import com.cnksi.core.test.spring.SpringContextTestCase;
import com.cnksi.core.tools.mapper.JsonMapper;
import com.cnksi.core.tools.utils.ModelUtil;
import com.cnksi.core.tools.utils.Reflections;
import com.cnksi.extjs4mvc.domain.Operation;
import com.cnksi.extjs4mvc.domain.Org;
import com.cnksi.extjs4mvc.domain.Resource;

@ContextConfiguration(locations = { "/spring/root-context.xml" })
public class OrgServiceTest extends SpringContextTestCase
{
	@Autowired
	private OrgService orgService;

	@Autowired
	private OperationService operationService;

	@Autowired
	private ResourceService resourceService;

	public void testSave()
	{
		orgService.deleteAll();

		Org parent = new Org();
		parent.setName("乐山电业局");
		parent.setParent(null);
		parent.setOrgtype("电业局");
		parent.setLevel(0);
		orgService.save(parent);

		Org org = new Org();
		org.setName("五通供电局");
		org.setParent(parent);
		org.setOrgtype("供电局");
		org.setLevel(1);
		orgService.save(org);

		org.setId(null);
		org.setName("沙湾供电局");
		org.setParent(parent);
		org.setOrgtype("供电局");
		org.setLevel(1);
		orgService.save(org);

		JSONTreeNode template = ModelUtil.getJSONTreeNodeTemplate(Org.class);
		List<JSONTreeNode> nodes = new ArrayList<JSONTreeNode>();
		Org o = orgService.findOne(1L);

		JSONTreeNode node = new JSONTreeNode();
		node.setText(Reflections.getFieldValue(o, template.getText()).toString());
		nodes.add(node);
		if (o.getChildren() != null && o.getChildren().size() > 0)
		{
			for (Org ox : o.getChildren())
			{
				JSONTreeNode nodex = new JSONTreeNode();
				nodex.setText(Reflections.getFieldValue(ox, template.getText()).toString());
				nodex.setId(Reflections.getFieldValue(ox, template.getId()).toString());
				node.getChildren().add(nodex);
			}
		}

		System.out.println(JsonMapper.nonEmptyMapper().toJson(node));
	}

	public void testSaveOperation()
	{
		Operation operation = new Operation();
		operation.setName("enable");
		operationService.save(operation);

		operation.setId(null);
		operation.setName("disable");
		operationService.save(operation);

		operation.setId(null);
		operation.setName("visible");
		operationService.save(operation);

		operation.setId(null);
		operation.setName("invisible");
		operationService.save(operation);

	}
	
	//创建菜单
	public void testSaveMenuResource()
	{
		Resource root = new Resource();
		root.setType("menu");
		root.setName("系统配置");
		root.setValue("");
		resourceService.save(root);
		
		Resource res = new Resource();
		res.setType("menu");
		res.setParent(root);
		res.setName("用户管理");
		res.setValue("{xtype:userlist}");
		resourceService.save(res);
		
		Resource resRole = new Resource();
		resRole.setType("menu");
		resRole.setParent(root);
		resRole.setName("角色管理");
		resRole.setValue("{xtype:rolelist}");
		resourceService.save(resRole);
		
		
		Resource resRes = new Resource();
		resRes.setType("menu");
		resRes.setParent(root);
		resRes.setName("资源管理");
		resRes.setValue("{xtype:'reslist'}");
		resourceService.save(resRes);
	}

	//创建Button操作资源
	public void testSaveOperationResource(){
		Resource oper1 = new Resource();
		oper1.setType("operation");
		oper1.setName("新增用户");
		oper1.setValue("{featureName:'userlist-create'}");
		resourceService.save(oper1);
		
		Resource oper2 = new Resource();
		oper2.setType("operation");
		oper2.setName("编辑用户");
		oper2.setValue("{featureName:'userlist-edit'}");
		resourceService.save(oper2);
		
		Resource oper3 = new Resource();
		oper3.setType("operation");
		oper3.setName("删除用户");
		oper3.setValue("{featureName:'userlist-delete'}");
		resourceService.save(oper3);
	}
}
