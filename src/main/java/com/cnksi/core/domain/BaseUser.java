package com.cnksi.core.domain;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public class BaseUser extends IdEntity implements Serializable
{

	private static final long serialVersionUID = 1L;

	private String username = "admin";

	private String plainPassword = "admin";

	private String password = "a03443f1e692a9fd334a0bcfd98820cb0ecdd8a4";

	private String salt = "39bb9314424cbc1c";

	public BaseUser()
	{

	}

	public BaseUser(Long id, String username)
	{

		this.id = id;

		this.username = username;
	}

	/**
	 * 用户账号
	 */
	public String getUsername()
	{

		return this.username;
	}

	/**
	 * @param username 用户账号
	 */
	public void setUsername(String username)
	{

		this.username = username;
	}

	/**
	 * 用户密码
	 */
	public String getPassword()
	{

		return this.password;
	}

	/**
	 * @param password 用户密码
	 */
	public void setPassword(String password)
	{

		this.password = password;
	}

	/**
	 * 加密Salt次数
	 */
	public String getSalt()
	{

		return salt;
	}

	public void setSalt(String salt)
	{

		this.salt = salt;
	}

	@Transient
	public String getPlainPassword()
	{

		return plainPassword;
	}

	public void setPlainPassword(String plainPassword)
	{

		this.plainPassword = plainPassword;
	}

	@Override
	public String toString()
	{

		return this.username;
	}

}