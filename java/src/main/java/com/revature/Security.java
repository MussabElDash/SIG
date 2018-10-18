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

import 

@Entity //Marks the class as a persistent class
@Table(name="securities")
public class Security {
	
	@Id //Marks as a primary key
	@Column(name="security_id")
	@SequenceGenerator(sequenceName="securities_seq", name="securities_seq")
	@GeneratedValue(generator="securities_seq", strategy = GenerationType.SEQUENCE)
	private Long Id;
	
	@Column(name="security_type")
	private String Type;
	
	@Column(name="security_name")
	private String Name;
	
	@ManyToOne
	@JoinColumn(name="ticker_symbol")
	private AssetPricing ap;
	
	@Column(name="amount")
	private Long Amount;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account OwnerAccount;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Long getAmount() {
		return Amount;
	}

	public void setAmount(Long amount) {
		Amount = amount;
	}

	public Long getOwnerAccount() {
		return OwnerAccount;
	}

	public void setOwnerAccount(Account owneraccount) {
		OwnerAccount = owneraccount;
	}
	

	public AssetPricing getAp() {
		return ap;
	}

	public void setAp(AssetPricing ap) {
		this.ap = ap;
	}

	@Override
	public String toString() {
		return "Securities [Id=" + Id + ", Type=" + Type + ", Name=" + Name + ", Amount=" + Amount + "]";
	}


	public Security(Long id, String type, String name, AssetPricing ap, Long amount, Account ownerAccount) {
		super();
		Id = id;
		Type = type;
		Name = name;
		this.ap = ap;
		Amount = amount;
		OwnerAccount = ownerAccount;
	}

	public Security() {
		super();
	}

}
