package com.revature;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="orders")
public class Orders {
	
	@Id //Marks as a primary key
	@Column(name="order_id")
	private Integer Id;
	
	@Column(name="account_id")
	private Integer AccountId;
	
	@Column(name="ticker_symbol")
	private String TickerSymbol;
	
	@Column(name="amount")
	private Integer Amount;
	
	@Column(name="broker_status")
	private String BrokerStatus;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getAccountId() {
		return AccountId;
	}

	public void setAccountId(Integer accountId) {
		AccountId = accountId;
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

	public String getBrokerStatus() {
		return BrokerStatus;
	}

	public void setBrokerStatus(String brokerStatus) {
		BrokerStatus = brokerStatus;
	}
	

	@Override
	public String toString() {
		return "Orders [Id=" + Id + ", Amount=" + Amount + ", BrokerStatus=" + BrokerStatus + "]";
	}

	public Orders(Integer id, Integer accountId, String tickerSymbol, Integer amount, String brokerStatus) {
		super();
		Id = id;
		AccountId = accountId;
		TickerSymbol = tickerSymbol;
		Amount = amount;
		BrokerStatus = brokerStatus;
	}

	public Orders() {
		super();
	}

}
