package com.ltp.employees.service;

import com.ltp.employees.pojo.Employee;
import java.util.List;

public interface EmployeeService {
    Employee getEmployee(Long id);
    Employee getEmployee(String name);
    List<Employee> getEmployee();
    Employee saveEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee employee);
    Employee updateLastPaidDate(Long id);
    void deleteEmployee(Long id);
} 
