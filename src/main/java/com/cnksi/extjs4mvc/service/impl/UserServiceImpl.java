package com.cnksi.extjs4mvc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cnksi.core.repository.BaseRepository;
import com.cnksi.core.service.impl.BaseServiceImpl;
import com.cnksi.extjs4mvc.domain.User;
import com.cnksi.extjs4mvc.repository.UserRepository;
import com.cnksi.extjs4mvc.service.UserService;

@Component
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService
{
	private UserRepository userRepository;

	@Override
	@Resource(name = "userRepository")
	public void setBaseDao(BaseRepository<User, Long> userRepository)
	{
		super.baseDao = userRepository;

		this.userRepository = (UserRepository) userRepository;
	}

	@Override
	public User findByUsername(String username)
	{
		return userRepository.findByUsername(username);
	}

}
