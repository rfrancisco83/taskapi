package com.rf.tasks.repository;

import org.springframework.data.repository.CrudRepository;

import com.rf.tasks.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByName(String name);

}
