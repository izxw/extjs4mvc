package com.cnksi.core.repository;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.cnksi.core.web.extjs.ParamUtils;
import com.google.common.collect.Maps;

public class SearchFilter
{

	public enum Operator
	{
		EQ, LIKE, GT, LT, GTE, LTE
	}

	public String fieldName;

	public Object value;

	public Operator operator;

	public SearchFilter(String fieldName, Operator operator, Object value)
	{
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	public static Map<String, SearchFilter> parse(Map<String, Object> filterParams)
	{
		Map<String, SearchFilter> filters = Maps.newHashMap();

		for (Entry<String, Object> entry : filterParams.entrySet())
		{
			String[] names = StringUtils.split(entry.getKey(), "_");
			if (names.length != 2)
			{
				throw new IllegalArgumentException(entry.getKey() + " is not a valid search filter name");
			}

			if (StringUtils.isNotBlank(String.valueOf(entry.getValue())))
			{
				SearchFilter filter = new SearchFilter(names[1], Operator.valueOf(names[0]), entry.getValue());

				filters.put(filter.fieldName, filter);
			}
		}

		return filters;
	}
	
	public static Map<String, SearchFilter> parse(List<ParamUtils> paramUtils)
	{
		Map<String, SearchFilter> filters = Maps.newHashMap();

		for (ParamUtils entry : paramUtils)
		{
			String[] names = StringUtils.split(entry.getProperty(), "_");
			if (names.length != 2)
			{
				throw new IllegalArgumentException(entry.getProperty() + " is not a valid search filter name");
			}

			if (StringUtils.isNotBlank(String.valueOf(entry.getValue())))
			{
				SearchFilter filter = new SearchFilter(names[1], Operator.valueOf(names[0]), entry.getValue());

				filters.put(filter.fieldName, filter);
			}
		}

		return filters;
	}
}
