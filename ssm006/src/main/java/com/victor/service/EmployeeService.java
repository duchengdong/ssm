package com.victor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.bean.Employee;
import com.victor.dao.EmployeeMapper;

@Service
public class EmployeeService {

	@Autowired
	EmployeeMapper mapper;
	public List<Employee> getAll() {
		
		return mapper.selectByExampleWithDept(null);
	}
	
	public void saveEmp(Employee employee) {
		mapper.insertSelective(employee);
		
	}
}
