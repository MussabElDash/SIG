package com.revature.dao;

import java.util.List;

import com.revature.beans.AssetPricing;

public interface AssetPricingDao {
	
	public boolean insertAssetPricing (AssetPricing assetPricing);
	public AssetPricing selectAssetPricingByTickerSymbol(String tickerSymbol);
	public List<AssetPricing> selectAllAssetPricings();
	public boolean deleteAssetPricingByTickerSymbol(String tickerSymbol);
	public boolean updateAssetPricing(AssetPricing assetPricing);
	public boolean removeAssetPricing(AssetPricing assetPricing);

}
