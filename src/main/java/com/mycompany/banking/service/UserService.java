package com.mycompany.banking.service;

import java.util.List;

import com.mycompany.banking.model.Login;
import com.mycompany.banking.model.User;

public interface UserService {
	
	boolean doesEmailExist(String email);
	
	boolean doesIdExist(Long id);
	
	public boolean register(User user);
	
	User validateUser(Login login);
	
	public User getUser(Long id);
	
	public User updateUser(User user);
	
  	public List<User> getAllUsers();
  	
}
