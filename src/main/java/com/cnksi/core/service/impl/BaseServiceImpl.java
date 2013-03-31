package com.cnksi.core.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import com.cnksi.core.exception.BaseException;
import com.cnksi.core.repository.BaseRepository;
import com.cnksi.core.repository.SearchFilter;
import com.cnksi.core.repository.springdata.DynamicSpecifications;
import com.cnksi.core.service.BaseService;
import com.cnksi.core.web.extjs.ParamUtils;

public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID>
{

	protected Class<T> entityClass;

	protected BaseRepository<T, ID> baseDao;

	public abstract void setBaseDao(BaseRepository<T, ID> baseDao);

	@SuppressWarnings("unchecked")
	public BaseServiceImpl()
	{
		try
		{
			entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	@Transactional(readOnly = false)
	@Override
	public <S extends T> S save(S entity) throws BaseException
	{
		return baseDao.save(entity);
	}

	@Transactional(readOnly = false)
	@Override
	public <S extends T> Iterable<S> save(Iterable<S> entities) throws BaseException
	{
		return baseDao.save(entities);
	}

	@Override
	public T findOne(ID id) throws BaseException
	{
		return baseDao.findOne(id);
	}

	@Override
	public boolean exists(ID id) throws BaseException
	{
		return baseDao.exists(id);
	}

	@Override
	public Iterable<T> findAll() throws BaseException
	{
		return baseDao.findAll();
	}

	@Override
	public Iterable<T> findAll(Iterable<ID> ids) throws BaseException
	{
		return baseDao.findAll(ids);
	}

	@Override
	public long count()
	{
		return baseDao.count();
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(ID id) throws BaseException
	{
		baseDao.delete(id);
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(T entity) throws BaseException
	{
		baseDao.delete(entity);
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(Iterable<? extends T> entities) throws BaseException
	{
		baseDao.delete(entities);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteAll() throws BaseException
	{
		baseDao.deleteAll();
	}

	/******************************************** PagingAndSortingRepository ***************************************************/
	@Override
	public Iterable<T> findAll(Sort sort) throws BaseException
	{
		return baseDao.findAll(sort);

	}

	@Override
	public Page<T> findAll(Pageable pageable) throws BaseException
	{
		return baseDao.findAll(pageable);

	}

	@Override
	public Page<T> findAll(Pageable pageable, Map<String, Object> searchParams) throws BaseException
	{

		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);

		Specification<T> spec = DynamicSpecifications.bySearchFilter(filters.values(), entityClass);

		return baseDao.findAll(spec, pageable);

	}

	@Override
	public Page<T> findAll(Pageable pageable, List<ParamUtils> params) throws BaseException
	{
		if (params != null)
		{
			Map<String, SearchFilter> filters = SearchFilter.parse(params);

			Specification<T> spec = DynamicSpecifications.bySearchFilter(filters.values(), entityClass);

			return baseDao.findAll(spec, pageable);
		} else
		{
			return baseDao.findAll(pageable);
		}

	}

	/******************************************** JpaRepository ***************************************************/
	@Override
	public void flush()
	{
		baseDao.flush();
	}

	@Transactional(readOnly = false)
	@Override
	public T saveAndFlush(T entity) throws BaseException
	{
		return baseDao.saveAndFlush(entity);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteInBatch(Iterable<T> entities) throws BaseException
	{
		baseDao.deleteInBatch(entities);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteAllInBatch() throws BaseException
	{
		baseDao.deleteAllInBatch();
	}

}
