package com.example.hr.controller;

import com.example.hr.dto.EmployeeUpdateRequest;
import com.example.hr.entity.Employee;
import com.example.hr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
// swagger ui
// http://localhost:7001/hr/api/v1/swagger-ui.html
@RestController
@RequestScope
// http://localhost:7001/hr/api/v1/employees
@RequestMapping("employees")
@CrossOrigin
public class EmployeeRestController {
    @Autowired
    private EmployeeService empSrv;

    @PutMapping("{identity}")
    public Employee updateEmployee(
            @RequestBody EmployeeUpdateRequest employee,
            @PathVariable String identity){
        return empSrv.updateEmployee(identity,employee);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee emp){
        return empSrv.addEmployee(emp);
    }

    // http://localhost:7001/hr/api/v1/employees?page=10&size=25
    @GetMapping
    public List<Employee> getEmployees(@RequestParam int page,@RequestParam int size){
        return empSrv.findEmployees(page,size);
    }

    // http://localhost:7001/hr/api/v1/employees/12345678910
    @GetMapping("{identity}")
    public Employee getEmployeeByIdentity(@PathVariable String identity){
        return empSrv.findEmpByIdentity(identity);
    }
}
