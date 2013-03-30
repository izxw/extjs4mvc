package com.cnksi.core.tools.poi.annotation;

import java.util.Collections;
import java.util.List;

public class DescriptionInfo
{

	private String field;

	private String comment;

	private Integer size;

	//在Excel中的顺序
	private Integer order;

	private Class<?> classz;

	public DescriptionInfo()
	{

	}

	public DescriptionInfo(String field, String comment, Integer size, Class<?> classz, Integer order)
	{

		this.field = field;

		this.comment = comment;

		this.size = size;

		this.classz = classz;

		this.order = order;

	}

	public String getField()
	{

		return field;
	}

	public String getComment()
	{

		return comment;
	}

	public Integer getSize()
	{

		return size;
	}

	public DescriptionInfo setField(String field)
	{

		this.field = field;

		return this;
	}

	public DescriptionInfo setComment(String comment)
	{

		this.comment = comment;

		return this;
	}

	public DescriptionInfo setSize(Integer size)
	{

		this.size = size;

		return this;
	}

	public Class<?> getClassz()
	{
		return classz;
	}

	public DescriptionInfo setClassz(Class<?> classz)
	{
		this.classz = classz;

		return this;
	}

	public Integer getOrder()
	{
		return order;
	}

	public void setOrder(Integer order)
	{
		this.order = order;
	}

	public static void Sort(List<DescriptionInfo> list)
	{
		Collections.sort(list, new ComparatorDescription());
	}
}
