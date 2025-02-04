package com.ltp.employees.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltp.employees.pojo.SkillUpgrade;
import com.ltp.employees.service.SkillUpgradeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/skill")
public class SkillUpgradeController {

    @Autowired
    SkillUpgradeService skillService;
    
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<SkillUpgrade>> getSkillUpgrade(@PathVariable Long employeeId) {
        return new ResponseEntity<>(skillService.getSkillUpgrade(employeeId), HttpStatus.OK);
    }

    @PostMapping("employee/{employeeId}") //This indicates that the employeeId part of the URL path can be a list of Long values separated by commas.
    public ResponseEntity<SkillUpgrade> saveSkillUpgrade(@Valid @RequestBody SkillUpgrade skill, @PathVariable Long employeeId) {
        return new ResponseEntity<>(skillService.saveSkillUpgrade(skill, employeeId), HttpStatus.CREATED);
    }

    @PostMapping("/general") //This indicates that the employeeId part of the URL path can be a list of Long values separated by commas.
    public ResponseEntity<SkillUpgrade> saveGeneralSkillUpgrade(@Valid @RequestBody SkillUpgrade skill) {
        return new ResponseEntity<>(skillService.saveGeneralSkillUpgrade(skill), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillUpgrade> UpdateSkillUpgrade(@Valid @RequestBody SkillUpgrade skill, @PathVariable Long id) {
        return new ResponseEntity<>(skillService.updateSkillUpgrade(skill, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSkillUpgrade(@PathVariable Long id) {
        skillService.deleteSkillUpgrade(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SkillUpgrade>> getSkillUpgrade() {
        return new ResponseEntity<>(skillService.getSkillUpgrades(), HttpStatus.OK);
    }
}
