package com.cnksi.extjs4mvc.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cnksi.core.domain.IdEntity;

@Entity
@Table(name = "privilege")
public class Privilege extends IdEntity
{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "opid")
	private Operation operation;

	@ManyToOne
	@JoinColumn(name = "resid")
	private Resource resouce;

	public Operation getOperation()
	{
		return operation;
	}

	public void setOperation(Operation operation)
	{
		this.operation = operation;
	}

	public Resource getResouce()
	{
		return resouce;
	}

	public void setResouce(Resource resouce)
	{
		this.resouce = resouce;
	}

}
