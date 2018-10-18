package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="accounts")
public class Account {

	@Id
	@Column(name="account_id")
	private int id;
	
	@Column(name="account_type")
	private String accountType;
	
	@Column(name="account_name")
	private String accountName;
	
	@Column(name="balance")
	private double balance;
	
	@ManyToOne
	@JoinColumn(name="username")
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
