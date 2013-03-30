package com.cnksi.extjs4mvc.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cnksi.core.annotation.NodeType;
import com.cnksi.core.constant.TreeNodeType;
import com.cnksi.core.domain.TreeBaseEntity;

@Entity
@Table(name = "org")
public class Org extends TreeBaseEntity
{
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "pid")
	private Org parent;

	@NodeType(type = TreeNodeType.TEXT)
	private String name;

	private String orgtype;

	private Integer level;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
	private Set<Org> children = new HashSet<Org>();

	public Org getParent()
	{
		return parent;
	}

	public void setParent(Org parent)
	{
		this.parent = parent;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getOrgtype()
	{
		return orgtype;
	}

	public void setOrgtype(String orgtype)
	{
		this.orgtype = orgtype;
	}

	public Integer getLevel()
	{
		return level;
	}

	public void setLevel(Integer level)
	{
		this.level = level;
	}

	public Set<Org> getChildren()
	{
		return children;
	}

	public void setChildren(Set<Org> children)
	{
		this.children = children;
	}

}
