package com.cnksi.core.tools.utils;

import com.cnksi.core.constant.TreeVeriable;
import com.cnksi.core.domain.extjs.JSONTreeNode;

public class Tree
{
	//根节点
	public JSONTreeNode root = new JSONTreeNode("0", TreeVeriable.ROOT);

	public void addNode(Object node)
	{
		eqNode(root, node);
	}

	private void eqNode(Object _root, Object _node)
	{
		if (_node instanceof JSONTreeNode && _root instanceof JSONTreeNode)
		{
			JSONTreeNode node = (JSONTreeNode) _node;

			JSONTreeNode root = (JSONTreeNode) _root;
			if (node.getParent() == null)
			{
				node.setParent("0");
			}
			if (root.getId().equals(node.getParent()))
			{
				root.getChildren().add(node);
			} else
			{
				for (Object n : root.getChildren())
				{
					eqNode(n, node);
				}
			}
		}
	}

	public void display(Object _node, String s)
	{
		if (_node instanceof JSONTreeNode)
		{
			JSONTreeNode node = (JSONTreeNode) _node;
			System.out.println(s + node.getId());
			s += "|------";
			if (node.getChildren().size() > 0)
			{
				for (Object n : node.getChildren())
				{
					display(n, s);
				}
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
