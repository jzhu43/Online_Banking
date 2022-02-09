package com.mycompany.banking.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.mycompany.banking.model.Admin;
import com.mycompany.banking.repository.AdminRepository;

@PropertySource(value="classpath:adminCredentials.properties")
@Service
public class AdminServiceImpl implements AdminService {
	
	@Value("${adminUsername}")
	private String username;
	
	@Value("${adminPassword}")
	private String password;

	@Autowired
	AdminRepository adminRepository;
	
	@Override
	public void createDefaultAdmin() {
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword("" + Objects.hash(password));
		adminRepository.save(admin);
	}

	@Override
	public boolean validateAdmin(Admin admin) {
		Optional<Admin> adminCredentials = adminRepository.findById(admin.getUsername());
		if (adminCredentials.isPresent()) {
			return Integer.valueOf(adminCredentials.get().getPassword()).equals(Objects.hash(admin.getPassword()));
		}
		return false;
	}

}
