package com.ltp.employees.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltp.employees.pojo.PerformanceTracking;
import com.ltp.employees.service.PerformanceTrackingService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/performance")
public class PerformanceTrackingController {
    
    @Autowired
    PerformanceTrackingService performanceService;

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<PerformanceTracking> getPerformance(@PathVariable Long employeeId) {
        return new ResponseEntity<>(performanceService.getPerformance(employeeId), HttpStatus.OK);
    }

    @PostMapping("employee/{employeeId}") //This indicates that the employeeId part of the URL path can be a list of Long values separated by commas.
    public ResponseEntity<PerformanceTracking> savePerformance(@PathVariable Long employeeId) {
        return new ResponseEntity<>(performanceService.savePerformanceTracking(employeeId), HttpStatus.CREATED);
    }

    @PutMapping("employee/{employeeId}")
    public ResponseEntity<PerformanceTracking> UpdatePerformance(@PathVariable Long employeeId) {
        return new ResponseEntity<>(performanceService.updatePerformanceTracking(employeeId), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<PerformanceTracking> UpdatePerformance(@Valid @RequestBody PerformanceTracking performanceTracking) {
        return new ResponseEntity<>(performanceService.updatePerformanceTracking(performanceTracking), HttpStatus.OK);
    }
}
