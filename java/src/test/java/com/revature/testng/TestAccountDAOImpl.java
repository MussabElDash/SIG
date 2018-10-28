package com.revature.testng;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.revature.beans.Account;
import com.revature.beans.User;
import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.sql.Date;

public class TestAccountDAOImpl {
	
	AccountDAO adao = new AccountDAOImpl();
	UserDAO udao = new UserDAOImpl();
	
	Account a = null;
	User u = new User("tester",
			 "pass",
			 "firstname",
			 "lastname",
			 "123 Easy St",
			 "Bobbitown", 
			 "YM",
			 12345,
			 123456789,
			 new Date(System.currentTimeMillis()),
			 1234567890,
			 0);
	Long id;
	Long nullid;
	
	@BeforeClass
	public void putUser() {
		udao.addUser(u);
	}
	
  @Test(priority=1)
  public void testAddAccount() {
	  
	  a = new Account();
	  a.setAccountType("IRA");
	  a.setAccountName("Retirement Stuff");
	  a.setBalance(0.0);
	  a.setOwner(u);
	  
	  id = adao.addAccount(a);
	  
	  assertNotEquals(id,null);
	  
  }
  
  @Test
  public void testAddAccountNullType() {
	  a = new Account();
	  a.setAccountType(null);
	  a.setAccountName("Retirement Stuff");
	  a.setBalance(0.0);
	  a.setOwner(u);
	  
	  nullid = adao.addAccount(a);
	  
	  assertEquals(id,null);
  }
  
  @Test
  public void testAddAccountNullOwner() {
	  a = new Account();
	  a.setAccountType("Vacation Stuff");
	  a.setAccountName("Retirement Stuff");
	  a.setBalance(0.0);
	  a.setOwner(null);
	  
	  nullid = adao.addAccount(a);
	  
	  assertEquals(id,null);
  }
  
  @Test( dependsOnMethods = { "testAddAccount" })
  public void testGetAccount() {

	  assertNotNull(adao.getAccount(id));
	  
  }
  
  @Test
  public void testGetAccountsList() {
	  
	  assertNotNull(adao.getAccountsByUser(u));
	  
  }
  
  @Test(dependsOnMethods = { "testAddAccount" })
  public void testUpdateAccount() {
	  
	  a = adao.getAccount(id);
	  a.setBalance(110.01);
	  
	  assertTrue(adao.updateAccount(a));
	  
  }
  
  @Test( dependsOnMethods = {"testUpdateAccount"} )
  public void verifyUpdate() {
	  a = adao.getAccount(id);
	  assertEquals(a.getBalance(), 110.01);
  }
  
  @Test( dependsOnMethods = {"testAddAccount","testUpdateAccount","verifyUpdate","testGetAccount"})
  public void testRemoveAccount() {
	  
	  a = adao.getAccount(id);
	  assertTrue(adao.removeAccount(a));
	  
  }
  
  @AfterClass
  public void cleanUser() {
	  udao.removeUser(u);
  }
  
}
