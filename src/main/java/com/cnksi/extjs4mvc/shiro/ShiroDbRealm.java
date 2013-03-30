package com.cnksi.extjs4mvc.shiro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.cnksi.core.security.shiro.BaseShiroDbRealm;
import com.cnksi.core.tools.mapper.JsonMapper;
import com.cnksi.core.tools.utils.Encodes;
import com.cnksi.extjs4mvc.domain.Privilege;
import com.cnksi.extjs4mvc.domain.Resource;
import com.cnksi.extjs4mvc.domain.Role;
import com.cnksi.extjs4mvc.domain.User;
import com.cnksi.extjs4mvc.service.UserService;

public class ShiroDbRealm extends BaseShiroDbRealm
{
	protected UserService userService;


	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{

		User user = (User) principals.getPrimaryPrincipal();

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		//判断是否启用操作过滤
		if (contentFilter)
		{
			info.addRoles(user.getRolecode());

			//List<String> permissions = Collections3.extractToList(operationService.findOperationByRoleids(user.getHasRoleids()), "operacode");
			
			//info.addStringPermissions(getPermissions(user.getRoleSet(),null));

		} else
		{
			info.addStringPermission("*:*:*");
		}
		return info;
	}

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException
	{

		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		User user = userService.findByUsername(token.getUsername());

		if (user != null)
		{
			byte[] salt = Encodes.decodeHex(user.getSalt());

			return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(salt), getName());
		} else
		{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	private Collection<String> getPermissions(Set<Role> roleSet,ShiroFilterFactoryBean shiroFilter)
	{
		List<String> permissions = new ArrayList<String>();
		for (Role role : roleSet)
		{
			Set<Privilege> privSet = role.getPrivilegeSet();
			if (privSet != null)
			{
				for (Privilege priv : privSet)
				{
					Resource res = priv.getResouce();
					if (res.getType().equals("menu"))
					{

						Map<String, String> map = JsonMapper.nonEmptyMapper().fromJson(res.getValue(), HashMap.class);

						if (map!=null && map.containsKey("url"))
						{
							permissions.add(map.get("url"));
							
							shiroFilter.getFilterChainDefinitionMap().put(map.get("url"), "roles["+role.getCode()+"]");
						}
					}
					
					if (res.getType().equals("operation"))
					{

						Map<String, String> map = JsonMapper.nonEmptyMapper().fromJson(res.getValue(), HashMap.class);

						if (map!=null && map.containsKey("featureName"))
						{
							permissions.add(map.get("featureName"));
							shiroFilter.getFilterChainDefinitionMap().put(map.get("url"),"perms["+map.get("featureName")+"]");
						}
					}
				}
			}
		}

		return permissions;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}
}
