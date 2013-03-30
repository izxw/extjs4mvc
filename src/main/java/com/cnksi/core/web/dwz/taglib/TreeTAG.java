package com.cnksi.core.web.dwz.taglib;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

import com.cnksi.core.tools.utils.Reflections;
import com.cnksi.core.web.dwz.ILinked;

/**
 * DWZ Lookup TREE
 * 
 * DWZ 查找带回 Tree TAG 
 * 
 * <p>
 * 传入的targets对象必须是实现了ILinked接口的对象
 * </p> 
 * @author dell
 *
 */
public class TreeTAG extends TagSupport
{

	private List<ILinked> targets;

	private String fields;

	private boolean lookup = false;

	private boolean expand = false;

	private static final long serialVersionUID = 1L;

	private static final String BLANK = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

	public int doEndTag() throws JspException
	{

		try
		{
			pageContext.getOut().write(getHtml());

		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return EVAL_PAGE;
	}

	public String getHtml()
	{

		if (targets != null && targets.size() > 0)
		{
			StringBuffer sb = new StringBuffer();

			//设置Tree是否展开
			if (expand)
			{
				sb.append("<ul class='tree treeFolder expand' style='width:100%'>");
			} else
			{
				sb.append("<ul class='tree treeFolder collapse'>");
			}

			for (ILinked link : targets)
			{
				if (link.getPLink() == null)
				{

					sb.append("<li><a href='javascript:' onclick=\"" + getOnClick(link) + "\">" + link.getLinkname() + getOtherItem(link) + "</a>");

					sb.append("<ul>");

					for (ILinked subLink : targets)
					{
						if (subLink.getPLink() != null && subLink.getPLink().getLinkid().equals(link.getLinkid()))
						{

							sb.append("<li><a href='javascript:' onclick=\"" + getOnClick(subLink) + "\">" + subLink.getLinkname() + getOtherItem(subLink) + "</a>");

							getChildren(subLink.getLinkid(), sb);

							sb.append("</li>");
						}
					}

					sb.append("</ul>");

					sb.append("</li>");
				}
			}
			sb.append("</ul>");

			return sb.toString();
		}

		return "";
	}

	/**
	 * 循环判断,获取父节点parentId 下的子节点
	 * 
	 * @param parentId
	 * @param sb
	 * @throws IOException
	 */
	private void getChildren(Object parentId, StringBuffer sb)
	{

		try
		{
			for (ILinked link : targets)
			{
				if (link.getPLink() != null && link.getPLink().getLinkid().equals(parentId))
				{
					sb.append("<ul>");

					sb.append("<li><a href='javascript:' onclick=\"" + getOnClick(link) + "\">" + link.getLinkname() + getOtherItem(link) + " </a>");

					getChildren(link.getLinkid(), sb);

					sb.append("</li>");

					sb.append("</ul>");

				}
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}

	}

	private String getOnClick(ILinked linked)
	{
		if (lookup)
		{
			StringBuilder sbuider = new StringBuilder("$.bringBack({");

			//$.bringBack({menuid:'1', menuname:'权限配置'})
			if (StringUtils.isNotEmpty(fields))
			{

				String[] fieldNames = fields.split(",");

				for (String filedName : fieldNames)
				{
					sbuider.append(filedName + ":\'" + Reflections.getFieldValue(linked, filedName) + "\',");
				}
			}

			sbuider.append("})");

			return sbuider.toString();
		} else
		{
			//treeItemClick(id,name,parentid)
			return "treeItemClick('" + linked.getLinkid() + "','" + linked.getLinkname() + "','" + (linked.getPLink() != null ? linked.getPLink().getLinkid() : "") + "')";
		}
	}

	private String getOtherItem(ILinked link)
	{
		StringBuffer sb = new StringBuffer();

		String[] fieldArr = fields.split(",");
		if (fieldArr.length > 0)
		{
			sb.append("<span class='treespan'>");

			for (String field : fieldArr)
			{
				sb.append(BLANK);
				sb.append("<span>");
				sb.append(Reflections.getFieldValue(link, field));
				sb.append("</span>");
			}
			sb.append("</span>");

		}
		return sb.toString();
	}

	public List<ILinked> getTargets()
	{

		return targets;
	}

	public void setTargets(List<ILinked> targets)
	{

		this.targets = targets;
	}

	public String getFields()
	{

		return fields;
	}

	public void setFields(String fields)
	{

		this.fields = fields;
	}

	public boolean isLookup()
	{
		return lookup;
	}

	public void setLookup(boolean lookup)
	{
		this.lookup = lookup;
	}

	public boolean isExpand()
	{
		return expand;
	}

	public void setExpand(boolean expand)
	{
		this.expand = expand;
	}

}
