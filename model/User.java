package model;

/*
 * User is abstract as we never instantiate this class
 */
public abstract class User {
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private long phoneNo;
	private String homeAddress;
		
	@Override
	public String toString() {
		return "Name: " + this.getFirstName() + " " + this.getLastName() + " | Username: " + this.getUserName();
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public long getPhoneNo() {
		return phoneNo;
	}
	
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public String getHomeAddress() {
		return homeAddress;
	}
	
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
}
