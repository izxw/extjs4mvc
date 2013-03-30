
package com.cnksi.core.web.dwz.taglib;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.cnksi.core.web.dwz.ILinked;

/**
 * DWZ CHECK TREE
 * 
 * DWZ 树形菜单TAG 
 * <p>
 * 传入的targets对象必须是实现了ILinked接口的对象
 * 
 * @author dell
 *
 */
public class CheckTreeTAG extends TagSupport
{

	private List<ILinked> targets;

	private List<Object> pathList;

	private String oncheck;

	private String inputName;

	private static final long serialVersionUID = 1L;

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

			sb.append("<ul class='tree treeFolder treeCheck expand'" + " oncheck= '" + oncheck + "'>");

			for (ILinked link : targets)
			{
				if (link.getPLink() == null)
				{
					if (pathList != null && pathList.contains(link.getLinkid()))
					{
						sb.append("<li><a tname='" + inputName + "' tvalue='" + link.getLinkid() + "' checked=true >" + link.getLinkname() + "</a>");

					} else
					{
						sb.append("<li><a tname='" + inputName + "' tvalue='" + link.getLinkid() + "'>" + link.getLinkname() + "</a>");
					}

					sb.append("<ul>");

					for (ILinked subLink : targets)
					{
						if (subLink.getPLink() != null && subLink.getPLink().getLinkid().equals(link.getLinkid()))
						{
							if (pathList != null && pathList.contains(subLink.getLinkid()))
							{
								sb.append("<li><a tname='" + inputName + "' tvalue='" + subLink.getLinkid() + "' checked=true>" + subLink.getLinkname() + "</a>");

							} else
							{
								sb.append("<li><a tname='" + inputName + "' tvalue='" + subLink.getLinkid() + "'>" + subLink.getLinkname() + "</a>");
							}
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

					if (pathList != null && pathList.contains(link.getLinkid()))
					{
						sb.append("<li><a tname='" + inputName + "' tvalue='" + link.getLinkid() + "' checked=true >" + link.getLinkname() + "</a></li>");

					} else
					{
						sb.append("<li><a tname='" + inputName + "' tvalue='" + link.getLinkid() + "' >" + link.getLinkname() + "</a></li>");
					}

					getChildren(link.getLinkid(), sb);

					sb.append("</ul>");

				}
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}

	}

	public List<ILinked> getTargets()
	{

		return targets;
	}

	public void setTargets(List<ILinked> targets)
	{

		this.targets = targets;
	}

	/**
	 * Check树中，input的naem值 
	 * @return
	 */
	public String getInputName()
	{

		return inputName;
	}

	public void setInputName(String inputName)
	{

		this.inputName = inputName;
	}

	/**
	 * 该Tree中，在数据库中已经存的数据ID集合
	 * 
	 * 用于判断当前某个节点是否checked
	 * 
	 * @return
	 */
	public List<Object> getPathList()
	{

		return pathList;
	}

	public void setPathList(List<Object> pathList)
	{

		this.pathList = pathList;
	}

	/**
	 * 设定OnCheck函数,TREE被点击时触发的事件
	 * 
	 * @return
	 */
	public String getOncheck()
	{

		return oncheck;
	}

	public void setOncheck(String oncheck)
	{

		this.oncheck = oncheck;
	}

}
