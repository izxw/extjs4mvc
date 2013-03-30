package com.cnksi.extjs4mvc.repository;

import org.springframework.stereotype.Component;

import com.cnksi.core.repository.BaseRepository;
import com.cnksi.extjs4mvc.domain.User;

@Component
public interface UserRepository extends BaseRepository<User, Long>
{

	User findByUsername(String username);
}
