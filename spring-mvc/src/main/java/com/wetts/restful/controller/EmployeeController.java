package com.wetts.restful.controller;

import com.wetts.restful.bean.Employee;
import com.wetts.restful.bean.EmployeeList;
import com.wetts.restful.ds.EmployeeDS;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
// 该注解，用在类上，说明该类的作用
@Api(value = "/api", description = "用户的相关操作")
public class EmployeeController {

    private EmployeeDS employeeDS = new EmployeeDS();

    // 该注解，用在方法上，说明该类的作用
    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    // 用在方法上包含一个参数说明
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType="path", defaultValue="1")
    @RequestMapping(method = RequestMethod.GET, value = "/emp/{id}", headers = "Accept=application/xml, application/json")
    public Employee getEmp(@PathVariable(value = "id") String id) {
        Employee e = employeeDS.get(Long.parseLong(id));
        return e;
    }

    @ApiOperation(value = "获取用户列表", notes = "")
    @RequestMapping(method = RequestMethod.GET, value = "/emps", headers = "Accept=application/xml, application/json")
    public EmployeeList getAllEmp() {
        List<Employee> employees = employeeDS.getAll();
        EmployeeList list = new EmployeeList(employees);
        return list;
    }

    @ApiOperation(value = "创建用户", notes = "根据Emp对象创建用户")
    @RequestMapping(method = RequestMethod.POST, value = "/emp")
    public Employee addEmp(@RequestBody Employee e) {
        employeeDS.add(e);
        return e;
    }

    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的emp信息来更新用户详细信息")
    // 用在方法上包含一组参数说明
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType="path"),
            @ApiImplicitParam(name = "e", value = "用户详细实体emp", required = true, dataType = "Employee")})
    @RequestMapping(method = RequestMethod.PUT, value = "/emp/{id}")
    public Employee updateEmp(@RequestBody Employee e, @PathVariable(value = "id") String id) {
        employeeDS.update(e);
        return e;
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @RequestMapping(method = RequestMethod.DELETE, value = "/emp/{id}")
    public void removeEmp(@PathVariable(value = "id") String id) {
        employeeDS.remove(Long.parseLong(id));
    }

}
