package com.example.hr.repository;

import com.example.hr.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee,String> {
    List<Employee> findTop10ByOrderBySalaryDesc();
    Optional<Employee> findOneByIdentity(String identity);
}
