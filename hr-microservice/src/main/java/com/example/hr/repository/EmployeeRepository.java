package com.example.hr.repository;

import com.example.hr.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findTop10ByOrderBySalaryDesc();
    Optional<Employee> findOneByIdentity(String identity);
}
