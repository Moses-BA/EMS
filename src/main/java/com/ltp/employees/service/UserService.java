package com.ltp.employees.service;

import java.util.List;

import com.ltp.employees.pojo.User;

public interface UserService {
    User getUser(Long id);
    User getUser(String username);
    List<User> getUser();
    User saveUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    User updateUserWithEmployee(Long id, User newUser, Long employeeId);
}