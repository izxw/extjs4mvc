package com.cnksi.core.web.utils;

import java.io.Serializable;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

/**
 * 封装前台分页对象
 * 
 * @author Joe
 *
 */
public class Pagination implements Serializable
{

	private static final long serialVersionUID = 1L;

	private int pageNum = 1;

	private int numPerPage = 20;

	private String orderField = "createtime";

	private boolean ascending = true;

	public int getPageNum()
	{

		return pageNum - 1;
	}

	public void setPageNum(int pageNum)
	{

		this.pageNum = pageNum;
	}

	public int getNumPerPage()
	{

		return numPerPage;
	}

	public void setNumPerPage(int numPerPage)
	{

		this.numPerPage = numPerPage;
	}

	public boolean isAscending()
	{
		return ascending;
	}

	public void setAscending(boolean ascending)
	{
		this.ascending = ascending;
	}

	public String getOrderField()
	{

		return orderField;
	}

	public void setOrderField(String orderField)
	{

		this.orderField = orderField;
	}

	public PageRequest getPageable()
	{
		PageRequest pagerequest = null;
		if (ascending)
		{
			pagerequest = new PageRequest(pageNum - 1, numPerPage, Direction.ASC, orderField);
		} else
		{
			pagerequest = new PageRequest(pageNum - 1, numPerPage, Direction.DESC, orderField);
		}
		return pagerequest;
	}

	@Override
	public String toString()
	{
		return "Pagination [pageNum=" + pageNum + ", numPerPage=" + numPerPage + ", orderField=" + orderField + "]";
	}

}
