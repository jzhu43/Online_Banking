package com.mycompany.banking.service;

import com.mycompany.banking.model.Admin;

public interface AdminService {
	
	public void createDefaultAdmin();
	
	public boolean validateAdmin(Admin admin);
	
}
