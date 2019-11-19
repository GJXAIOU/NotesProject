 /**
 * FileName:     DataSourceConfig.java
 * Createdate:   2019-02-14 17:03:21   
 */

package com.lzc.assembly.profiles;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;

/**
 * Description: 数据源配置  
 * Copyright:   Copyright (c)2019    
 * @author:     LZC  
 * @version:    1.0  
 * @date:   	2019-02-14 17:03:21   
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-14   LZC         1.0         1.0 Version  
 */
@Configuration
public class DataSourceConfig {

	/**
	* <p>描述：嵌入式数据源；开发环境</p>
	* @return
	* @author LZC
	* @date   2019-02-14 17:37
	 */
	@Bean(destroyMethod="shutdown")
	@Profile("dev")
	public DataSource embeddedDataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("classpath:profiles/schema.sql")
				.addScript("classpath:profiles/test-data.sql")
				.build();
	}
	
	/**
	* <p>描述：jndi数据源；生产环境</p>
	* @return
	* @author LZC
	* @date   2019-02-14 17:38
	 */
	@Bean
	@Profile("prod")
	public DataSource jndiDataSource() {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiName("jdbc/myDS");
		jndiObjectFactoryBean.setResourceRef(true);
		jndiObjectFactoryBean.setProxyInterface(DataSource.class);
		return (DataSource) jndiObjectFactoryBean.getObject();
	}
}
