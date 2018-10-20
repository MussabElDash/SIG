package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity //Marks the class as a persistent class
@Table(name="securities")
public class Security {
	
	@Id //Marks as a primary key
	@Column(name="security_id")
	@SequenceGenerator(sequenceName="securities_seq", name="securities_seq")
	@GeneratedValue(generator="securities_seq", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="security_type")
	private String type;
	
	@Column(name="security_name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="ticker_symbol")
	private AssetPricing ap;
	
	@Column(name="amount")
	private Long amount;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account ownerAccount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Account getOwnerAccount() {
		return ownerAccount;
	}

	public void setOwnerAccount(Account ownerAccount) {
		this.ownerAccount = ownerAccount;
	}

	@Override
	public String toString() {
		return "Security [id=" + id + ", type=" + type + ", name=" + name + ", amount=" + amount + "]";
	}

	public Security(Long id, String type, String name, AssetPricing ap, Long amount, Account ownerAccount) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.ap = ap;
		this.amount = amount;
		this.ownerAccount = ownerAccount;
	}

	public Security() {
		super();
	}

}
