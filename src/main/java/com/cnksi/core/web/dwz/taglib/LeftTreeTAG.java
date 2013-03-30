
package com.cnksi.core.web.dwz.taglib;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

import com.cnksi.core.web.dwz.ILinked;

/**
 * DWZ左侧Accordion树形导航TAG <p> 传入的targets对象必须是实现了ILinked接口的对象
 * 
 * @author dell
 * 
 * @see {@link ILinked}
 */
public class LeftTreeTAG extends TagSupport
{

	private static final long serialVersionUID = 1L;

	private List<ILinked> targets;

	// 应用前置路径
	private String basePath = "";

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

			for (ILinked link : targets)
			{
				if (link.getPLink() == null)
				{
					sb.append("<div class='accordionHeader'><h2><span>Folder</span>" + link.getLinkname() + "</h2></div>");

					sb.append("\n");

					sb.append("<div class='accordionContent'>");

					sb.append("\n");

					sb.append("\t<ul class='tree treeFolder'>");

					sb.append("\n");

					for (ILinked subLink : targets)
					{
						if (subLink.getPLink() != null && subLink.getPLink().getLinkid().equals(link.getLinkid()))
						{

							if (StringUtils.isNotBlank(subLink.getLinkURI()))
							{
								String uri = subLink.getLinkURI();

								if (!uri.startsWith("/"))
								{
									uri = "/" + uri;
								}
								sb.append("\t\t");
								sb.append("<li><a id='" + subLink.getRel() + "' href='" + basePath + uri + ";jsessionid=" + pageContext.getSession().getId() + "?navTabId=" + subLink.getRel() + "' target='" + subLink.getTarget() + "' rel='" + subLink.getRel() + "'>" + subLink.getLinkname() + "</a>\n");
							} else
							{
								sb.append("\t\t");
								sb.append("\t\t<li><a id='" + subLink.getRel() + "'>" + subLink.getLinkname() + "</a>");
							}

							getChildren(subLink.getLinkid(), sb);

							sb.append("\t\t");
							sb.append("</li>");

							sb.append("\n");
						}
					}
					sb.append("\t");
					sb.append("</ul>");

					sb.append("\n");

					sb.append("</div>");

					sb.append("\n");
				}
			}
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
					sb.append("\t\t\t");
					sb.append("<ul>");

					sb.append("\n");

					String uri = link.getLinkURI();

					if (!uri.startsWith("/"))
					{
						uri = "/" + uri;
					}
					sb.append("\t\t\t\t");
					sb.append("<li><a id='" + link.getRel() + "' href='" + basePath + uri + ";jsessionid=" + pageContext.getSession().getId() + "?navTabId=" + link.getRel() + "' target='" + link.getTarget() + "' rel='" + link.getRel() + "'>" + link.getLinkname() + "</a></li>");

					sb.append("\n");

					getChildren(link.getLinkid(), sb);

					sb.append("\t\t\t");
					sb.append("</ul>");

					sb.append("\n");
				}
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}

	}

	/**
	 * 实现了ILinked接口的菜单对象集合
	 * 
	 * @return
	 */
	public List<ILinked> getTargets()
	{

		return targets;
	}

	public void setTargets(List<ILinked> targets)
	{

		this.targets = targets;
	}

	/**
	 * 应用的ctx
	 * @return
	 */
	public String getBasePath()
	{

		return basePath;
	}

	public void setBasePath(String basePath)
	{

		this.basePath = basePath;
	}

}
