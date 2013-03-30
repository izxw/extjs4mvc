package com.cnksi.core.domain;

import java.util.Set;

import com.cnksi.core.annotation.NodeType;
import com.cnksi.core.constant.TreeNodeType;

public abstract class TreeBaseEntity extends IdEntity
{
	private static final long serialVersionUID = 1L;
	@NodeType(type = TreeNodeType.LAYER)
	private Integer layer;
	@NodeType(type = TreeNodeType.NODEINFO)
	private String nodeInfo;
	@NodeType(type = TreeNodeType.LEAF)
	private String nodeType;
	@NodeType(type = TreeNodeType.NODEINFOTYPE)
	private String nodeInfoType;
	
	public abstract TreeBaseEntity getParent();

	@SuppressWarnings("rawtypes")
	public abstract Set getChildren();

	public Integer getLayer()
	{
		return layer;
	}

	public void setLayer(Integer layer)
	{
		this.layer = layer;
	}

	public String getNodeInfo()
	{
		return nodeInfo;
	}

	public void setNodeInfo(String nodeInfo)
	{
		this.nodeInfo = nodeInfo;
	}

	public String getNodeType()
	{
		return nodeType;
	}

	public void setNodeType(String nodeType)
	{
		this.nodeType = nodeType;
	}

	public String getNodeInfoType()
	{
		return nodeInfoType;
	}

	public void setNodeInfoType(String nodeInfoType)
	{
		this.nodeInfoType = nodeInfoType;
	}
}
