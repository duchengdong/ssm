package com.victor.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.victor.bean.Employee;
import com.victor.dao.DepartmentMapper;
import com.victor.dao.EmployeeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class MapperTest {
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	SqlSession sqlSession;
	
	@Test
	public void testCRUD() {
		
		System.out.println(departmentMapper);
//		departmentMapper.insertSelective(new Department(null,"测试部"));
//		
//		Department department = departmentMapper.selectByPrimaryKey(2);
//		System.out.println(department.getDeptName());

//		Employee employee = new Employee(1, "victor", "m", "victor@victor.com", 5);
//		employeeMapper.insertSelective(employee);
		
		EmployeeMapper mapper =  sqlSession.getMapper(EmployeeMapper.class);
		for (int i = 2; i < 1000; i++) {
			Employee employee = new Employee(i, "victor"+i, "m", "victor"+i+"@victor.com", 5);
			mapper.insertSelective(employee);
		}
	}
}
