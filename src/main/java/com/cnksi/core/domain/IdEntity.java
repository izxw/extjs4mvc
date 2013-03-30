package com.cnksi.core.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.cnksi.core.annotation.NodeType;
import com.cnksi.core.constant.TreeNodeType;

@MappedSuperclass
public class IdEntity extends BaseEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NodeType(type = TreeNodeType.ID)
	protected Long id;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
}
