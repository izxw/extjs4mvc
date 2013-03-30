package com.cnksi.extjs4mvc.domain;

import javax.persistence.Table;

import javax.persistence.Entity;

import com.cnksi.core.domain.IdEntity;

/**
 * 操作
 * @author Joe
 *
 */
@Entity
@Table(name = "operation")
public class Operation extends IdEntity
{
	private static final long serialVersionUID = 1L;

	private String name;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

}
