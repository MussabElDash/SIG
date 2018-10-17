package com.revature.beans;

public class Account {

	private int id;
	private String accountType;
	private String accountName;
	private double balance;
	
	//TODO: The Hibernate annotation here
	// will map the Many to One relationship
	// that has been established in the DB
	private User owner;

	public Account(int id, String accountType, String accountName, double balance, User owner) {
		super();
		this.id = id;
		this.accountType = accountType;
		this.accountName = accountName;
		this.balance = balance;
		this.owner = owner;
	}

	public Account() {
		super();
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountType=" + accountType + ", accountName=" + accountName + ", balance="
				+ balance + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
}
