package com.mycompany.banking.model;

public class UserCheckbookReq {
	private Long userId;
	private String userName;
	private Long requestNumber;
	private String accountType;
	private String description;
	private Boolean confirmed;
	
	public UserCheckbookReq() {
	}

	public UserCheckbookReq(Long userId, String userName, Long requestNumber, String accountType, String description,
			Boolean confirmed) {
		this.userId = userId;
		this.userName = userName;
		this.requestNumber = requestNumber;
		this.accountType = accountType;
		this.description = description;
		this.confirmed = confirmed;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(Long requestNumber) {
		this.requestNumber = requestNumber;
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

	public Boolean getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}	
	
}
