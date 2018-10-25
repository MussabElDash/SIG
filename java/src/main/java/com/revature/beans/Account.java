package com.revature.beans;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "accounts")
// TODO ADD account types in here
@Check(constraints = "account_type IN ('Brokerage', 'IRA')")
public class Account {

	@Id
	@Column(name = "account_id")
	@SequenceGenerator(sequenceName = "acc_seq", name = "acc_seq")
	@GeneratedValue(generator = "acc_seq", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "account_type", nullable = false)
	private String accountType;

	@Column(name = "account_name")
	private String accountName;

	@Column(name = "balance")
	@ColumnDefault(value = "0")
	private Double balance;

	@ManyToOne
	@JoinColumn(name = "username", nullable = false)
	private User owner;

	@OneToMany(mappedBy = "ownerAccount")
	private Set<Order> orders;

	@OneToMany(mappedBy = "ownerAccount")
	private Set<Security> securities;

	@OneToMany(mappedBy = "requesterAccount")
	private Set<Trade> requesterTrades;

	@OneToMany(mappedBy = "receiverAccount")
	private Set<Trade> receiverTrades;

	public Account(long id, String accountType, String accountName, double balance, User owner) {
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Set<Order> getOrders() {
		return orders;
	}

	public Set<Security> getSecurities() {
		return securities;
	}

	public Set<Trade> getRequesterTrades() {
		return requesterTrades;
	}

	public Set<Trade> getReceiverTrades() {
		return receiverTrades;
	}

}
