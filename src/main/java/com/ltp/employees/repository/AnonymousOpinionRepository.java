package com.ltp.employees.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ltp.employees.pojo.AnonymousOpinion;

public interface AnonymousOpinionRepository extends CrudRepository<AnonymousOpinion, Long> {
	List<AnonymousOpinion> findByEmployeeId(Long employeeId);
    List<AnonymousOpinion> findByEmployeeName(String employeeName);
}
