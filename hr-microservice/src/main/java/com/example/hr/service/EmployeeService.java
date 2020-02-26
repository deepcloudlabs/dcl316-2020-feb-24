package com.example.hr.service;

import com.example.hr.entity.Employee;
import com.example.hr.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired private EmployeeRepository empRepo;

    public Employee findEmpByIdentity(String identity) {
        return empRepo.findOneByIdentity(identity)
                .orElseThrow(
                    () -> new IllegalArgumentException(
                            "Cannot find employee!"));
    }

    public List<Employee> findEmployees(int page, int size) {
         return empRepo.findAll(PageRequest.of(page,size))
                 .getContent();
    }

    @Transactional
    public Employee addEmployee(Employee emp) {
        Optional<Employee> employee =
                empRepo.findOneByIdentity(emp.getIdentity());
        if (employee.isPresent())
            throw new IllegalArgumentException("Employee already exists!");
        empRepo.save(emp);
        return emp;
    }
}






