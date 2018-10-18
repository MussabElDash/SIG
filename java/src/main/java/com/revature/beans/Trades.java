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

@Entity
@Table (name="trades") //many to one = many trades can have one....
public class Trades {
	
	@Id
	@Column(name="trade_id")
	@SequenceGenerator(sequenceName="trade_seq", name="trade_seq")
	@GeneratedValue(generator="trade_seq", strategy = GenerationType.SEQUENCE)
	private Long tradeId;
	
	@Column (name="requestor_username")
	private String requestorUsername;
	
	@Column (name="receiver_username")
	private String receiverUsername;
	
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
	
	@ManyToOne
	@JoinColumn(name="username")
	private User u;
	
	
	public Long getTradeId() {
		return tradeId;
	}
	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}
	public String getRequestorUsername() {
		return requestorUsername;
	}
	public void setRequestorUsername(String requestorUsername) {
		this.requestorUsername = requestorUsername;
	}
	public String getReceiverUsername() {
		return receiverUsername;
	}
	public void setReceiverUsername(String receiverUsername) {
		this.receiverUsername = receiverUsername;
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
	@Override
	public String toString() {
		return "Trades [tradeId=" + tradeId + ", requestorUsername=" + requestorUsername + ", receiverUsername="
				+ receiverUsername + ", brokerStatus=" + brokerStatus + ", receiverApproval=" + receiverApproval
				+ ", securityIdRequestor=" + securityIdRequestor + ", secutiyIdReceiver=" + secutiyIdReceiver
				+ ", amountRequestor=" + amountRequestor + ", amountReceiver=" + amountReceiver + "]";
	}
	public Trades(Long tradeId, String requestorUsername, String receiverUsername, Long brokerStatus,
			Long receiverApproval, Long securityIdRequestor, Long secutiyIdReceiver, Long amountRequestor,
			Long amountReceiver) {
		super();
		this.tradeId = tradeId;
		this.requestorUsername = requestorUsername;
		this.receiverUsername = receiverUsername;
		this.brokerStatus = brokerStatus;
		this.receiverApproval = receiverApproval;
		this.securityIdRequestor = securityIdRequestor;
		this.secutiyIdReceiver = secutiyIdReceiver;
		this.amountRequestor = amountRequestor;
		this.amountReceiver = amountReceiver;
	}
	public Trades(String requestorUsername, String receiverUsername, Long brokerStatus, Long receiverApproval,
			Long securityIdRequestor, Long secutiyIdReceiver, Long amountRequestor, Long amountReceiver) {
		super();
		this.requestorUsername = requestorUsername;
		this.receiverUsername = receiverUsername;
		this.brokerStatus = brokerStatus;
		this.receiverApproval = receiverApproval;
		this.securityIdRequestor = securityIdRequestor;
		this.secutiyIdReceiver = secutiyIdReceiver;
		this.amountRequestor = amountRequestor;
		this.amountReceiver = amountReceiver;
	}
	public Trades() {
		super();
	}
	

}
