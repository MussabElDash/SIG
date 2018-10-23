package com.revature.services;

import java.util.List;

import com.revature.beans.AssetPricing;
import com.revature.dao.AssetPricingDao;
import com.revature.dao.AssetPricingDaoImpl;

public class AssetPricingService {
	private static AssetPricingDao dao;

	public static AssetPricingDao setDaoIfNotSet(AssetPricingDao Dao) {
		if (dao == null || Dao == null) {
			dao = Dao;
		}
		return dao;
	}

	public static List<AssetPricing> getAllAssets() {
		return setDaoIfNotSet(new AssetPricingDaoImpl()).selectAllAssetPricings();
	}

	public static boolean updateAsset(AssetPricing asset) {
		return setDaoIfNotSet(new AssetPricingDaoImpl()).updateAssetPricing(asset);
	}
}
