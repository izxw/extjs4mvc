package com.cnksi.core.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cnksi.core.tools.poi.annotation.Description;

@MappedSuperclass
public class BaseEntity implements Serializable
{
	private static final long serialVersionUID = 4578922372402076316L;

	@Temporal(TemporalType.TIMESTAMP)
	@Description(comment = "创建时间", order = 100)
	@Column(name = "create_dt")
	protected Date createtime = new Date();

	@Column(name = "create_by")
	protected String creater;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modi_dt")
	private Date lastmodifytime = new Date();

	@Column(name = "modi_by")
	protected String modifier;

	@Column(name = "is_del")
	protected String del;

	protected Integer version = 0;

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public Date getLastmodifytime()
	{
		return lastmodifytime;
	}

	public void setLastmodifytime(Date lastmodifytime)
	{
		this.lastmodifytime = lastmodifytime;
	}

	public String getCreater()
	{
		return creater;
	}

	public void setCreater(String creater)
	{
		this.creater = creater;
	}

	public String getModifier()
	{
		return modifier;
	}

	public void setModifier(String modifier)
	{
		this.modifier = modifier;
	}

	public String getDel()
	{
		return del;
	}

	public void setDel(String del)
	{
		this.del = del;
	}

	/**
	 * a lock 
	 * 
	 * @return
	 */
	public Integer getVersion()
	{
		return version;
	}

	public void setVersion(Integer version)
	{
		this.version = version;
	}

}
