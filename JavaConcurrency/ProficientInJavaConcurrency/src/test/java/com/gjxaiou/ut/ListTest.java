package com.gjxaiou.ut;

import org.junit.Assert;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import java.util.List;

public class ListTest {
	@Test
	public void testGet() {
		int index = 0;
		Integer expected = 100;
		List<Integer> mockList = PowerMockito.mock(List.class);
		PowerMockito.when(mockList.get(index)).thenReturn(expected);
		Integer actual = mockList.get(index);
		Assert.assertEquals("返回值不相等", expected, actual);
	}
}