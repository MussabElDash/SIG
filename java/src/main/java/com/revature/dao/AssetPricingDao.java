package com.revature.dao;

import java.util.List;

import com.revature.beans.AssetPricing;

public interface AssetPricingDao {
	
	public long insertAssetPricing (AssetPricing assetPricing);
	public AssetPricing selectAssetPricingByTickerSymbol(String tickerSymbol);
	public List<AssetPricing> selectAllTrades();
	public boolean deleteAssetPricingByTickerSymbol(String tickerSymbol);
	public boolean updateAssetPricing(AssetPricing assetPricing);

}
