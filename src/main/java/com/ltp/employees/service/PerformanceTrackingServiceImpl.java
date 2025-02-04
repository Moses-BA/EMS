package com.ltp.employees.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltp.employees.exception.EntityNotFoundException;
import com.ltp.employees.pojo.Employee;
import com.ltp.employees.pojo.PerformanceTracking;
import com.ltp.employees.pojo.Task.TaskStatus;
import com.ltp.employees.repository.PerformanceTrackingRepository;
import com.ltp.employees.repository.TaskRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PerformanceTrackingServiceImpl implements PerformanceTrackingService {

    @Autowired
    private PerformanceTrackingRepository performanceTrackingRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public PerformanceTracking savePerformanceTracking(Long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId); 

        Optional<PerformanceTracking> existingTracking = performanceTrackingRepository.findByEmployeeId(employeeId); // Check if a performance tracking record already exists

        if (existingTracking.isPresent()) {
            throw new IllegalArgumentException("Performance tracking already exists for employee: " + employee.getName());
        }

        PerformanceTracking performanceTracking = new PerformanceTracking(); // Create a new performance tracking record
        performanceTracking.setEmployee(employee);
        calculatePerformanceMetrics(performanceTracking);                     // Calculate total tasks assigned and tasks completed

        return performanceTrackingRepository.save(performanceTracking); // Save the new performance tracking record
    }

    @Override
    public PerformanceTracking updatePerformanceTracking(Long employeeId) {
        Optional<PerformanceTracking> existingTracking = performanceTrackingRepository.findByEmployeeId(employeeId);

        PerformanceTracking performanceTracking = unwrapPerformance(existingTracking, 404L); // Update the existing performance tracking record
        calculatePerformanceMetrics(performanceTracking);  // Recalculate total tasks assigned and tasks completed (in case tasks have changed)

        return performanceTrackingRepository.save(performanceTracking); // Save the updated performance tracking record
    }

    @Override
    public PerformanceTracking updatePerformanceTracking(PerformanceTracking performanceTracking) {
        Optional<PerformanceTracking> existingTracking = performanceTrackingRepository.findById(performanceTracking.getId());
        PerformanceTracking existingPerformanceTracking = unwrapPerformance(existingTracking, performanceTracking.getId());
        existingPerformanceTracking.setTotalTasksAssigned(performanceTracking.getTotalTasksAssigned());
        existingPerformanceTracking.setTasksCompleted(performanceTracking.getTasksCompleted());

        if (existingPerformanceTracking.getTotalTasksAssigned() > 0) {   // Recalculate completion percentage
            if (existingPerformanceTracking.getTasksCompleted() == 0) {
                existingPerformanceTracking.setCompletionPercentage(0); // Handle case where tasksCompleted is zero
            } else {
                existingPerformanceTracking.setCompletionPercentage((int) Math.round((double) existingPerformanceTracking.getTasksCompleted() / existingPerformanceTracking.getTotalTasksAssigned() * 100)); 
            }
        } else {
            existingPerformanceTracking.setCompletionPercentage(0);
        }

        return performanceTrackingRepository.save(existingPerformanceTracking);

    }

    private void calculatePerformanceMetrics(PerformanceTracking performanceTracking) {
        // Calculate total tasks assigned
        Integer totalTasksAssigned = taskRepository.countByEmployeeId(performanceTracking.getEmployee().getId());
        performanceTracking.setTotalTasksAssigned(totalTasksAssigned);

        // Calculate tasks completed (you might need to define a "completed" status in your Task entity)
        Integer tasksCompleted = taskRepository.countByEmployeeIdAndStatus(performanceTracking.getEmployee().getId(), TaskStatus.COMPLETED);
        performanceTracking.setTasksCompleted(tasksCompleted);

        // Calculate completion percentage
        if (totalTasksAssigned > 0) {
            if (tasksCompleted == 0) {
                performanceTracking.setCompletionPercentage(0); // Handle case where tasksCompleted is zero
            } else {
                performanceTracking.setCompletionPercentage((int) Math.round((double) tasksCompleted / totalTasksAssigned * 100)); 
            }
        } else {
            performanceTracking.setCompletionPercentage(100);
        }
    }

    @Override
    public PerformanceTracking getPerformance(Long employeeId) {
        return unwrapPerformance(performanceTrackingRepository.findByEmployeeId(employeeId), 404L);
    }

    @Override
    public PerformanceTracking getPerformance(String employeeName) {
        return unwrapPerformance(performanceTrackingRepository.findByEmployeeName(employeeName), 404L);
    }

    static PerformanceTracking unwrapPerformance(Optional<PerformanceTracking> entity, Long performanceId) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(performanceId, PerformanceTracking.class);
    }
}
