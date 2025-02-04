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

import com.ltp.employees.pojo.AnonymousOpinion;
import com.ltp.employees.service.AnonymousOpinionService;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/opinion")
public class AnonymousOpinionController {
    
    @Autowired
    AnonymousOpinionService opinionService;
    
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<AnonymousOpinion>> getAnonymousOpinion(@PathVariable Long employeeId) {
        return new ResponseEntity<>(opinionService.getAnonymousOpinion(employeeId), HttpStatus.OK);
    }

    @PostMapping("employee/{employeeId}") //This indicates that the employeeId part of the URL path can be a list of Long values separated by commas.
    public ResponseEntity<AnonymousOpinion> saveAnonymousOpinion(@Valid @RequestBody AnonymousOpinion opinion, @PathVariable Long employeeId) {
        return new ResponseEntity<>(opinionService.saveAnonymousOpinion(opinion, employeeId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnonymousOpinion> UpdateAnonymousOpinion(@Valid @RequestBody AnonymousOpinion opinion, @PathVariable Long id) {
        return new ResponseEntity<>(opinionService.updateAnonymousOpinion(opinion, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAnonymousOpinion(@PathVariable Long id) {
        opinionService.deleteAnonymousOpinion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AnonymousOpinion>> getAnonymousOpinion() {
        return new ResponseEntity<>(opinionService.getAnonymousOpinion(), HttpStatus.OK);
    }
    
}
