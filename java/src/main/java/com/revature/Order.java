package com.revature;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity 
@Table(name="orders")
public class Order {
	
	@Id //Marks as a primary key
	@Column(name="order_id")
	@SequenceGenerator(sequenceName="order_seq", name="order_seq")
	@GeneratedValue(generator="order_seq", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account ownerAccount;
	
	@ManyToOne
	@JoinColumn(name="ticker_symbol")
	private AssetPricing ap;
	
	@Column(name="amount")
	private Long amount;
	
	@Column(name="broker_status")
	private String brokerStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getOwnerAccount() {
		return ownerAccount;
	}

	public void setOwnerAccount(Account ownerAccount) {
		this.ownerAccount = ownerAccount;
	}

	public AssetPricing getAp() {
		return ap;
	}

	public void setAp(AssetPricing ap) {
		this.ap = ap;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getBrokerStatus() {
		return brokerStatus;
	}

	public void setBrokerStatus(String brokerStatus) {
		this.brokerStatus = brokerStatus;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", amount=" + amount + ", brokerStatus=" + brokerStatus + "]";
	}

	public Order(Long id, Account ownerAccount, AssetPricing ap, Long amount, String brokerStatus) {
		super();
		this.id = id;
		this.ownerAccount = ownerAccount;
		this.ap = ap;
		this.amount = amount;
		this.brokerStatus = brokerStatus;
	}

	public Order() {
		super();
	}

	
	
}	