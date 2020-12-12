
 /**
 * FileName:     PatternTest.java
 * Copyright (c) 2019 lzc.All Rights Reserved.
 */

package spittr;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * Description:   
 * @author:     lzc  
 * @version:    1.0  
 * @date:       2019-04-18 12:49:53  
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-04-18   lzc         1.0         1.0 Version  
 */

public class PatternTest {

	@Test
	public void test() {
		Pattern pattern = Pattern.compile("\\@(\\S+)");
		Matcher matcher = pattern.matcher("werf@fred well");
		if (matcher.find()) {
			String username = matcher.group(1);
			System.out.println(username);
		}
	}
}
