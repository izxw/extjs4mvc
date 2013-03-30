package com.cnksi.extjs4mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.cnksi.core.repository.BaseRepository;
import com.cnksi.extjs4mvc.domain.Org;

@Component
public interface OrgRepository extends BaseRepository<Org, Long>
{
	@Query("FROM Org where parent is null")
	public List<Org> selectTopOrg();
}
