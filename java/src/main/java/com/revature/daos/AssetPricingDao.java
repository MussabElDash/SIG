package com.revature.daos;

import java.util.List;

import com.revature.beans.AssetPricing;

public interface AssetPricingDao {
	
	public void insertAssetPricing (AssetPricing assetPricing);
	public AssetPricing selectAssetPricingByTickerSymbol(String tickerSymbol);
	public List<AssetPricing> selectAllTrades();
	public Integer deleteAssetPricingByTickerSymbol(String tickerSymbol);
	public Integer updateAssetPricing(AssetPricing assetPricing);

}
