package com.wetts.restful.demo.controller;

import com.wetts.restful.demo.bean.Employee;
import com.wetts.restful.demo.bean.EmployeeList;
import com.wetts.restful.demo.ds.EmployeeDS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class EmployeeController {

	private EmployeeDS employeeDS = new EmployeeDS();

	@RequestMapping(method=RequestMethod.GET, value="/emp/{id}", headers="Accept=application/xml, application/json")
	public @ResponseBody Employee getEmp(@PathVariable String id) {
		Employee e = employeeDS.get(Long.parseLong(id));
		return e;
	}

	@RequestMapping(method=RequestMethod.GET, value="/emps", headers="Accept=application/xml, application/json")
	public @ResponseBody EmployeeList getAllEmp() {
		List<Employee> employees = employeeDS.getAll();
		EmployeeList list = new EmployeeList(employees);
		return list;
	}

	@RequestMapping(method=RequestMethod.POST, value="/emp")
	public @ResponseBody Employee addEmp(@RequestBody Employee e) {
		employeeDS.add(e);
		return e;
	}

	@RequestMapping(method=RequestMethod.PUT, value="/emp/{id}")
	public @ResponseBody Employee updateEmp(@RequestBody Employee e, @PathVariable String id) {
		employeeDS.update(e);
		return e;
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/emp/{id}")
	public @ResponseBody void removeEmp(@PathVariable String id) {
		employeeDS.remove(Long.parseLong(id));
	}

	@RequestMapping(method=RequestMethod.GET, value="/hello")
	public String helloWorld() {
		return "index";
	}
}
