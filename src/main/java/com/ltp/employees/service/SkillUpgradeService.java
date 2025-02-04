package com.ltp.employees.service;

import java.util.List;

import com.ltp.employees.pojo.SkillUpgrade;

public interface SkillUpgradeService {
    List<SkillUpgrade> getSkillUpgrade(Long employeeId);
    List<SkillUpgrade> getSkillUpgrade(String employeeName);
    List<SkillUpgrade> getSkillUpgrades();
    SkillUpgrade saveSkillUpgrade(SkillUpgrade skill, Long employeeId);
    SkillUpgrade saveGeneralSkillUpgrade(SkillUpgrade skill);
    SkillUpgrade updateSkillUpgrade(SkillUpgrade skill, Long id);
    void deleteSkillUpgrade(Long id);
}
