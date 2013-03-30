package com.cnksi.extjs4mvc.service;

import com.cnksi.core.service.BaseService;
import com.cnksi.extjs4mvc.domain.User;

public interface UserService extends BaseService<User, Long>
{	

	User findByUsername(String username);

}
