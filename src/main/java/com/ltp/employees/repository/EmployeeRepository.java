package com.ltp.employees.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ltp.employees.pojo.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	Optional<Employee> findByName(String name);
}