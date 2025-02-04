package com.ltp.employees.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ltp.employees.pojo.Employee;
import com.ltp.employees.pojo.SkillUpgrade;

public interface SkillUpgradeRepository extends CrudRepository<SkillUpgrade, Long> { 
    List<SkillUpgrade> findByEmployee(Employee employee);
}
