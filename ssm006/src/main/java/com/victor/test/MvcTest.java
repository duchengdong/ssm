package com.victor.test;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;
import com.victor.bean.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations= {"classpath:applicationContext.xml","file:src/main/webapp/springmvc.xml"})
public class MvcTest {
	//传入SpringMVC的ioc
	@Autowired
	WebApplicationContext context;
	//虚拟mvc请求，获取到处理结果
	MockMvc mockMvc;
	@Before
	public void initMokeMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		
	}
	@Test
	public void testPage() throws Exception{
		//模拟请求拿到返回值
		MvcResult result = 	mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1")).andReturn();
		//请求成功后，请求域中会有pageInfo；
		HttpServletRequest request = result.getRequest();
		PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
		List<Employee> list =  pageInfo.getList();
		for (Employee employee : list) {
			System.out.println(employee.getEmpName());
		}
		
	}
}
