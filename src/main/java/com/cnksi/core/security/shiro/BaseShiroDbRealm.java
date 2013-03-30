package com.cnksi.core.security.shiro;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.cnksi.core.domain.BaseUser;
import com.cnksi.core.security.service.user.BaseUserService;
import com.cnksi.core.tools.utils.Encodes;

public class BaseShiroDbRealm extends AuthorizingRealm
{
	//是否启用操作权限过滤
	protected boolean contentFilter = true;

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{

		//User user = (User) principals.getPrimaryPrincipal();

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		//判断是否启用操作过滤
		//		if (contentFilter)
		//		{
		//			info.addRoles(roleService.findRolecodeByUserid(user.getId()));
		//
		//			List<String> permissions = Collections3.extractToList(operationService.findOperationByRoleids(user.getHasRoleids()), "operacode");
		//
		//			info.addStringPermissions(permissions);
		//
		//		} else
		//		{
		//			info.addStringPermission("*:*:*");
		//		}
		
		info.addStringPermission("*:*:*");
		
		return info;
	}

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException
	{

		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		System.out.println(token.getUsername());

		//		User user = userService.findByUsername(token.getUsername());
		//
		//		if (user != null)
		//		{
		//			byte[] salt = Encodes.decodeHex(user.getSalt());
		//
		//			return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(salt), getName());
		//		} else
		//		{
		//			return null;
		//		}
		BaseUser user = new BaseUser();
		byte[] salt = Encodes.decodeHex(user.getSalt());
		return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(salt), getName());
	}

	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@PostConstruct
	public void initCredentialsMatcher()
	{

		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(BaseUserService.HASH_ALGORITHM);
		matcher.setHashIterations(BaseUserService.HASH_INTERATIONS);

		setCredentialsMatcher(matcher);
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal)
	{

		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo()
	{

		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null)
		{
			for (Object key : cache.keys())
			{
				cache.remove(key);
			}
		}
	}

	public boolean isContentFilter()
	{
		return contentFilter;
	}

	public void setContentFilter(boolean contentFilter)
	{
		this.contentFilter = contentFilter;
	}
}
