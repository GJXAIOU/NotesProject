package com.lzc.assembly.externals;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lzc.assembly.externals.placeholder.BlankDiscPlaceholder;
import com.lzc.assembly.externals.placeholder.PlaceholderConfig;
import com.lzc.assembly.externals.spel.BlankDiscSpEL;
import com.lzc.assembly.externals.spel.SpELConfig;
import com.lzc.assembly.externals.spel.SystemPropertiesBean;

public class EnvironmentInjectionTest {

	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration(classes = EnvironmentConfig.class)
	public static class InjectFromProperties {

		@Autowired
		private BlankDisc blankDisc;

		@Test
		public void assertBlankDiscProperties() {
			assertEquals("周杰伦", blankDisc.getArtist());
			assertEquals("夜曲", blankDisc.getTitle());
		}

	}

	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration(classes = EnvironmentConfigWithDefaults.class)
	public static class InjectFromPropertiesWithDefaultValues {

		@Autowired
		private BlankDisc blankDisc;

		@Test
		public void assertBlankDiscProperties() {
			assertEquals("林俊杰", blankDisc.getArtist());
			assertEquals("学不会", blankDisc.getTitle());
		}

	}

	public static class InjectFromPropertiesWithRequiredProperties {

		@Test(expected = BeanCreationException.class)
		public void assertBlankDiscProperties() {
			//由于没有配置资源文件，也没有设置环境默认值，故创建bean失败
			new AnnotationConfigApplicationContext(EnvironmentConfigWithRequiredProperties.class);
		}

	}

	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration("classpath:externals/placeholder-config.xml")
	public static class InjectFromProperties_XMLConfig {

		@Autowired
		private BlankDisc blankDisc;

		@Test
		public void assertBlankDiscProperties() {
			assertEquals("周杰伦", blankDisc.getArtist());
			assertEquals("夜曲", blankDisc.getTitle());
		}
	}
	
	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration(classes = PlaceholderConfig.class)
	public static class InjectFromPropertiesWithPlaceholder {

//		@Autowired
//		private BlankDisc blankDisc;
		@Autowired
		private BlankDiscPlaceholder blankDisc;

		@Test
		public void assertBlankDiscProperties() {
			assertEquals("周杰伦", blankDisc.getArtist());
			assertEquals("夜曲", blankDisc.getTitle());
		}

	}
	
	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration(classes = SpELConfig.class)
	public static class InjectFromPropertiesWithSpEL {

//		@Autowired
//		private BlankDisc blankDisc1;
		@Autowired
		@Qualifier("blankDiscSpEL1")
		private BlankDiscSpEL blankDisc1;
		@Autowired
		@Qualifier("blankDiscSpEL2")
		private BlankDiscSpEL blankDisc2;
		@Autowired
		private SystemPropertiesBean systemPropsBean;

		@Test
		public void assertBlankDiscProperties() {
			assertEquals("林俊杰", blankDisc1.getArtist());
			assertEquals("学不会", blankDisc1.getTitle());
			//加载单个属性文件时
			assertEquals("周杰伦", blankDisc2.getArtist());
			assertEquals("夜曲", blankDisc2.getTitle());
			//加载多个属性文件时，后加载的覆盖已加载的同名属性
//			assertEquals("林俊杰", blankDisc2.getArtist());
//			assertEquals("那些你很冒险的梦", blankDisc2.getTitle());
			System.out.println(systemPropsBean.toString());
		}

	}

}