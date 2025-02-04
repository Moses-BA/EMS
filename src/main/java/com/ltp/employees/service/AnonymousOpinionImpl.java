package com.ltp.employees.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ltp.employees.exception.EntityNotFoundException;
import com.ltp.employees.pojo.AnonymousOpinion;
import com.ltp.employees.pojo.Employee;
import com.ltp.employees.repository.AnonymousOpinionRepository;
import com.ltp.employees.repository.EmployeeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AnonymousOpinionImpl implements AnonymousOpinionService {

    private AnonymousOpinionRepository opinionRepository; 
    private EmployeeRepository employeeRepository;

    @Override
    public List<AnonymousOpinion> getAnonymousOpinion(Long employeeId) {
        return opinionRepository.findByEmployeeId(employeeId);
    }

    @Override
    public List<AnonymousOpinion> getAnonymousOpinion(String employeeName) {
        return opinionRepository.findByEmployeeName(employeeName);
    }

    @Override
    public List<AnonymousOpinion> getAnonymousOpinion() {
        return (List<AnonymousOpinion>)opinionRepository.findAll();
    }

    @Override
    public AnonymousOpinion saveAnonymousOpinion(AnonymousOpinion opinion, Long employeeId) {
        Employee employee = EmployeeServiceImpl.unwrapEmployee(employeeRepository.findById(employeeId), employeeId);
        opinion.setEmployee(employee); 
        return opinionRepository.save(opinion);
    }

    @Override
    public void deleteAnonymousOpinion(Long id) {
        opinionRepository.deleteById(id);
    }

    @Override
    public AnonymousOpinion updateAnonymousOpinion(AnonymousOpinion newOpinion, Long id) {
        AnonymousOpinion opinion = unwrapAnonymousOpinion(opinionRepository.findById(id), id);
        opinion.setTalk(newOpinion.getTalk());

        return opinionRepository.save(opinion);
    }

    static AnonymousOpinion unwrapAnonymousOpinion(Optional<AnonymousOpinion> entity, Long anonymousOpinionId) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(anonymousOpinionId, AnonymousOpinion.class);
    }
}
