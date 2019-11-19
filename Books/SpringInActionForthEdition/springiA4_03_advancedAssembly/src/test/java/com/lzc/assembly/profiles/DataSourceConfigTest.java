
 /**
 * FileName:     DataSourceConfigTest.java
 * Createdate:   2019-02-15 16:44:19   
 */

package com.lzc.assembly.profiles;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Description:   
 * Copyright:   Copyright (c)2019    
 * @author:     LZC  
 * @version:    1.0  
 * @date:   	2019-02-15 16:44:19   
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-15   LZC         1.0         1.0 Version  
 */
public class DataSourceConfigTest {
	
	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration(classes=DataSourceConfig.class)
	@ActiveProfiles("dev")
	public static class DevDataSourceConfigTest {
		@Autowired
		private DataSource dataSource;
		
		@Test
		public void shouldBeEmbeddedDatasource() {
			assertNotNull(dataSource);
			
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			List<String> results = jdbcTemplate.query("SELECT t.id,t.name From Things t", new RowMapper<String>() {
				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getLong("id") + ":" + rs.getString("name");
				}
			});
			assertEquals(1, results.size());
			assertEquals("1:A", results.get(0));
		}
	}
	
	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration(classes=DataSourceConfig.class)
	@ActiveProfiles("prod")
	public static class ProductDataSourceConfigTest {
		
		@Autowired
		private DataSource dataSource;
		
		@Test
		public void testProductDataSource() {
			// should be null, because there isn't a datasource configured in JNDI
			assertNotNull(dataSource);
		}
	}
	
	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration("classpath:profiles/datasource-config.xml")
	@ActiveProfiles("dev")
	public static class DevDataSourceXmlConfigTest {
		@Autowired
		private DataSource dataSource;
		
		@Test
		public void shouldBeEmbeddedDatasource() {
			assertNotNull(dataSource);
			
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			List<String> results = jdbcTemplate.query("SELECT t.id,t.name From Things t", new RowMapper<String>() {
				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getLong("id") + ":" + rs.getString("name");
				}
			});
			assertEquals(1, results.size());
			assertEquals("1:A", results.get(0));
		}
	}
	
	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration("classpath:profiles/datasource-config.xml")
	@ActiveProfiles("prod")
	public static class ProductDataSourceXmlConfigTest {
		
		@Autowired
		private DataSource dataSource;
		
		@Test
		public void testProductDataSource() {
			//报错
			// should be null, because there isn't a datasource configured in JNDI
			assertNotNull(dataSource);
		}
	}

}
