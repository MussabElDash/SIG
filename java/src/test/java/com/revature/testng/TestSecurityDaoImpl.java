package com.revature.testng;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.sql.Date;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.beans.Account;
import com.revature.beans.AssetPricing;
import com.revature.beans.Security;
import com.revature.beans.User;
import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.AssetPricingDao;
import com.revature.dao.AssetPricingDaoImpl;
import com.revature.dao.SecurityDao;
import com.revature.dao.SecurityDaoImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;

public class TestSecurityDaoImpl {
	
	AssetPricingDao apdao = new AssetPricingDaoImpl();
	AccountDAO adao = new AccountDAOImpl();
	UserDAO udao = new UserDAOImpl();
	SecurityDao sdao = new SecurityDaoImpl();
	
	AssetPricing ap = null;
	Account a = null;
	User u = null;
	Security s = null;
	
	Long validid = null;
	
	@BeforeClass
	public void setupObjects(){
		
		ap = new AssetPricing("DOOF", 
				"Bobbitown Chips", 
				0.0);
		
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
		
		a = new Account();
		a.setAccountType("IRA");
		a.setAccountName("Vaca");
		a.setBalance(0.0);
		a.setOwner(u);
		
		apdao.insertAssetPricing(ap);
		udao.addUser(u);
		adao.addAccount(a);
		
	}
	
	@Test
	public void testAddSecurity() {
		
		s = new Security();
		s.setType("Dollar");
		s.setAp(ap);
		s.setOwnerAccount(a);
		s.setAmount(0);
		
		validid = sdao.insertSecurity(s);
		
		s.setId(validid);
		
		assertNotEquals(validid,null);
		
	}
	
	@Test ( dependsOnMethods = { "testAddSecurity" })
	public void testGetSecurity() {
		
		assertNotEquals(sdao.selectSecurityById(validid),null);
		
	}
	
	@Test ( dependsOnMethods = { "testAddSecurity" })
	public void testGetAllSecurities() {
		
		assertNotEquals(sdao.getAllSecurities(),null);
		
	}
	
	@Test ( dependsOnMethods = { "testAddSecurity" })
	public void testGetSecuritiesByAccount() {
		
		assertNotEquals(sdao.getSecuritiesByAccount(a),null);
		
	}
	
	@Test( dependsOnMethods = { "testAddSecurity" })
	public void testUpdateSecurity() {
	
		s.setAmount(2);
		assertTrue(sdao.updateSecurity(s));
	
	}
	
	@Test ( dependsOnMethods = { "testUpdateSecurity" })
	public void testUpdateValidity() {
		assertEquals(sdao.selectSecurityById(validid).getAmount().intValue(),2);
	}
	
	@Test ( dependsOnMethods = { "testUpdateValidity" })
	public void testRemoveSecurity() {
		assertTrue(sdao.removeSecurityById(validid));
	}
	
	@AfterClass
	public void removeObjects() {
		
		apdao.removeAssetPricing(ap);
		adao.removeAccount(a);
		udao.removeUser(u);
		
	}
	
}
