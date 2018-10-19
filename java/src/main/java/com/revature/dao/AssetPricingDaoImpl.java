package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.AssetPricing;
import com.revature.util.HibernateUtil;

public class AssetPricingDaoImpl implements AssetPricingDao {
	
	/*
	 * 	public void insertAssetPricing (AssetPricing assetPricing);
	public AssetPricing selectAssetPricingByTickerSymbol(String tickerSymbol);
	public List<AssetPricing> selectAllTrades();
	public Integer deleteAssetPricingByTickerSymbol(String tickerSymbol);
	public Integer updateAssetPricing(AssetPricing assetPricing);
	 */
	
	public long insertAssetPricing (AssetPricing assetPricing) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		Long id = null;
		
		try {
			tx = session.beginTransaction();
			id = (Long) session.save(assetPricing);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		
		return id;
	}
	
	public AssetPricing selectAssetPricingByTickerSymbol(String tickerSymbol) {
		Session session = HibernateUtil.getSession();
		AssetPricing assetPricing = null;
		
		try {
			Query query = session.createQuery("FROM AssetPricing WHERE tickerSymbol =:givenTickerSymbol");
			query.setParameter("givenTickerSymbol", tickerSymbol);
			assetPricing = (AssetPricing) query.uniqueResult();
		
		} catch (HibernateException e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
		
		return assetPricing;
		
	}
	
	public List<AssetPricing> selectAllTrades() {
		Session session = HibernateUtil.getSession();
		List<AssetPricing> allAssetsPricingList = null;
		
		try {
			Query query = session.createQuery("FROM AssetPricing");
			allAssetsPricingList = query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return allAssetsPricingList;
	}
	
	public boolean deleteAssetPricingByTickerSymbol(String tickerSymbol) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.delete(tickerSymbol);
			tx.commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			return false;
		} finally {
			session.close();
		}
	}
	
	public boolean updateAssetPricing(AssetPricing assetPricing) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.update(assetPricing);
			tx.commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			return false;
		} finally {
			session.close();
		}
	}
		

}



























