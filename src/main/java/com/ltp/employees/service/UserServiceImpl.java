package com.ltp.employees.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

import com.ltp.employees.exception.EntityNotFoundException;
import com.ltp.employees.pojo.Employee;
import com.ltp.employees.pojo.User;
import com.ltp.employees.repository.EmployeeRepository;
import com.ltp.employees.repository.UserRepository;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
    private EmployeeRepository employeeRepository;

    @SuppressWarnings("null")
    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return unwrapUser(user, id);
    }

    @Override
    public User getUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return unwrapUser(user, 404L);
    }

    @Override
    public List<User> getUser(){
        return (List<User>)userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @SuppressWarnings("null")
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, User newUser) {
        Optional<User> user = userRepository.findById(id);
        User unwrappedUser = unwrapUser(user, id);
        unwrappedUser.setUsername(newUser.getUsername());
        unwrappedUser.setPassword(newUser.getPassword());
        return userRepository.save(unwrappedUser);
    }

    @Override
    public User updateUserWithEmployee(Long id, User newUser, Long employeeId) {
        Optional<User> user = userRepository.findById(id);
        User unwrappedUser = unwrapUser(user, id);

        Employee employee = EmployeeServiceImpl.unwrapEmployee(employeeRepository.findById(employeeId), employeeId);

        unwrappedUser.setEmployee(employee);
        return userRepository.save(unwrappedUser); 
    }

    static User unwrapUser(Optional<User> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, User.class);
    }
    
}
