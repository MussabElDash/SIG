package com.revature.testng;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.beans.AssetPricing;
import com.revature.dao.AssetPricingDao;
import com.revature.dao.AssetPricingDaoImpl;

public class TestAssetPricingDaoImpl {
	
	AssetPricingDao asdao = new AssetPricingDaoImpl();
	AssetPricing as = null;
	
	@BeforeClass
	public void setupPricing() {
		as = new AssetPricing();
		as.setTickerSymbol("DOOD");
		as.setCompanyName("Chill LLC.");
		as.setPrice(0.0);
	}
	
	@Test
	public void testAddAssetPricing() {
		assertNotEquals(asdao.insertAssetPricing(as),false);
	}
	
	@Test ( dependsOnMethods = { "testAddAssetPricing" } )
	public void testSelectAssetPricingByTickerSymbol() {
		assertNotEquals(asdao.selectAssetPricingByTickerSymbol("DOOD"),null);
	}
	
	@Test
	public void testSelectAllAssetPricing() {
		assertNotNull(asdao.selectAllAssetPricings());
	}
	
	@Test ( dependsOnMethods = { "testAddAssetPricing" })
	public void testUpdateAssetPricing(){
		as.setCompanyName("Chill, Yo. LLC.");
		assertTrue(asdao.updateAssetPricing(as));
	}
	
	@Test ( dependsOnMethods = { "testUpdateAssetPricing" } )
	public void testVerifyAssetPricingUpdate() {
		assertEquals(asdao.selectAssetPricingByTickerSymbol("DOOD").getCompanyName(),"Chill, Yo. LLC.");
	}
	
	@Test ( dependsOnMethods = { "testVerifyAssetPricingUpdate" })
	public void testRemoveAssetPricing() {
		assertTrue(asdao.removeAssetPricing(as));
	}
	
}
