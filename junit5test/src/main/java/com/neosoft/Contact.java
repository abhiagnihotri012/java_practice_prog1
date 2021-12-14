package com.neosoft;



//Mobile phone contact class
public class Contact {

	private String firstName;
	private String lastName;
	private String phoneNumber;
	
	public Contact(String firstName, String lastName, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}	

	public void validateFirstName() {
		if(this.firstName == null)
			throw new RuntimeException("First Name can not be null");
	}
	
	public void validateLastName() {
		if(this.lastName == null)
			throw new RuntimeException("Last Name can not be null");
	}
	

	
	public void validatePhoneNumber() {
		if(this.phoneNumber.length() !=10)
			throw new RuntimeException("Phone number should be 10 digits long");
		if(this.phoneNumber == null)
			throw new RuntimeException("Phone number should not be null");
		if(!this.phoneNumber.matches("\\d+"))
			throw new RuntimeException("Phone number should contain only digits");
		if(this.phoneNumber.startsWith("0"))
			throw new RuntimeException("Phone number should not start with 0");
	}
}
