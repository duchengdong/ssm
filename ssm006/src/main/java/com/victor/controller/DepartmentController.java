package com.victor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.victor.bean.Department;
import com.victor.bean.Msg;
import com.victor.service.DepartmentService;


@Controller
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;
	
	/**
	 * @ResponseBody必须导入jackson包
	 * @param pn
	 * @return
	 */
	@RequestMapping("/depts")
	@ResponseBody
	public Msg getEmpsWithJson() {
		List<Department> emps = departmentService.getAll();
		return Msg.success().add("pageInfo",emps);
	}
}
