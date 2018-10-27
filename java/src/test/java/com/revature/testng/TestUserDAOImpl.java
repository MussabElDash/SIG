package com.revature.testng;

import java.sql.Date;

import org.testng.annotations.Test;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;


import com.revature.beans.User;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;

public class TestUserDAOImpl {
	
	UserDAO udao = new UserDAOImpl();
	User u = null;
		
	@Test(priority = 1)
	public void testAddUser() {
		  
		 u = new User("tester",
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
		 
		 assertEquals(udao.addUser(u),true);
		  
	}
	
	@Test
	public void testAddUserWithNullPass() {
		  
		 u = new User("testerTwo",
				 null,
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
		 
		 assertEquals(udao.addUser(u),false);
		  
	}
	
	@Test
	public void testAddUserWithNullUsername() {
		  
		 u = new User(null,
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
		 
		 assertEquals(udao.addUser(u),false);
		  
	}
	
	@Test
	public void testAddUserWithNullLastName() {
		  
		 u = new User("testerTwo",
				 "pass",
				 "firstname",
				 null,
				 "123 Easy St",
				 "Bobbitown", 
				 "YM",
				 12345,
				 123456789,
				 new Date(System.currentTimeMillis()),
				 1234567890,
				 0);
		 
		 assertEquals(udao.addUser(u),false);
		  
	}
	
	@Test
	public void testAddUserNullAddress() {
		  
		 u = new User("testerTwo",
				 "pass",
				 "firstname",
				 "lastname",
				 null,
				 "Bobbitown", 
				 "YM",
				 12345,
				 123456789,
				 new Date(System.currentTimeMillis()),
				 1234567890,
				 0);
		 
		 assertEquals(udao.addUser(u),false);
		  
	}
	
	@Test
	public void testAddUserNullCity() {
		  
		 u = new User("testerTwo",
				 "pass",
				 "firstname",
				 "lastname",
				 "123 Easy St",
				 null, 
				 "YM",
				 12345,
				 123456789,
				 new Date(System.currentTimeMillis()),
				 1234567890,
				 0);
		 
		 assertEquals(udao.addUser(u),false);
		  
	}
	
	@Test
	public void testAddUserNullState() {
		  
		 u = new User("testerTwo",
				 "pass",
				 "firstname",
				 "lastname",
				 "123 Easy St",
				 "Bobbitown", 
				 null,
				 12345,
				 123456789,
				 new Date(System.currentTimeMillis()),
				 1234567890,
				 0);
		 
		 assertEquals(udao.addUser(u),false);
		  
	}
	
	@Test
	public void testAddUserNullZip() {
		  
		 u = new User("testerTwo",
				 "pass",
				 "firstname",
				 "lastname",
				 "123 Easy St",
				 "Bobbitown", 
				 "YM",
				 null,
				 123456789,
				 new Date(System.currentTimeMillis()),
				 1234567890,
				 0);
		 
		 assertEquals(udao.addUser(u),false);
		  
	}
	
	@Test
	public void testAddUserNullSSN() {
		  
		 u = new User("testerTwo",
				 "pass",
				 "firstname",
				 "lastname",
				 "123 Easy St",
				 "Bobbitown", 
				 "YM",
				 12345,
				 null,
				 new Date(System.currentTimeMillis()),
				 1234567890,
				 0);
		 
		 assertEquals(udao.addUser(u),false);
		  
	}
	
	@Test
	public void testAddUserNullDOB() {
		  
		 u = new User("testerTwo",
				 "pass",
				 "firstname",
				 "lastname",
				 "123 Easy St",
				 "Bobbitown", 
				 "YM",
				 12345,
				 123456789,
				 null,
				 1234567890,
				 0);
		 
		 assertEquals(udao.addUser(u),false);
		  
	}
	
	@Test
	public void testAddUserNullPhone() {
		  
		 u = new User("testerTwo",
				 "pass",
				 "firstname",
				 "lastname",
				 "123 Easy St",
				 "Bobbitown", 
				 "YM",
				 12345,
				 123456789,
				 new Date(System.currentTimeMillis()),
				 null,
				 0);
		 
		 assertEquals(udao.addUser(u),false);
		  
	}
	
	@Test(dependsOnMethods = { "testAddUser" })
	public void testGetUser() {
		  
		 assertNotEquals(udao.getUser("tester"),null);
		  
	}
	
	@Test(dependsOnMethods = { "testAddUser","testGetUser" })
	public void testUpdateUser() {
		
		u = udao.getUser("tester");
		
		u.setCity("Pleasentville");
		
		assertTrue(udao.updateUser(u));
		
	}
	
	@Test(dependsOnMethods = { "testAddUser","testGetUser","testUpdateUser" })
	public void testRemoveUser() {
		
		u = udao.getUser("tester");
		assertTrue(udao.removeUser(u));
		
	}
	
	
	  
}
