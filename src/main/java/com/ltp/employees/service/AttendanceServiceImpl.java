package com.ltp.employees.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ltp.employees.exception.EntityNotFoundException;
import com.ltp.employees.pojo.Attendance;
import com.ltp.employees.pojo.Employee;
import com.ltp.employees.repository.EmployeeRepository;
import com.ltp.employees.repository.AttendanceRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private AttendanceRepository attendanceRepository;
    private EmployeeRepository employeeRepository;

    @Override
    public List<Attendance> getAttendance(Long employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }

    @Override
    public List<Attendance> getAttendance(String employeeName) {
        return attendanceRepository.findByEmployeeName(employeeName);
    }

    @Override
    public List<Attendance> getAttendance() {
        return (List<Attendance>)attendanceRepository.findAll();
    }

    @Override
    public Attendance saveAttendance(Attendance attendance, Long employeeId) {
        Employee employee = EmployeeServiceImpl.unwrapEmployee(employeeRepository.findById(employeeId), employeeId);
        attendance.setEmployee(employee); 
        return attendanceRepository.save(attendance);
    }

    @Override
    public void deleteAttendance(Long employeeId, LocalDate date) {
        attendanceRepository.deleteByEmployeeIdAndDate(employeeId, date);
    }
	
    static Attendance unwrapAttendance(Optional<Attendance> entity, Long employeeId) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(employeeId, Attendance.class);
    }

    @Override
    public Attendance updateAttendance(Attendance newAttendance, Long employeeId) {
        Attendance attendance = AttendanceServiceImpl.unwrapAttendance(attendanceRepository.findByDate(LocalDate.now()), employeeId);
        attendance.setIsPresent(newAttendance.getIsPresent());
        return attendanceRepository.save(attendance);
    }
}
