package ApnaBank;

import java.io.Serializable;
import java.util.ArrayList;

class Account implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name = "";private String address ="" ;private String username="" ;private String password;
	private String contactNo ;private String initialDepo;
	ArrayList<Integer> transaction= new ArrayList<Integer>();
	int accountNo;
	public Account(int accountNo,String name, String address, String username, String password, String contactNo, String initialDepo) {
		super();
		this.name = name;
		this.accountNo = accountNo;
		this.address = address;
		this.username = username; 
		this.password = password;
		this.contactNo = contactNo;
		this.initialDepo = initialDepo;
		this.transaction.add(Integer.parseInt(initialDepo));
	}
	
	void depositAmount(int amount) {
		this.transaction.add(amount);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getInitialDepo() {
		return initialDepo;
	}
	public void setInitialDepo(String initialDepo) {
		this.initialDepo = initialDepo;
	}
	public ArrayList<Integer> getTransaction() {
		return transaction;
	}
	public void setTransaction(int transaction) {
		this.transaction.add(transaction);
	}
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	@Override
	public String toString() {
		return "Account [name=" + name + ", address=" + address + ", username=" + username + ", password=" + password
				+ ", contactNo=" + contactNo + ", initialDepo=" + initialDepo + ", transaction=" + transaction
				+ ", accountNo=" + accountNo + "]";
	}
	
	
}