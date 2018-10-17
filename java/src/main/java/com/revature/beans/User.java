package com.revature.beans;

import java.sql.Date;

public class User {
	
	private String username;
	private String pass;
	private String fname;
	private String lname;
	private String address;
	private String city;
	private String state;
	private int zip;
	private int ssn;
	private Date dateOfBirth;
	private int phone;
	private int approvalFlag;
	
	public User(String username, String pass, String fname, String lname, String address, String city, String state,
			int zip, int ssn, Date dateOfBirth, int phone, int approvalFlag) {
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

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public int getApprovalFlag() {
		return approvalFlag;
	}

	public void setApprovalFlag(int approvalFlag) {
		this.approvalFlag = approvalFlag;
	}
	
}
