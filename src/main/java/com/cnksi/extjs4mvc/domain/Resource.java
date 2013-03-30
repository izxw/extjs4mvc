package com.cnksi.extjs4mvc.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cnksi.core.annotation.NodeType;
import com.cnksi.core.constant.TreeNodeType;
import com.cnksi.core.domain.TreeBaseEntity;

/**
 * 资源
 * @author Joe
 *
 */
@Entity
@Table(name = "resource")
public class Resource extends TreeBaseEntity
{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "pid")
	private Resource parent;
	
	@NodeType(type=TreeNodeType.TEXT)
	private String name;

	private String type;

	@NodeType(type=TreeNodeType.DESCRIPTION)
	private String value;

	private String value1;

	//@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
	@Transient
	private Set<Resource> children = new HashSet<Resource>();

	public Resource getParent()
	{
		return parent;
	}

	public void setParent(Resource parent)
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

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public String getValue1()
	{
		return value1;
	}

	public void setValue1(String value1)
	{
		this.value1 = value1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set getChildren()
	{
		return this.children;
	}

}
