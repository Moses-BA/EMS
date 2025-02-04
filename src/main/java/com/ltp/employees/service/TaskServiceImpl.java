package com.ltp.employees.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ltp.employees.exception.EntityNotFoundException;
import com.ltp.employees.pojo.Employee;
import com.ltp.employees.pojo.Task;
import com.ltp.employees.pojo.Task.TaskStatus;
import com.ltp.employees.repository.EmployeeRepository;
import com.ltp.employees.repository.TaskRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private EmployeeRepository employeeRepository;

    @Override
    public List<Task> getTask(Long employeeId) {
        return taskRepository.findByEmployeeId(employeeId);
    }

    @Override
    public List<Task> getTask(String employeeName) {
        return taskRepository.findByEmployeeName(employeeName);
    }

    @Override
    public List<Task> getTask() {
        return (List<Task>)taskRepository.findAll();
    }

    @Override
    public Task saveTask(Task task, Long employeeId) {
        Employee employee = EmployeeServiceImpl.unwrapEmployee(employeeRepository.findById(employeeId), employeeId);
        task.setEmployee(employee); 
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
	
    static Task unwrapTask(Optional<Task> entity, Long taskId) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(taskId, Task.class);
    }

    @Override
    public Task updateTask(Task newTask, Long id) {
        Task task = unwrapTask(taskRepository.findById(id), id);
        task.setTitle(newTask.getTitle());
        task.setDescription(newTask.getDescription());
        task.setStatus(newTask.getStatus());
        task.setDueDate(newTask.getDueDate());

        return taskRepository.save(task);
    }

    @Override
    public Task updateTaskStatus(Long id) {
        Task task = unwrapTask(taskRepository.findById(id), id);
        task.setStatus(TaskStatus.COMPLETED);
        return taskRepository.save(task);
    }
}
