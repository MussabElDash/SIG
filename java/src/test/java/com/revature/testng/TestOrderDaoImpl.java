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
import com.revature.beans.Order;
import com.revature.beans.User;
import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.AssetPricingDao;
import com.revature.dao.AssetPricingDaoImpl;
import com.revature.dao.OrderDao;
import com.revature.dao.OrderDaoImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;

public class TestOrderDaoImpl {

	AccountDAO adao = new AccountDAOImpl();
	UserDAO udao = new UserDAOImpl();
	AssetPricingDao apdao = new AssetPricingDaoImpl();
	OrderDao odao = new OrderDaoImpl();

	AssetPricing ap = null;
	Account a = null;
	User u = null;
	Order o = null;
	Order bo = null;

	Long validid;
	Long invalidid;

	@BeforeClass
	public void setupDependantObjects() {

		ap = new AssetPricing("DOOF", "Bobbitown Chips", 0.0);

		u = new User("tester", "pass", "firstname", "lastname", "123 Easy St", "Bobbitown", "YM", 12345, 123456789,
				new Date(System.currentTimeMillis()), 1234567890, 0);

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
	public void testInsertOrder() {

		o = new Order();
		o.setOwnerAccount(a);
		o.setAp(ap);
		o.setAmount(0);
		o.setBrokerStatus(0);
		o.setCreatedOn(new Date(System.currentTimeMillis()));

		validid = odao.insertOrder(o);

		assertNotEquals(validid, null);

	}

	@Test(dependsOnMethods = { "testInsertOrder" })
	public void testSelectOrderById() {

		assertNotEquals(odao.selectOrderById(validid), null);

	}

	@Test(dependsOnMethods = { "testInsertOrder" })
	public void testGetAllOrders() {

		assertNotEquals(odao.getAllOrders(), null);

	}

	@Test(dependsOnMethods = { "testInsertOrder" })
	public void testGetOrdersByAccount() {

		assertNotEquals(odao.getOrdersByAccount(a), null);

	}

	@Test(dependsOnMethods = { "testInsertOrder" })
	public void testUpdateOrder() {

		o.setAmount(1);
		assertTrue(odao.updateOrder(o));

	}

	@Test(dependsOnMethods = { "testUpdateOrder" })
	public void verifyUpdateTest() {

		o = odao.selectOrderById(validid);
		assertEquals(o.getAmount().intValue(), 1);

	}

	@Test(dependsOnMethods = { "testInsertOrder", "verifyUpdateTest", "testSelectOrderById", "testGetOrdersByAccount" })
	public void testRemoveOrder() {

		assertTrue(odao.removeOrderById(validid.longValue()));

	}

	@AfterClass
	public void flushObjects() {

		adao.removeAccount(a);
		udao.removeUser(u);
		apdao.removeAssetPricing(ap);

	}

}
