package com.revature.testng;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.sql.Date;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.beans.Account;
import com.revature.beans.AssetPricing;
import com.revature.beans.Security;
import com.revature.beans.Trade;
import com.revature.beans.User;
import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.AssetPricingDao;
import com.revature.dao.AssetPricingDaoImpl;
import com.revature.dao.SecurityDao;
import com.revature.dao.SecurityDaoImpl;
import com.revature.dao.TradesDao;
import com.revature.dao.TradesDaoImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;

public class TestTradesDAOImpl {
	
	/*
	 * What constraints does trades have in the DB?
	 * - Requser
	 * - account
	 * - security 
	 */
	
	TradesDao tdao = new TradesDaoImpl();
	UserDAO udao = new UserDAOImpl();
	AccountDAO adao = new AccountDAOImpl();
	AssetPricingDao apdao = new AssetPricingDaoImpl();
	SecurityDao sdao = new SecurityDaoImpl();
	
	Trade trade = null;
	User Requser = new User("tester", 
			"pass", 
			"firstname", 
			"lastname", 
			"123 Easy St", 
			"Bobbitown", 
			"TX", 
			75020, 
			123456789, 
			new Date(System.currentTimeMillis()), 
			1234567890, 
			0);
	User Recuser = new User("testing", 
			"pass", 
			"firstname", 
			"lastname", 
			"123 Easy St", 
			"Bobbitown", 
			"TX", 
			75020, 
			123456789, 
			new Date(System.currentTimeMillis()), 
			1234567890, 
			0);
	Account Reqacct = new Account(0, 
			"Brokerage", 
			"myBrokerage", 
			0.0, 
			Requser);
	Account Recacct = new Account(0, 
			"Brokerage1", 
			"myBrokerage1", 
			0.0, 
			Requser);
	AssetPricing ap = new AssetPricing("TEST", 
			"Google", 
			1103.00);
	Security Reqsec = new Security((long) 0, "Stock", "Google", ap, 5, Reqacct);
	Security Recsec = new Security((long) 0, "Stock", "Google", ap, 5, Recacct);
	Long id;
	Long nullid;
	
	@BeforeClass
	public void putSetup() {
		udao.addUser(Requser);
		udao.addUser(Recuser);
		adao.addAccount(Reqacct);
		adao.addAccount(Recacct);
		apdao.insertAssetPricing(ap);
		sdao.insertSecurity(Reqsec);
		sdao.insertSecurity(Recsec);
	}
	
	@Test
	public void testInsertTrades() {
		trade = new Trade();
		trade.setRequesterAccount(Reqacct);
		trade.setReceiverAccount(Recacct);
		trade.setBrokerStatus(1);
		trade.setReceiverApproval(1);
		trade.setRequesterSecurity(Reqsec);
		trade.setReceiverSecutiy(Recsec);
		trade.setAmountrequester(5);
		trade.setAmountReceiver(5);
		trade.setCreatedOn(new Date(System.currentTimeMillis()));
		
		id = tdao.insertTrades(trade);
		
		trade.setId(id);
		assertNotEquals(id,null);
	
	}
	
	@Test( dependsOnMethods = { "testInsertTrades" })
	public void testSelectTradeByTradeId ()  {
		
		assertNotNull(tdao.selectTradesByTradeId(trade.getId()));
	}
	
	@Test( dependsOnMethods = { "testInsertTrades" })
	public void testUpdateTrade () {
		
		trade.setAmountrequester(9);
		assertTrue(tdao.updateTrades(trade));
	}
	
	@Test( dependsOnMethods = { "testInsertTrades", "testUpdateTrade", "testSelectTradeByTradeId" })
	public void testDeleteTradesByTradeId () {
		
		assertTrue(tdao.deleteTradesByTradeId(trade));
	}
	
	@Test( dependsOnMethods = { "testInsertTrades" })
	public void testSelectAllTrades () {
		
		assertNotNull(tdao.selectAllTrades());
	}
	
	@Test( dependsOnMethods = { "testInsertTrades" })
	public void selectTradeByRequestorAccount() {
		
		assertNotNull(tdao.selectTradesByRequestorAccount(Reqacct));
	}
	
	@Test( dependsOnMethods = { "testInsertTrades" })
	public void selectTradeByReceiverAccount() {
		
		assertNotNull(tdao.selectTradesByReceiverAccount(Recacct));
	}
	
	@AfterClass
	public void removeObjects () {
		sdao.removeSecurity(Reqsec);
		sdao.removeSecurity(Recsec);
		adao.removeAccount(Reqacct);
		adao.removeAccount(Recacct);
		udao.removeUser(Requser);
		udao.removeUser(Recuser);
		apdao.deleteAssetPricingByTickerSymbol("TEST");
		
	}
	

}















