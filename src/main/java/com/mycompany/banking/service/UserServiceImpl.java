package com.mycompany.banking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.banking.model.Account;
import com.mycompany.banking.model.Login;
import com.mycompany.banking.model.User;
import com.mycompany.banking.repository.AccountRepository;
import com.mycompany.banking.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	public boolean doesEmailExist(String email) {
		if(userRepository.findByEmail(email).isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean doesIdExist(Long id) {
		
		if(userRepository.findById(id).isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean register(User user) {
		
		Account primary = new Account("primary");
		Account savings = new Account("savings");
		primary = accountRepository.save(primary);
		savings = accountRepository.save(savings);
		
		user.setPrimary(primary);
		user.setSavings(savings);
		
		user.setPassword(""  + Objects.hash(user.getPassword()));
		
		try {
			user = userRepository.save(user);
			return true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			accountRepository.delete(primary);
			accountRepository.delete(savings);
			return false;
		}
		
	}

	@Override
	public User validateUser(Login login) {
		List<User> listUsers = userRepository.findByUserName(login.getUserName());
		if(listUsers != null && !listUsers.isEmpty()) {
			User user = listUsers.get(0);
			if(Integer.valueOf(user.getPassword()).equals(Objects.hash(login.getPassword()))) {
				return user;
			}
			else {
				return null;
			}
				
		}
		
		return null;
	}
		
	public User getUser(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);		
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}
  	
}
