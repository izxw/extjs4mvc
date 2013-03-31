package com.cnksi.core.web.controller.resolver;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnksi.core.domain.TreeBaseEntity;
import com.cnksi.core.domain.extjs.JSONTreeNode;
import com.cnksi.core.tools.mapper.JsonMapper;
import com.cnksi.core.tools.utils.ModelUtil;
import com.cnksi.core.tools.utils.Tree;
import com.cnksi.core.web.controller.BaseController;
import com.cnksi.core.web.extjs.ParamUtils;
import com.cnksi.extjs4mvc.domain.User;
import com.fasterxml.jackson.databind.JavaType;

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
@RequestMapping("/ext")
public abstract class ExtBaseController<T, ID extends Serializable> extends BaseController<T, ID>
{
	public ExtBaseController()
	{
		super();
	}

	protected JSONTreeNode getJsonTree(List<T> datas)
	{
		return getJsonTree(datas, entityClass);
	}

	/**
	 * 输出Extjs Tree信息
	 * @param datas
	 * @param classz
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	protected JSONTreeNode getJsonTree(List<?> datas, Class classz)
	{
		JSONTreeNode template = ModelUtil.getJSONTreeNodeTemplate(classz);

		Tree tree = new Tree();

		for (Object t : datas)
		{
			if (t instanceof TreeBaseEntity)
			{
				TreeBaseEntity entity = (TreeBaseEntity) t;

				JSONTreeNode nodex = new JSONTreeNode();
				nodex.setJSONTreeNodeValue(template, entity);
				tree.addNode(nodex);
			}
		}

		tree.display(tree.getRoot(), "菜单树");
		return tree.getRoot();
	}

	/**
	 * 向客户端直接输出内容
	 * @param contents
	 * @param response
	 */
	protected void toWrite(String contents, HttpServletResponse response)
	{

		response.setContentType("text/html;charset=UTF-8;");
		Writer writer = null;
		try
		{
			response.setCharacterEncoding("UTF-8");
			writer = response.getWriter();
			writer.write(contents);
		} catch (IOException e)
		{

			e.printStackTrace();
		} finally
		{
			try
			{
				writer.flush();
				writer.close();
				response.flushBuffer();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Json字符串序列化ParamUtils集合
	 * @param jsonFilter=[{"property":"EQ_username","value":"guest"}]
	 * @return
	 */
	public static List<ParamUtils> getParamUtils(String jsonFilter)
	{
		JavaType type = JsonMapper.nonEmptyMapper().createCollectionType(List.class, ParamUtils.class);
		List<ParamUtils> params = JsonMapper.nonEmptyMapper().fromJson(jsonFilter, type);
		return params;
	}

	/**
	 * 取出Shiro中的当前用户LoginName.
	 */
	public static User getCurrentUser()
	{

		User user = (User) SecurityUtils.getSubject().getPrincipal();

		return user;
	}

}
