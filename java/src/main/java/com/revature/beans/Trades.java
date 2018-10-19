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

@Entity
@Table (name="trades") //many to one = many trades can have one....
public class Trades {
	
	@Id
	@Column(name="trade_id")
	@SequenceGenerator(sequenceName="trade_seq", name="trade_seq")
	@GeneratedValue(generator="trade_seq", strategy = GenerationType.SEQUENCE)
	private Long tradeId;
	
	@ManyToOne
	@Column (name="requestor_username")
	private User requestor;
	
	@ManyToOne
	@Column (name="receiver_username")
	private User receiver;
	
	@Column (name="broker_status")
	private Long brokerStatus;
	
	@Column (name="receiver_approval")
	private Long receiverApproval;
	
	@Column (name="security_id_requestor")
	private Long securityIdRequestor;
	
	@Column (name="security_id_receiver")
	private Long secutiyIdReceiver;
	
	@Column (name="amount_requestor")
	private Long amountRequestor;
	
	@Column (name="amount_receiver")
	private Long amountReceiver;
	
	@Column (name="created_on")
	private Date createdOn;
	
	public Long getTradeId() {
		return tradeId;
	}
	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}
	
	public User getRequestor() {
		return requestor;
	}
	public void setRequestor(User requestor) {
		this.requestor = requestor;
	}
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	public Long getBrokerStatus() {
		return brokerStatus;
	}
	public void setBrokerStatus(Long brokerStatus) {
		this.brokerStatus = brokerStatus;
	}
	public Long getReceiverApproval() {
		return receiverApproval;
	}
	public void setReceiverApproval(Long receiverApproval) {
		this.receiverApproval = receiverApproval;
	}
	public Long getSecurityIdRequestor() {
		return securityIdRequestor;
	}
	public void setSecurityIdRequestor(Long securityIdRequestor) {
		this.securityIdRequestor = securityIdRequestor;
	}
	public Long getSecutiyIdReceiver() {
		return secutiyIdReceiver;
	}
	public void setSecutiyIdReceiver(Long secutiyIdReceiver) {
		this.secutiyIdReceiver = secutiyIdReceiver;
	}
	public Long getAmountRequestor() {
		return amountRequestor;
	}
	public void setAmountRequestor(Long amountRequestor) {
		this.amountRequestor = amountRequestor;
	}
	public Long getAmountReceiver() {
		return amountReceiver;
	}
	public void setAmountReceiver(Long amountReceiver) {
		this.amountReceiver = amountReceiver;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	@Override
	public String toString() {
		return "Trades [tradeId=" + tradeId + ", requestor=" + requestor + ", receiver=" + receiver + ", brokerStatus="
				+ brokerStatus + ", receiverApproval=" + receiverApproval + ", securityIdRequestor="
				+ securityIdRequestor + ", secutiyIdReceiver=" + secutiyIdReceiver + ", amountRequestor="
				+ amountRequestor + ", amountReceiver=" + amountReceiver + ", createdOn=" + createdOn + "]";
	}
	
	public Trades(Long tradeId, User requestor, User receiver, Long brokerStatus, Long receiverApproval,
			Long securityIdRequestor, Long secutiyIdReceiver, Long amountRequestor, Long amountReceiver,
			Date createdOn) {
		super();
		this.tradeId = tradeId;
		this.requestor = requestor;
		this.receiver = receiver;
		this.brokerStatus = brokerStatus;
		this.receiverApproval = receiverApproval;
		this.securityIdRequestor = securityIdRequestor;
		this.secutiyIdReceiver = secutiyIdReceiver;
		this.amountRequestor = amountRequestor;
		this.amountReceiver = amountReceiver;
		this.createdOn = createdOn;
	}
	
	public Trades(User requestor, User receiver, Long brokerStatus, Long receiverApproval, Long securityIdRequestor,
			Long secutiyIdReceiver, Long amountRequestor, Long amountReceiver, Date createdOn) {
		super();
		this.requestor = requestor;
		this.receiver = receiver;
		this.brokerStatus = brokerStatus;
		this.receiverApproval = receiverApproval;
		this.securityIdRequestor = securityIdRequestor;
		this.secutiyIdReceiver = secutiyIdReceiver;
		this.amountRequestor = amountRequestor;
		this.amountReceiver = amountReceiver;
		this.createdOn = createdOn;
	}
	public Trades() {
		super();
	}
	
	

}
