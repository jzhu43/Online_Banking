package com.mycompany.banking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.banking.model.CheckbookRequest;
import com.mycompany.banking.model.UserCheckbookReq;
import com.mycompany.banking.repository.CheckBookRepository;

@Service
public class CheckBookReqServiceImpl implements CheckBookReqService {
	
		@Autowired
		CheckBookRepository checkbookRepository;
		
		@Override
		public CheckbookRequest getCheckbookReq(Long id) {
			Optional<CheckbookRequest> req = checkbookRepository.findById(id);
			if (req.isPresent()) {
				return req.get();
			}
			return null;
		}
		
		@Override
		public void submitCheckBookRequest(CheckbookRequest request) {
			checkbookRepository.save(request);
		}
		
		@Override
		public void updateCheckbookReq(CheckbookRequest req) {
			checkbookRepository.save(req);
		}

		@Override
		public List<UserCheckbookReq> getAllUserCheckbookReqs() {
			return checkbookRepository.userJoinCheckbookReq();
		}

}
