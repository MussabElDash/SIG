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
	@JoinColumn(name = "requester_username", nullable = false)
	private User requester;

	@ManyToOne
	@JoinColumn(name = "receiver_username")
	private User receiver;

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

	public User getrequester() {
		return requester;
	}

	public void setrequester(User requester) {
		this.requester = requester;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
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

	@Override
	public String toString() {
		return "Trade [id=" + id + ", requester=" + requester + ", receiver=" + receiver + ", brokerStatus="
				+ brokerStatus + ", receiverApproval=" + receiverApproval + ", requesterSecurity=" + requesterSecurity
				+ ", receiverSecutiy=" + receiverSecutiy + ", amountrequester=" + amountrequester + ", amountReceiver="
				+ amountReceiver + ", createdOn=" + createdOn + "]";
	}

	public Trade(Long id, User requester, User receiver, Integer brokerStatus, Integer receiverApproval,
			Security requesterSecurity, Security receiverSecutiy, Integer amountrequester, Integer amountReceiver,
			Date createdOn) {
		super();
		this.id = id;
		this.requester = requester;
		this.receiver = receiver;
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
