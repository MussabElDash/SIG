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
@Table(name = "trades") // many to one = many trades can have one....
@Check(constraints = "broker_status IN (-1, 0, 1) AND receiver_approval IN (-1, 0, 1) AND amount_requester >= 0")
public class Trade {

	@Id
	@Column(name = "trade_id")
	@SequenceGenerator(sequenceName = "trade_seq", name = "trade_seq")
	@GeneratedValue(generator = "trade_seq", strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "requester_account_id", nullable = false)
	private Account requesterAccount;

	@ManyToOne
	@JoinColumn(name = "receiver_account_id")
	private Account receiverAccount;

	@Column(name = "broker_status")
	private Integer brokerStatus;

	@Column(name = "receiver_approval")
	private Integer receiverApproval;

	@ManyToOne
	@JoinColumn(name = "security_id_requester", nullable = false)
	private Security requesterSecurity;

	@ManyToOne
	@JoinColumn(name = "security_id_receiver")
	private Security receiverSecutiy;

	@Column(name = "amount_requester", nullable = false)
	@ColumnDefault(value = "0")
	private Integer amountrequester;

	@Column(name = "amount_receiver")
	private Integer amountReceiver;

	@Column(name = "created_on")
	private Date createdOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getrequesterAccount() {
		return requesterAccount;
	}

	public void setrequesterAccount(Account requesterAccount) {
		this.requesterAccount = requesterAccount;
	}

	public Account getReceiverAccount() {
		return receiverAccount;
	}

	public void setReceiveraccount(Account receiverAccount) {
		this.receiverAccount = receiverAccount;
	}

	public Integer getBrokerStatus() {
		return brokerStatus;
	}

	public void setBrokerStatus(Integer brokerStatus) {
		this.brokerStatus = brokerStatus;
	}

	public Integer getReceiverApproval() {
		return receiverApproval;
	}

	public void setReceiverApproval(Integer receiverApproval) {
		this.receiverApproval = receiverApproval;
	}

	public Integer getAmountrequester() {
		return amountrequester;
	}

	public void setAmountrequester(Integer amountrequester) {
		this.amountrequester = amountrequester;
	}

	public Integer getAmountReceiver() {
		return amountReceiver;
	}

	public void setAmountReceiver(Integer amountReceiver) {
		this.amountReceiver = amountReceiver;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Account getRequesterAccount() {
		return requesterAccount;
	}

	public void setRequesterAccount(Account requesterAccount) {
		this.requesterAccount = requesterAccount;
	}

	public Security getRequesterSecurity() {
		return requesterSecurity;
	}

	public void setRequesterSecurity(Security requesterSecurity) {
		this.requesterSecurity = requesterSecurity;
	}

	public Security getReceiverSecutiy() {
		return receiverSecutiy;
	}

	public void setReceiverSecutiy(Security receiverSecutiy) {
		this.receiverSecutiy = receiverSecutiy;
	}

	public void setReceiverAccount(Account receiverAccount) {
		this.receiverAccount = receiverAccount;
	}

	@Override
	public String toString() {
		return "Trade [id=" + id + ", requesterAccount=" + requesterAccount.toString() + ", receiverAccount=" + receiverAccount.toString()
				+ ", brokerStatus=" + brokerStatus + ", receiverApproval=" + receiverApproval + ", requesterSecurity="
				+ requesterSecurity.toString() + ", receiverSecutiy=" + receiverSecutiy.toString() + ", amountrequester=" + amountrequester
				+ ", amountReceiver=" + amountReceiver + ", createdOn=" + createdOn.toString() + "]";
	}

	public Trade(Long id, Account requesterAccount, Account receiverAccount, Integer brokerStatus, Integer receiverApproval,
			Security requesterSecurity, Security receiverSecutiy, Integer amountrequester, Integer amountReceiver,
			Date createdOn) {
		super();
		this.id = id;
		this.requesterAccount = requesterAccount;
		this.receiverAccount = receiverAccount;
		this.brokerStatus = brokerStatus;
		this.receiverApproval = receiverApproval;
		this.requesterSecurity = requesterSecurity;
		this.receiverSecutiy = receiverSecutiy;
		this.amountrequester = amountrequester;
		this.amountReceiver = amountReceiver;
		this.createdOn = createdOn;
	}

	public Trade() {
		super();
	}

}
