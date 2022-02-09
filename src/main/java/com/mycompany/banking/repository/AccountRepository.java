package com.mycompany.banking.repository;


import org.springframework.data.repository.CrudRepository;

import com.mycompany.banking.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{

}
