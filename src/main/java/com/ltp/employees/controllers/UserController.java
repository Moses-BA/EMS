package com.ltp.employees.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltp.employees.enums.UserRole;
import com.ltp.employees.pojo.User;
import com.ltp.employees.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;


@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserService userService;
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        return new ResponseEntity<>(userService.getUser(username), HttpStatus.OK);
    }

    @PostMapping("/register")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user, BindingResult result, HttpSession session) {
        userService.saveUser(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

    @PostMapping("/registeradmin") 
    public ResponseEntity<User> createAdmin(@Valid @RequestBody User user) {
        user.setRole(UserRole.ADMIN);
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
    }

    @PutMapping("/{id}/{employeeId}")
    public ResponseEntity<User> updateUserWithEmployee(@PathVariable Long id, @Valid @RequestBody User user, @PathVariable Long employeeId) {
        return new ResponseEntity<>(userService.updateUserWithEmployee(id, user, employeeId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUser() {
        return new ResponseEntity<>(userService.getUser(), HttpStatus.OK);
    }
}
