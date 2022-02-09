package com.mycompany.banking.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.banking.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	List<User> findByEmail(String email);
	
	List<User>findByUserName(String userName);
	
}
