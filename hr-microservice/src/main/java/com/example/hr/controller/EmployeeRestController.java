package com.example.hr.controller;

import com.example.hr.dto.EmployeeUpdateRequest;
import com.example.hr.entity.Employee;
import com.example.hr.service.EmployeeService;
import com.example.hr.validation.TcKimlikNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

// swagger ui
// http://localhost:7001/hr/api/v1/swagger-ui.html
@RestController
@RequestScope
// http://localhost:7001/hr/api/v1/employees
@RequestMapping("employees")
@CrossOrigin
@Validated
public class EmployeeRestController {
    @Autowired
    private EmployeeService empSrv;

    @PutMapping("{identity}")
    public Employee updateEmployee(@RequestBody @Validated EmployeeUpdateRequest employee, @PathVariable @TcKimlikNo String identity){ return empSrv.updateEmployee(identity,employee); }

    @PatchMapping("{identity}")
    public Employee patchEmployee(@RequestBody Map<String,Object> request, @PathVariable  @TcKimlikNo String identity){ return empSrv.patchEmployee(identity,request); }

    @PostMapping
    public Employee createEmployee(@RequestBody @Validated Employee emp){
        return empSrv.addEmployee(emp);
    }

    @DeleteMapping("{identity}")
    public Employee removeEmployeeByIdentity(@PathVariable  @TcKimlikNo String identity){ return empSrv.deleteEmpByIdentity(identity); }

    @GetMapping
    public List<Employee> getEmployees(@RequestParam @Min(0) int page, @RequestParam @Max(25) int size){ return empSrv.findEmployees(page,size); }

    @GetMapping("{identity}")
    public Employee getEmployeeByIdentity(@PathVariable @TcKimlikNo String identity){ return empSrv.findEmpByIdentity(identity); }

}
