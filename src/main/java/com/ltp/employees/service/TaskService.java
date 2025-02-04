package com.ltp.employees.service;

import com.ltp.employees.pojo.Task;
import java.util.List;

public interface TaskService {
    List<Task> getTask(Long employeeId);
    List<Task> getTask(String employeeName);
    List<Task> getTask();
    Task saveTask(Task task, Long employeeId);
    Task updateTask(Task task, Long id);
    Task updateTaskStatus(Long id);
    void deleteTask(Long id); 
}