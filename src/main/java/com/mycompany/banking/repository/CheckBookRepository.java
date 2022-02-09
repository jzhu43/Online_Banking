package com.mycompany.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mycompany.banking.model.CheckbookRequest;
import com.mycompany.banking.model.UserCheckbookReq;

public interface CheckBookRepository extends CrudRepository<CheckbookRequest, Long>{

	@Query("SELECT new com.mycompany.banking.model.UserCheckbookReq(u.id, u.userName, c.requestNumber, c.accountType, c.description, c.confirmed) FROM users u, checkbook_reqs c WHERE u.id = c.userId")
	public List<UserCheckbookReq> userJoinCheckbookReq();

}
