package com.cnksi.core.tools.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * List 工具
 * @author Joe
 *
 */
@SuppressWarnings("all")
public final class JLists
{

	public static <E> ArrayList<E> newArrayList()
	{
		return new ArrayList<E>();
	}

	public static <E> ArrayList<E> newArrayList(E... elements)
	{
		ArrayList<E> list = new ArrayList<E>();
		Collections.addAll(list, elements);
		return list;
	}

	public static <E> ArrayList<E> newArrayListWithCapacity(int initialArraySize)
	{
		return new ArrayList<E>(initialArraySize);
	}

	public static <E> LinkedList<E> newLinkedList()
	{
		return new LinkedList<E>();
	}

	public static <E> LinkedList<E> newLinkedList(Iterable<? extends E> elements)
	{
		LinkedList<E> list = newLinkedList();
		for (E element : elements)
		{
			list.add(element);
		}
		return list;
	}

	/**
	 * 转换Collection所有元素(通过toString())为String, 每个元素的前面加入prefix，后面加入postfix，如<div>mymessage</div>。
	 */

	public static String convertToString(final Collection collection, final String prefix, final String postfix)
	{

		StringBuilder builder = new StringBuilder();
		for (Object o : collection)
		{
			builder.append(prefix).append(o).append(postfix);
		}
		return builder.toString();
	}

	/**
	 * 判断是否为空.
	 */
	public static boolean isEmpty(Collection collection)
	{

		return (collection == null || collection.isEmpty());
	}

	/**
	 * 判断是否为空.
	 */
	public static boolean isNotEmpty(Collection collection)
	{

		return (collection != null && !(collection.isEmpty()));
	}

	/**
	 * 取得Collection的第一个元素，如果collection为空返回null.
	 */
	public static <T> T getFirst(Collection<T> collection)
	{

		if (isEmpty(collection))
		{
			return null;
		}

		return collection.iterator().next();
	}

	/**
	 * 获取Collection的最后一个元素 ，如果collection为空返回null.
	 */
	public static <T> T getLast(Collection<T> collection)
	{

		if (isEmpty(collection))
		{
			return null;
		}

		// 当类型为List时，直接取得最后一个元素 。
		if (collection instanceof List)
		{
			List<T> list = (List<T>) collection;
			return list.get(list.size() - 1);
		}

		// 其他类型通过iterator滚动到最后一个元素.
		Iterator<T> iterator = collection.iterator();
		while (true)
		{
			T current = iterator.next();
			if (!iterator.hasNext())
			{
				return current;
			}
		}
	}

	/**
	 * 返回a+b的新List.
	 */
	public static <T> List<T> union(final Collection<T> a, final Collection<T> b)
	{

		List<T> result = new ArrayList<T>(a);
		result.addAll(b);
		return result;
	}

	/**
	 * 返回a-b的新List.
	 */
	public static <T> List<T> subtract(final Collection<T> a, final Collection<T> b)
	{

		List<T> list = new ArrayList<T>(a);
		for (T element : b)
		{
			list.remove(element);
		}

		return list;
	}

	/**
	 * 返回a与b的交集的新List.
	 */
	public static <T> List<T> intersection(Collection<T> a, Collection<T> b)
	{

		List<T> list = new ArrayList<T>();

		for (T element : a)
		{
			if (b.contains(element))
			{
				list.add(element);
			}
		}
		return list;
	}
}
