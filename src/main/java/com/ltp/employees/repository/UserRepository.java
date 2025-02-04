package com.ltp.employees.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ltp.employees.pojo.User;

public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByUsername(String username);
}