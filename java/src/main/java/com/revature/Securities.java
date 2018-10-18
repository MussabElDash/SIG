package com.revature;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //Marks the class as a persistent class
@Table(name="securities")
public class Securities {
	
	@Id //Marks as a primary key
	@Column(name="security_id")
	private Integer Id;
	
	@Column(name="security_type")
	private String Type;
	
	@Column(name="security_name")
	private String Name;
	
	@Column(name="ticker_symbol")
	private String TickerSymbol;
	
	@Column(name="amount")
	private Integer Amount;
	
	@Column(name="account_id")
	private Integer AccountId;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
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

	public String getTickerSymbol() {
		return TickerSymbol;
	}

	public void setTickerSymbol(String tickerSymbol) {
		TickerSymbol = tickerSymbol;
	}

	public Integer getAmount() {
		return Amount;
	}

	public void setAmount(Integer amount) {
		Amount = amount;
	}

	public Integer getAccountId() {
		return AccountId;
	}

	public void setAccountId(Integer accountId) {
		AccountId = accountId;
	}

	@Override
	public String toString() {
		return "Securities [Id=" + Id + ", Type=" + Type + ", Name=" + Name + ", Amount=" + Amount + "]";
	}

	public Securities(Integer id, String type, String name, String tickerSymbol, Integer amount, Integer accountId) {
		super();
		Id = id;
		Type = type;
		Name = name;
		TickerSymbol = tickerSymbol;
		Amount = amount;
		AccountId = accountId;
	}

	public Securities() {
		super();
	}

}
