package com.cnksi.core.tools.poi.annotation;

import java.util.Comparator;

public class ComparatorDescription implements Comparator<DescriptionInfo>
{

	@Override
	public int compare(DescriptionInfo o1, DescriptionInfo o2)
	{
		return o1.getOrder().compareTo(o2.getOrder());
	}

}