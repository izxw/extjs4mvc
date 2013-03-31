package com.cnksi.core.tools.utils;

import com.cnksi.core.constant.TreeVeriable;
import com.cnksi.core.domain.extjs.JSONTreeNode;

/**
 * Java 封装树形结构
 * 
 * @author Joe
 *
 */
public class Tree
{
	//根节点
	public JSONTreeNode root = new JSONTreeNode("0", TreeVeriable.ROOT);

	public void addNode(JSONTreeNode node)
	{
		eqNode(root, node);
	}

	private void eqNode(JSONTreeNode root, JSONTreeNode node)
	{
		if (node.getParent() == null)
		{
			node.setParent("0");
		}
		if (root.getId().equals(node.getParent()))
		{
			root.getChildren().add(node);
		} else
		{
			for (JSONTreeNode n : root.getChildren())
			{
				eqNode(n, node);
			}
		}
	}

	public void display(JSONTreeNode node, String s)
	{
		s += "|------";
		if (node.getChildren().size() > 0)
		{
			for (JSONTreeNode n : node.getChildren())
			{
				display(n, s);
			}
		}
	}

	public JSONTreeNode getRoot()
	{
		return root;
	}

	public void setRoot(JSONTreeNode root)
	{
		this.root = root;
	}

}
