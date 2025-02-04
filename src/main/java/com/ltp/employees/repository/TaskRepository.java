package com.ltp.employees.repository;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

import com.ltp.employees.pojo.Task;
import com.ltp.employees.pojo.Task.TaskStatus;

public interface TaskRepository extends CrudRepository<Task, Long> {
	List<Task> findByEmployeeId(Long employeeId);
    List<Task> findByEmployeeName(String employeeName);
    Integer countByEmployeeId(long id);
    Integer countByEmployeeIdAndStatus(Long employeeId, TaskStatus status);
}