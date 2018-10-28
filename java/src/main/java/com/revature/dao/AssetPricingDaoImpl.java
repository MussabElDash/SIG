package com.revature.dao;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.AssetPricing;
import com.revature.util.HibernateUtil;
import com.revature.util.LogInterface;

public class AssetPricingDaoImpl implements AssetPricingDao {

	Logger log = LogInterface.logger;

	/**
	 * insertAssetPricing(AssetPricing assetPricing) - Inserts
	 * a new persisted AssetPricing instance into the database.
	 * Returns a LONG of the new ID generated for the instance
	 * if insert succeeds. Returns NULL if the insertion fails.
	 */
	public boolean insertAssetPricing(AssetPricing assetPricing) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.save(assetPricing);
			tx.commit();
			log.info("New Asset Pricing successfully added into the DB [ Ticker: " + assetPricing.getTickerSymbol() + " ]");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			log.error("FAILED to add new Asset Pricing to the DB.");
			return false;
		} finally {
			session.close();
		}

	}

	/**
	 * selectAssetPricingByTickerSymbol(String tickerSymbol) - Gets a persisted
	 * instance of AssetPricing from DB by its Ticker Symbol (Primary Key).
	 * Produces one AssetPricing instance. Returns NULL if select fails.
	 */
	public AssetPricing selectAssetPricingByTickerSymbol(String tickerSymbol) {
		Session session = HibernateUtil.getSession();
		AssetPricing assetPricing = null;

		try {
			Query query = session.createQuery("FROM AssetPricing WHERE tickerSymbol =:givenTickerSymbol");
			query.setParameter("givenTickerSymbol", tickerSymbol);
			assetPricing = (AssetPricing) query.uniqueResult();
			log.info("Successfully fetched Asset Pricing: " + assetPricing.toString());	
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("FAILED to fetch Asset Pricing [ Ticker: " + tickerSymbol + " ]");
		} finally {
			session.close();
		}

		return assetPricing;

	}

	/**
	 * List<AssetPricing> selectAllAssetPricings() - Gets all persisted
	 * instances of AssetPricing from the DB and returns a List containing
	 * them. Returns NULL if selection fails.
	 */
	@SuppressWarnings("unchecked")
	public List<AssetPricing> selectAllAssetPricings() {
		Session session = HibernateUtil.getSession();
		List<AssetPricing> allAssetsPricingList = null;

		try {
			Query query = session.createQuery("FROM AssetPricing");
			allAssetsPricingList = query.list();
			log.info("Succesfully fetched all Asset Pricing from the DB.");
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("FAILED to fetch all AssetPricing from the DB.");
		} finally {
			session.close();
		}
		return allAssetsPricingList;
	}

	/**
	 * deleteAssetPricingByTickerSymbol(String tickerSymbol) - Removes the
	 * persisted instance of AssetPricing from the DB by its Ticker Symbol
	 * (Primary Key). Returns TRUE if removal succeeds, returns FALSE
	 * if the removal fails.
	 */
	public boolean deleteAssetPricingByTickerSymbol(String tickerSymbol) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.delete(tickerSymbol);
			tx.commit();
			log.info("Successfully removed Asset Pricing [ Ticker Symbol: " + tickerSymbol + " ]");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			log.error("FAILED to remove Asset Pricing [ Ticker Symbol: " + tickerSymbol + " ]");
			return false;
		} finally {
			session.close();
		}
	}

	/**
	 * updateAssetPricing(AssetPricing assetPricing) - Updates the persisted
	 * instance of Asset Pricing to the given instance. Returns TRUE if the
	 * update succeeds, returns FALSE if the update fails.
	 */
	public boolean updateAssetPricing(AssetPricing assetPricing) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.update(assetPricing);
			tx.commit();
			log.info("Successfully updated Asset Pricing [ Ticker Symbol: " + assetPricing.getTickerSymbol() + " ]");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			log.error("FAILED to update Asset Pricing [ Ticker Symbol: " + assetPricing.getTickerSymbol() + " ]");
			return false;
		} finally {
			session.close();
		}
	}

}