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

@Entity // Marks the class as a persistent class
@Table(name = "securities")
// TODO ADD Security types
@Check(constraints = "amount >= 0 AND security_type IN ('type1', 'type2', 'type3')")
public class Security {

	@Id // Marks as a primary key
	@Column(name = "security_id")
	@SequenceGenerator(sequenceName = "securities_seq", name = "securities_seq")
	@GeneratedValue(generator = "securities_seq", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "security_type", nullable = false)
	private String type;

	@ManyToOne
	@JoinColumn(name = "ticker_symbol", nullable = false)
	private AssetPricing ap;

	@Column(name = "amount")
	@ColumnDefault(value = "0")
	private Integer amount;

	@ManyToOne
	@JoinColumn(name = "account_id", nullable = false)
	private Account ownerAccount;

	@OneToMany(mappedBy = "requesterSecurity")
	private Set<Trade> requesterTrades;

	@OneToMany(mappedBy = "receiverSecutiy")
	private Set<Trade> receiverTrades;

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

	public Account getOwnerAccount() {
		return ownerAccount;
	}

	public void setOwnerAccount(Account ownerAccount) {
		this.ownerAccount = ownerAccount;
	}

	public Set<Trade> getRequesterTrades() {
		return requesterTrades;
	}

	public Set<Trade> getReceiverTrades() {
		return receiverTrades;
	}

	@Override
	public String toString() {
		return "Security [id=" + id + ", type=" + type + ", amount=" + amount + "]";
	}

	public Security(Long id, String type, String name, AssetPricing ap, Integer amount, Account ownerAccount) {
		super();
		this.id = id;
		this.type = type;
		this.ap = ap;
		this.amount = amount;
		this.ownerAccount = ownerAccount;
	}

	public Security() {
		super();
	}

}
