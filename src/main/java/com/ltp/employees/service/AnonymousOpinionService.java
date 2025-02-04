package com.ltp.employees.service;

import java.util.List;

import com.ltp.employees.pojo.AnonymousOpinion;

public interface AnonymousOpinionService {
    List<AnonymousOpinion> getAnonymousOpinion(Long employeeId);
    List<AnonymousOpinion> getAnonymousOpinion(String employeeName);
    List<AnonymousOpinion> getAnonymousOpinion();
    AnonymousOpinion saveAnonymousOpinion(AnonymousOpinion anonymousOpinion, Long employeeId);
    AnonymousOpinion updateAnonymousOpinion(AnonymousOpinion anonymousOpinion, Long id);
    void deleteAnonymousOpinion(Long id); 
}
