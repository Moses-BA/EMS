package com.ltp.employees.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.ltp.employees.pojo.Attendance;
import com.ltp.employees.service.AttendanceService;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    
    @Autowired
    AttendanceService attendanceService;
    
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Attendance>> getAttendance(@PathVariable Long employeeId) {
        return new ResponseEntity<>(attendanceService.getAttendance(employeeId), HttpStatus.OK);
    }

    @PostMapping("employee/{employeeId}")
    public ResponseEntity<Attendance> saveAttendance(@Valid @RequestBody Attendance attendance, @PathVariable Long employeeId) {
        return new ResponseEntity<>(attendanceService.saveAttendance(attendance, employeeId), HttpStatus.CREATED);
    }

    @PutMapping("employee/{employeeId}") //Not in use
    public ResponseEntity<Attendance> UpdateAttendance(@Valid @RequestBody Attendance attendance, @PathVariable Long employeeId) {
        return new ResponseEntity<>(attendanceService.updateAttendance(attendance, employeeId), HttpStatus.OK);
    }

    @DeleteMapping("employee/{employeeId}/{date}")
    public ResponseEntity<HttpStatus> deleteAttendance(@PathVariable Long employeeId, @PathVariable @DateTimeFormat(pattern="yyyy/MM/dd") LocalDate date) {
        attendanceService.deleteAttendance(employeeId, date);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
