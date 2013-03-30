package com.cnksi.core.constant;

/**
 * 树形字段类型枚举
 *
 */
public enum TreeNodeType
{
	ID, //主键
	TEXT, //名称
	CODE, //编码{Extends} 
	CLS, //样式
	HREF, //链接(存放链接显示的内容)
	HREFTARGET, //链接地址
	EXPANDABLE, //是否可以展开
	EXPANDED, //是否展开
	DESCRIPTION, //描述
	ICON, //图片
	CHECKED, //是否选中
	PARENT, //父节点ID
	LEAF, //是否是叶子
	NODEINFO, //节点信息 
	NODEINFOTYPE, //节点实体类型
	ORDERINDEX, //排序字段
	LAYER, DISABLED; //是否禁用
	public Boolean equalsType(TreeNodeType other)
	{
		int i = this.compareTo(other);
		if (i != 0)
		{
			return false;
		}
		return true;
	}
}
