package com.mycompany.banking.service;

import java.util.List;

import com.mycompany.banking.model.CheckbookRequest;
import com.mycompany.banking.model.UserCheckbookReq;

public interface CheckBookReqService {
	
	public CheckbookRequest getCheckbookReq(Long id);

	public void submitCheckBookRequest(CheckbookRequest req);
	
	public void updateCheckbookReq(CheckbookRequest req);
	
	public List<UserCheckbookReq> getAllUserCheckbookReqs();
}
