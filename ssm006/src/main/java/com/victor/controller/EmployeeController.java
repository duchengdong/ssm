package com.victor.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.victor.bean.Employee;
import com.victor.bean.Msg;
import com.victor.service.EmployeeService;

@Controller
//@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	/**
	 * @ResponseBody必须导入jackson包
	 * @param pn
	 * @return
	 */
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value="pn",defaultValue="1") Integer pn) {
		PageHelper.startPage(pn, 5);
		List<Employee> emps = employeeService.getAll();
		PageInfo page =  new PageInfo(emps,5);
		return Msg.success().add("pageInfo",page);
	}
	 

	/**
	 * 查询员工数据（分页 ）
	 * 
	 * @return
	 */
//	@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn",defaultValue="1") Integer pn,Model model) {
		
		PageHelper.startPage(pn, 5);
		List<Employee> emps = employeeService.getAll();
		PageInfo page =  new PageInfo(emps,5);
		model.addAttribute("pageInfo", page);
		return "list";

	}
}
