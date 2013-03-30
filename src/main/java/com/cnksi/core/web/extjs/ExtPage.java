package com.cnksi.core.web.extjs;

import org.springframework.data.domain.PageRequest;

public class ExtPage
{
	private Integer limit = 25;

	private Integer page = 1;

	private Integer start = 0;

	public PageRequest getPageable()
	{
		return new PageRequest(page-1, limit);
	}

	public Integer getLimit()
	{
		return limit;
	}

	public void setLimit(Integer limit)
	{
		this.limit = limit;
	}

	public Integer getPage()
	{
		return page;
	}

	public void setPage(Integer page)
	{
		this.page = page;
	}

	public Integer getStart()
	{
		return start;
	}

	public void setStart(Integer start)
	{
		this.start = start;
	}

}
