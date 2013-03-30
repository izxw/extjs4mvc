package com.cnksi.extjs4mvc.repository;

import org.springframework.stereotype.Component;

import com.cnksi.core.repository.BaseRepository;
import com.cnksi.extjs4mvc.domain.Role;

@Component
public interface RoleRepository extends BaseRepository<Role, Long>
{
}
