package com.cnksi.core.domain;

import java.util.HashSet;
import java.util.Set;

public class SimpleTreeRoot extends TreeBaseEntity
{
	public SimpleTreeRoot(Long id)
	{
		this.id = id;
	}

	@Override
	public TreeBaseEntity getParent()
	{
		return null;
	}

	@Override
	public Set getChildren()
	{
		return new HashSet();
	}

}
