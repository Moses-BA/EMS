package com.ltp.employees.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltp.employees.pojo.Task;
import com.ltp.employees.service.TaskService;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/task")
public class TaskController {
    
    @Autowired
    TaskService taskService;
    
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Task>> getTask(@PathVariable Long employeeId) {
        return new ResponseEntity<>(taskService.getTask(employeeId), HttpStatus.OK);
    }

    @PostMapping("employee/{employeeId}") //This indicates that the employeeId part of the URL path can be a list of Long values separated by commas.
    public ResponseEntity<Task> saveTask(@Valid @RequestBody Task task, @PathVariable Long employeeId) {
        return new ResponseEntity<>(taskService.saveTask(task, employeeId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> UpdateTask(@Valid @RequestBody Task task, @PathVariable Long id) {
        return new ResponseEntity<>(taskService.updateTask(task, id), HttpStatus.OK);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Task> UpdateTaskStatus(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.updateTaskStatus(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getTask() {
        return new ResponseEntity<>(taskService.getTask(), HttpStatus.OK);
    }
    
}
