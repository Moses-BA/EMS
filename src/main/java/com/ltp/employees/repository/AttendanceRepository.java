package com.ltp.employees.repository;

import org.springframework.data.repository.CrudRepository;
import com.ltp.employees.pojo.Attendance;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

public interface AttendanceRepository extends CrudRepository<Attendance, Long> {
	List<Attendance> findByEmployeeId(Long employeeId);
    List<Attendance> findByEmployeeName(String employeeName); 
    Optional<Attendance> findByDate(LocalDate date);
    Optional<Attendance> findByEmployeeIdAndDate(Long employeeId, LocalDate date);
    void deleteByEmployeeIdAndDate(Long employeeId, LocalDate date);
}