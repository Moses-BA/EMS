package com.ltp.employees.service;

import com.ltp.employees.pojo.PerformanceTracking;

public interface PerformanceTrackingService {
    PerformanceTracking savePerformanceTracking(Long employeeId);
    PerformanceTracking updatePerformanceTracking(Long employeeId);
    PerformanceTracking updatePerformanceTracking(PerformanceTracking performanceTracking);
    PerformanceTracking getPerformance(Long employeeId);
    PerformanceTracking getPerformance(String employeeName);
}
