package com.cnksi.extjs4mvc.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.cnksi.core.domain.IdEntity;

@Entity
@Table(name = "role")
public class Role extends IdEntity
{

	private static final long serialVersionUID = 1L;

	private String name;

	private String code;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_privilege", joinColumns = { @JoinColumn(name = "rid") }, inverseJoinColumns = { @JoinColumn(name = "privid") })
	private Set<Privilege> privilegeSet = new HashSet<Privilege>();

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public Set<Privilege> getPrivilegeSet()
	{
		return privilegeSet;
	}

	public void setPrivilegeSet(Set<Privilege> privilegeSet)
	{
		this.privilegeSet = privilegeSet;
	}

}
