package com.mycompany.banking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Entity(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", updatable= false, nullable=false)
	private Long userId;
	
	@Column(unique = true)
	@NotEmpty(message="Username cannot be empty")
	private String userName;
	
	@NotEmpty(message="First name cannot be empty")
	private String firstName;
	
	@NotEmpty(message="Last name cannot be empty")
	private String lastName;
	
	@NotEmpty(message="Password cannot be empty")
//	@Pattern(regexp="^[0-9]{3,18}", message="Enter a valid password, should contain letters and numbers")
	private String password;
	
	@NotEmpty(message="Email cannot be empty")
	@Column(unique = true)
	@Email(message = "Should be of the form name@domain.extension")
	private String email;
	
	@NotEmpty(message="Phone number cannot be empty")
	private String phoneNumber;
	
	private boolean enabled = true;
	
	@OneToOne
	@JoinColumn(name="primary_id")
	private Account primary;
	
	@OneToOne
	@JoinColumn(name="savings_id")
	private Account savings;

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Account getPrimary() {
		return primary;
	}

	public void setPrimary(Account primary) {
		this.primary = primary;
	}

	public Account getSavings() {
		return savings;
	}

	public void setSavings(Account savings) {
		this.savings = savings;
	}

}
