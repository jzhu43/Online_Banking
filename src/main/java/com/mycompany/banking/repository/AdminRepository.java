package com.mycompany.banking.repository;


import org.springframework.data.repository.CrudRepository;

import com.mycompany.banking.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, String>{

}
