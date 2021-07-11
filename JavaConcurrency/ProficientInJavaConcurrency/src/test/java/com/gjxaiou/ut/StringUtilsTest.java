package com.gjxaiou.ut;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({StringUtils.class})
public class StringUtilsTest {
	@Test
	public void testEmpty() {
		String abc = "abc";
		boolean expected = true;

		PowerMockito.mockStatic(StringUtils.class);
		PowerMockito.when(StringUtils.isEmpty(abc)).thenReturn(expected);
		boolean actual = StringUtils.isEmpty(abc);
		Assert.assertEquals("返回值不相等", expected, actual);
	}
}
