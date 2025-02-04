package com.ltp.employees.repository;

import org.springframework.data.repository.CrudRepository;

import com.ltp.employees.pojo.PerformanceTracking;
import java.util.Optional;

public interface PerformanceTrackingRepository extends CrudRepository<PerformanceTracking, Long> {
    Optional<PerformanceTracking> findByEmployeeId(Long employeeId);
    Optional<PerformanceTracking> findByEmployeeName(String employeeName);
    
}
