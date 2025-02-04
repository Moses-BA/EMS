package com.ltp.employees;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ltp.employees.enums.UserRole;
import com.ltp.employees.pojo.Attendance;
import com.ltp.employees.pojo.Employee;
import com.ltp.employees.pojo.Task;
import com.ltp.employees.pojo.User;
import com.ltp.employees.repository.AttendanceRepository;
import com.ltp.employees.repository.EmployeeRepository;
import com.ltp.employees.repository.TaskRepository;
import com.ltp.employees.repository.UserRepository;
import com.ltp.employees.service.AttendanceService;
import com.ltp.employees.service.EmployeeService;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class EmployeeApplication implements CommandLineRunner { 

    private EmployeeRepository employeeRepository;
    private TaskRepository taskRepository;
    private AttendanceRepository attendanceRepository;
    private EmployeeService employeeService;
    private AttendanceService attendanceService;
    private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@SuppressWarnings("null")
    @Override
	public void run(String... args) throws Exception {
        Employee[] employees = new Employee[] {
            new Employee("Jon Snow", "6123456432", "agsnejdj7@gmail.com", "4th Lake street, Kubwa", "Manager"),
            new Employee("Tyrion Lannister", "69876543", "ahshdhe57@gmail.com", "4th Lake street, Gwarinpa", "HR"),
            new Employee("Carl Johnson", "645673212", "INSIGNE7@gmail.com", "4th Lake street, Wuse", "HOD"),
            new Employee("Steve Curry", "123456789", "bruhssa7@gmail.com", "4th Lake street, Lugbe", "Janitor")
        };

        for (Employee employee : employees) {
            employeeRepository.save(employee);
        }

        Attendance attendance = new Attendance(true);
        attendance.setEmployee(employeeService.getEmployee("Jon Snow"));
        attendanceRepository.save(attendance);
        List<Attendance> attends = attendanceService.getAttendance(1L);
        Attendance attend = attends.get(0);
        LocalDate date = LocalDate.parse("01/11/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        attend.setDate(date);
        attendanceRepository.save(attend);

        Task task = new Task("Backend Application", "Deliver API endpoints.", LocalDate.ofYearDay(2025, 300));
        task.setEmployee(employeeService.getEmployee("Tyrion Lannister"));
        taskRepository.save(task);

        User user = new User("mosesi", "mypassword", UserRole.USER);
        user.setEmployee(employeeService.getEmployee("Jon Snow"));
        userRepository.save(user);
	}
}
