package com.cnksi.extjs4mvc.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.cnksi.core.domain.BaseUser;
import com.cnksi.core.tools.mapper.JsonMapper;
import com.cnksi.core.tools.utils.Collections3;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({ "roleSet" })
public class User extends BaseUser
{
	private static final long serialVersionUID = 1L;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "role_user", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns = { @JoinColumn(name = "rid") })
	private Set<Role> roleSet = new HashSet<Role>();

	public Set<Role> getRoleSet()
	{
		return roleSet;
	}

	public void setRoleSet(Set<Role> roleSet)
	{
		this.roleSet = roleSet;
	}

	@SuppressWarnings("unchecked")
	public Collection<String> getRolecode()
	{
		List<String> rolesCode = Collections3.extractToList(roleSet, "code");
		return rolesCode;
	}
}
