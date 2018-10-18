package com.revature.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class User {
	
	@Id
	@Column(name="username")
	private String username;
	
	@Column(name="pass")
	private String pass;
	
	@Column(name="fname")
	private String fname;
	
	@Column(name="lname")
	private String lname;
	
	@Column(name="address")
	private String address;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="zip")
	private Long zip;
	
	@Column(name="ssn")
	private Long ssn;
	
	@Column(name="dob")
	private Date dateOfBirth;
	
	@Column(name="phone")
	private Long phone;
	
	@Column(name="approval_flag")
	private Long approvalFlag;
	
	public User(String username, String pass, String fname, String lname, String address, String city, String state,
			long zip, long ssn, Date dateOfBirth, long phone, long approvalFlag) {
		super();
		this.username = username;
		this.pass = pass;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.ssn = ssn;
		this.dateOfBirth = dateOfBirth;
		this.phone = phone;
		this.approvalFlag = approvalFlag;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", pass=" + pass + ", fname=" + fname + ", lname=" + lname + ", address="
				+ address + ", city=" + city + ", state=" + state + ", zip=" + zip + ", ssn=" + ssn + ", dateOfBirth="
				+ dateOfBirth + ", phone=" + phone + ", approvalFlag=" + approvalFlag + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getZip() {
		return zip;
	}

	public void setZip(long zip) {
		this.zip = zip;
	}

	public long getSsn() {
		return ssn;
	}

	public void setSsn(long ssn) {
		this.ssn = ssn;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public long getApprovalFlag() {
		return approvalFlag;
	}

	public void setApprovalFlag(long approvalFlag) {
		this.approvalFlag = approvalFlag;
	}
	
}
