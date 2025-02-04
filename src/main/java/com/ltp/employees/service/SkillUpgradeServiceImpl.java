package com.ltp.employees.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ltp.employees.exception.EntityNotFoundException;
import com.ltp.employees.pojo.Employee;
import com.ltp.employees.pojo.SkillUpgrade;
import com.ltp.employees.repository.EmployeeRepository;
import com.ltp.employees.repository.SkillUpgradeRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class SkillUpgradeServiceImpl implements SkillUpgradeService {

    private SkillUpgradeRepository skillRepository; 
    private EmployeeRepository employeeRepository;

    @Override
    public List<SkillUpgrade> getSkillUpgrade(Long employeeId) {
        Employee employee = EmployeeServiceImpl.unwrapEmployee(employeeRepository.findById(employeeId), employeeId);
        return skillRepository.findByEmployee(employee);
    }

    @Override
    public List<SkillUpgrade> getSkillUpgrade(String employeeName) {
        Employee employee = EmployeeServiceImpl.unwrapEmployee(employeeRepository.findByName(employeeName), 404L);
        return skillRepository.findByEmployee(employee);
    }

    @Override
    public List<SkillUpgrade> getSkillUpgrades() {
        return (List<SkillUpgrade>)skillRepository.findAll();
    }

    @SuppressWarnings("null")
    @Override
    public SkillUpgrade saveSkillUpgrade(SkillUpgrade skill, Long employeeId) {
        Employee employee = EmployeeServiceImpl.unwrapEmployee(employeeRepository.findById(employeeId), employeeId);
        if (skill.getEmployee() != null) {
            skill.getEmployee().add(employee);
        } else {
            List<Employee> employees = new ArrayList<>();
            employees.add(employee);
            skill.setEmployee(employees);
        }

        return skillRepository.save(skill);
    }

    @SuppressWarnings("null")
    @Override
    public SkillUpgrade saveGeneralSkillUpgrade(SkillUpgrade skill) {
        List<Employee> employeesCopy = (List<Employee>) employeeRepository.findAll();
        List<Employee> employees = List.copyOf(employeesCopy);  //To avoid Concurrent Modification Exception
        for (Employee employee : employees) {
            List<SkillUpgrade> updatedSkills = new ArrayList<>(employee.getSkills());
            updatedSkills.add(skill);
            employee.setSkills(updatedSkills);

            if (skill.getEmployee() != null) {
                skill.getEmployee().add(employee);
            } else {
                List<Employee> employees1 = new ArrayList<>();
                employees1.add(employee);
                skill.setEmployee(employees1);
            }
        }

        return skillRepository.save(skill);
    }

    @Override
    public SkillUpgrade updateSkillUpgrade(SkillUpgrade newSkill, Long id) {
        SkillUpgrade skill = unwrapSkillUpgrade(skillRepository.findById(id), id);
        skill.setSkill(newSkill.getSkill());
        skill.setReason(newSkill.getReason());

        return skillRepository.save(skill);
    }

    @Override
    public void deleteSkillUpgrade(Long id) {
        skillRepository.deleteById(id);
    }

    static SkillUpgrade unwrapSkillUpgrade(Optional<SkillUpgrade> entity, Long SkillUpgradeId) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(SkillUpgradeId, SkillUpgrade.class);
    }
}
