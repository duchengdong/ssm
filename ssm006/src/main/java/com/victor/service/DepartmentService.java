package com.victor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.bean.Department;
import com.victor.dao.DepartmentMapper;



@Service
public class DepartmentService {
	@Autowired
	DepartmentMapper mapper;
	public List<Department> getAll() {
		
		return mapper.selectByExample(null);
	}
}
