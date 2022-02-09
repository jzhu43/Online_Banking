package com.mycompany.banking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mycompany.banking.service.AdminService;

@SpringBootApplication
public class BankingApplication implements CommandLineRunner {

	@Autowired
	AdminService adminService;
	
	public static void main(String[] args) {
		SpringApplication.run(BankingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		adminService.createDefaultAdmin();
	}

}
