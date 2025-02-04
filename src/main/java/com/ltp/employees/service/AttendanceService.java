package com.ltp.employees.service;

import com.ltp.employees.pojo.Attendance;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {
    List<Attendance> getAttendance(Long employeeId);
    List<Attendance> getAttendance(String employeeName);
    List<Attendance> getAttendance();
    Attendance saveAttendance(Attendance attendance, Long employeeId);
    Attendance updateAttendance(Attendance attendance, Long employeeId);
    void deleteAttendance(Long employeeId, LocalDate date);
}