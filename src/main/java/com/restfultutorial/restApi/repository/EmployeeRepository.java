package com.restfultutorial.restApi.repository;

import com.restfultutorial.restApi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
