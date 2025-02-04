package com.ltp.employees.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ltp.employees.exception.EntityNotFoundException;
import com.ltp.employees.pojo.Employee;
import com.ltp.employees.repository.EmployeeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return unwrapEmployee(employee, id);
    }

    @Override
    public Employee getEmployee(String name) {
        Optional<Employee> employee = employeeRepository.findByName(name);
        return unwrapEmployee(employee, 404L);
    }

    @Override
    public List<Employee> getEmployee() {
        return (List<Employee>)employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    } 

    @Override
    public Employee updateEmployee(Long id, Employee newEmployee) {
        Optional<Employee> employee = employeeRepository.findById(id);
        Employee unwrappedEm = unwrapEmployee(employee, id);
        unwrappedEm.setName(newEmployee.getName());
        unwrappedEm.setAddress(newEmployee.getAddress());
        unwrappedEm.setEmail(newEmployee.getEmail());
        unwrappedEm.setJob(newEmployee.getJob());
        unwrappedEm.setPhoneNumber(newEmployee.getPhoneNumber());

        return employeeRepository.save(unwrappedEm);
    }

    @Override
    public Employee updateLastPaidDate(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        Employee unwrappedEm = unwrapEmployee(employee, id);
        unwrappedEm.setLastPaidDate(LocalDate.now());
        
        return employeeRepository.save(unwrappedEm);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    static Employee unwrapEmployee(Optional<Employee> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Employee.class);
    }
    
}
