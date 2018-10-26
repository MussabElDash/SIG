package com.revature.beans;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
@Check(constraints = "approval_flag IN (-1, 0, 1)")
public class User {

	@Id
	@Column(name = "username")
	private String username;

	@JsonIgnore
	@Column(name = "pass", nullable = false)
	private String pass;

	@Column(name = "fname", nullable = false)
	private String fname;

	@Column(name = "lname", nullable = false)
	private String lname;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "state", nullable = false)
	private String state;

	@Column(name = "zip", nullable = false)
	private Integer zip;

	@JsonIgnore
	@Column(name = "ssn", nullable = false)
	private Integer ssn;

	@Column(name = "dob", nullable = false)
	private Date dateOfBirth;

	@Column(name = "phone", nullable = false)
	private Integer phone;

	@Column(name = "approval_flag", nullable = false)
	@ColumnDefault(value = "0")
	private Integer approvalFlag;

	@JsonIgnore
	@OneToMany(mappedBy = "owner")
	private Set<Account> accounts;

	public User(String username, String pass, String fname, String lname, String address, String city, String state,
			Integer zip, Integer ssn, Date dateOfBirth, Integer phone, Integer approvalFlag) {
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

	public void setZip(Integer zip) {
		this.zip = zip;
	}

	public long getSsn() {
		return ssn;
	}

	public void setSsn(Integer ssn) {
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

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public long getApprovalFlag() {
		return approvalFlag;
	}

	public void setApprovalFlag(Integer approvalFlag) {
		this.approvalFlag = approvalFlag;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

}
