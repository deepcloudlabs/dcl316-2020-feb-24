package com.example.hr.service;

import com.example.hr.dto.EmployeeUpdateRequest;
import com.example.hr.entity.Department;
import com.example.hr.entity.Employee;
import com.example.hr.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class EmployeeService {
    private static final String UPDATABLE_FIELDS[] = {"salary", "photo", "iban", "department"};

    @Autowired
    private EmployeeRepository empRepo;

    public Employee findEmpByIdentity(String identity) {
        return empRepo.findOneByIdentity(identity).orElseThrow(() -> new IllegalArgumentException("Cannot find employee!"));
    }

    public List<Employee> findEmployees(int page, int size) {
        return empRepo.findAll(PageRequest.of(page, size)).getContent();
    }

    public Employee updateEmployee(String identity, EmployeeUpdateRequest employee) {
        Optional<Employee> emp = empRepo.findOneByIdentity(identity);
        if (!emp.isPresent()) throw new IllegalArgumentException("Cannot find employee to update!");
        Employee managed = emp.get();
        managed.setIban(employee.getIban());
        managed.setSalary(employee.getSalary());
        managed.setPhoto(employee.getPhoto());
        managed.setFulltime(employee.isFulltime());
        managed.setDepartment(employee.getDepartment());
        empRepo.save(managed);
        return managed;
    }

    public Employee addEmployee(Employee emp) {
        Optional<Employee> employee = empRepo.findOneByIdentity(emp.getIdentity());
        if (employee.isPresent()) throw new IllegalArgumentException("Employee already exists!");
        empRepo.save(emp);
        return emp;
    }

    public Employee deleteEmpByIdentity(String identity) {
        Optional<Employee> emp = empRepo.findOneByIdentity(identity);
        if (!emp.isPresent())
            throw new IllegalArgumentException("Cannot find employee to delete!");
        Employee employee = emp.get();
        empRepo.delete(employee);
        return employee;
    }

    public Employee patchEmployee(String identity, Map<String, Object> request) {
        if (request.containsKey("department")) request.put("department",Department.valueOf(request.get("department").toString()));
        Optional<Employee> emp = empRepo.findOneByIdentity(identity);
        if (!emp.isPresent()) throw new IllegalArgumentException("Cannot find employee to patch!");
        Employee managed = emp.get();
        Arrays.stream(UPDATABLE_FIELDS).forEach(field -> {
            Object value = request.get(field);
            if (Objects.isNull(value)) return;
            try {
                Field managedField = managed.getClass().getDeclaredField(field);
                managedField.setAccessible(true);
                managedField.set(managed, value);
                managedField.setAccessible(false);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        });
        return managed;
    }
}






