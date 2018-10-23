package com.revature.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "orders")
@Check(constraints = "amount >= 0 AND broker_status IN (-1, 0, 1)")
public class Order {

	@Id // Marks as a primary key
	@Column(name = "order_id")
	@SequenceGenerator(sequenceName = "order_seq", name = "order_seq")
	@GeneratedValue(generator = "order_seq", strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "account_id", nullable = false)
	private Account ownerAccount;

	@ManyToOne
	@JoinColumn(name = "ticker_symbol", nullable = false)
	private AssetPricing ap;

	@Column(name = "amount")
	@ColumnDefault(value = "0")
	private Integer amount;

	@Column(name = "broker_status")
	@ColumnDefault(value = "0")
	private Integer brokerStatus;

	@Column(name = "created_on")
	private Date createdOn;

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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getBrokerStatus() {
		return brokerStatus;
	}

	public void setBrokerStatus(Integer brokerStatus) {
		this.brokerStatus = brokerStatus;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", amount=" + amount + ", brokerStatus=" + brokerStatus + ", createdOn=" + createdOn
				+ "]";
	}

	public Order(Long id, Account ownerAccount, AssetPricing ap, Integer amount, Integer brokerStatus, Date createdOn) {
		super();
		this.id = id;
		this.ownerAccount = ownerAccount;
		this.ap = ap;
		this.amount = amount;
		this.brokerStatus = brokerStatus;
		this.createdOn = createdOn;
	}

	public Order() {
		super();
	}

}