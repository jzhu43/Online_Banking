package com.mycompany.banking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="checkbook_reqs")
public class CheckbookRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long requestNumber;
	private long userId;
	private String accountType;
	private String description;
	private boolean confirmed = false;
	
	public CheckbookRequest() {


	}

	public long getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(long requestNumber) {
		this.requestNumber = requestNumber;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	
	
}
