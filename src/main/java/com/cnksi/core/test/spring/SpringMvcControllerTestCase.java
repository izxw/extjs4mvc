package com.cnksi.core.test.spring;

import org.junit.Before;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;

/**
 * Spring的支持依赖注入的JUnit4 集成测试基类, 相比Spring原基类名字更短.
 * 
 * 子类需要定义applicationContext文件的位置,如:
 * @ContextConfiguration(locations = { "/applicationContext.xml" })
 * 
 * @author Joe
 */
@ActiveProfiles("test")
@ContextConfiguration(locations = { "classpath:/spring-mvc.xml", "classpath:/applicationContext-test.xml" })
public class SpringMvcControllerTestCase
{

	protected MockMvc mockMvc;

	@Before
	public void setup()
	{

		mockMvc = MockMvcBuilders.xmlConfigSetup("classpath:/spring-mvc.xml", "classpath:/applicationContext-test.xml").build();

	}

}
