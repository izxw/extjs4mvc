
package com.cnksi.core.helper;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class EhCacheHelper
{

	private Cache cache;

	public EhCacheHelper(Cache cache)
	{

		this.cache = cache;
	}

	/**
	 * 将对象放入缓存
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value)
	{

		Element element = new Element(key, value);

		cache.put(element);
	}

	/**
	 * 将缓存中的对象取出
	 * @param key
	 * @return
	 */
	public Object get(String key)
	{

		Element element = cache.get(key);

		if (element != null)
		{
			return element.getValue();

		} else
		{
			return null;
		}
	}

	/**
	 * 从缓存中移出对象
	 * @param key
	 */
	public void remove(String key)
	{

		cache.remove("key");
	}

	/**
	 * Shutdown缓存管理器
	 */
	public void shutdown()
	{

		cache.getCacheManager().shutdown();
	}

	/**
	 * 缓存监控
	 */
	public void cacheMonitor()
	{

		//得到缓存中的对象数
		int cachesize = cache.getSize();

		System.out.println("当前缓存中的对象数:" + cachesize);

		//得到缓存对象占用内存的大小
		long memorySize = cache.getMemoryStoreSize();

		System.out.println("当前缓存对象占用内存的大小:" + memorySize);

		//得到缓存读取的命中次数
		long cachHits = cache.getStatistics().getCacheHits();

		System.out.println("当前缓存读取的命中次数:" + cachHits);

		//得到缓存读取的错失次数
		long cacheMiss = cache.getStatistics().getCacheMisses();

		System.out.println("当前缓存读取的错失次数:" + cacheMiss);

	}
}
