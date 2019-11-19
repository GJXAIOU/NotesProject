
 /**
 * FileName:     SystemPropertiesBean.java
 * Createdate:   2019-02-18 11:17:24   
 */

package com.lzc.assembly.externals.spel;

/**
 * Description:   
 * Copyright:   Copyright (c)2019    
 * @author:     LZC  
 * @version:    1.0  
 * @date:   	2019-02-18 11:17:24   
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-18   LZC         1.0         1.0 Version  
 */

public class SystemPropertiesBean {

	private String javaVersion;
	private String javaHome;

	public SystemPropertiesBean(String javaVersion, String javaHome) {
		this.javaVersion = javaVersion;
		this.javaHome = javaHome;
	}

	public String getJavaVersion() {
		return javaVersion;
	}

	public String getJavaHome() {
		return javaHome;
	}

	@Override
	public String toString() {
		return "SystemPropertiesBean [javaVersion=" + javaVersion + ", javaHome=" + javaHome + "]";
	}
	
}
