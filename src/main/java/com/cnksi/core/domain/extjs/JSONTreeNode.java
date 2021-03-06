package com.cnksi.core.domain.extjs;

import java.util.ArrayList;
import java.util.List;

import com.cnksi.core.domain.TreeBaseEntity;
import com.cnksi.core.tools.utils.JStringUtils;
import com.cnksi.core.tools.utils.Reflections;

/**
 * 树形模版类
 *
 */
public class JSONTreeNode
{
	private String id;
	private String text;
	private String code;
	private Boolean leaf = false;
	private String href;
	private String hrefTarget;
	private String cls;
	private String icon;
	private Boolean expandable = true;
	private Boolean expanded = true;
	private String description;
	private Boolean checked;
	private String parent;
	private String nodeType;
	private String nodeInfo;
	private String nodeInfoType;
	private Integer orderIndex;
	private Boolean disabled;
	private List<JSONTreeNode> children = new ArrayList<JSONTreeNode>();

	public JSONTreeNode()
	{
	}

	public JSONTreeNode(String id, String text)
	{
		this.id = id;
		this.text = text;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public Boolean getLeaf()
	{
		if (this.children.isEmpty())
		{
			this.leaf = true;
		}
		return leaf;
	}

	public void setLeaf(Boolean leaf)
	{
		this.leaf = leaf;
	}

	public String getHref()
	{
		return href;
	}

	public void setHref(String href)
	{
		this.href = href;
	}

	public String getHrefTarget()
	{
		return hrefTarget;
	}

	public void setHrefTarget(String hrefTarget)
	{
		this.hrefTarget = hrefTarget;
	}

	public String getCls()
	{
		return cls;
	}

	public void setCls(String cls)
	{
		this.cls = cls;
	}

	public String getIcon()
	{
		return icon;
	}

	public void setIcon(String icon)
	{
		this.icon = icon;
	}

	public Boolean getExpandable()
	{
		return expandable;
	}

	public void setExpandable(Boolean expandable)
	{
		this.expandable = expandable;
	}

	public Boolean getExpanded()
	{
		return expanded;
	}

	public void setExpanded(Boolean expanded)
	{
		this.expanded = expanded;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Boolean getChecked()
	{
		return checked;
	}

	public void setChecked(Boolean checked)
	{
		this.checked = checked;
	}

	public String getParent()
	{
		return parent;
	}

	public void setParent(String parent)
	{
		this.parent = parent;
	}

	public String getNodeInfo()
	{
		return nodeInfo;
	}

	public void setNodeInfo(String nodeInfo)
	{
		this.nodeInfo = nodeInfo;
	}

	public String getNodeInfoType()
	{
		return nodeInfoType;
	}

	public void setNodeInfoType(String nodeInfoType)
	{
		this.nodeInfoType = nodeInfoType;
	}

	public Integer getOrderIndex()
	{
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex)
	{
		this.orderIndex = orderIndex;
	}

	public Boolean getDisabled()
	{
		return disabled;
	}

	public void setDisabled(Boolean disabled)
	{
		this.disabled = disabled;
	}

	public List<JSONTreeNode> getChildren()
	{
		return children;
	}

	public void setChildren(List<JSONTreeNode> children)
	{
		this.children = children;
	}

	public String getNodeType()
	{
		return nodeType;
	}

	public void setNodeType(String nodeType)
	{
		this.nodeType = nodeType;
	}

	public void setJSONTreeNodeValue(JSONTreeNode template, TreeBaseEntity obj)
	{
		if (obj.getParent() != null)
		{
			this.setParent(obj.getParent().getId().toString());
		}
		if (JStringUtils.isNotEmpty(template.getText()))
		{
			this.setText(Reflections.getFieldValue(obj, template.getText()).toString());
		}

		if (JStringUtils.isNotEmpty(template.getId()))
		{
			this.setId(Reflections.getFieldValue(obj, template.getId()).toString());
		}

		if (JStringUtils.isNotEmpty(template.getDescription()))
		{
			this.setDescription(Reflections.getFieldValue(obj, template.getDescription()).toString());
		}
	}

	@Override
	public String toString()
	{
		return "JSONTreeNode [id=" + id + ", text=" + text + ", code=" + code + ", parent=" + parent + "]";
	}

}
