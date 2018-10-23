package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Account;
import com.revature.beans.Trade;
import com.revature.beans.User;

import com.revature.util.HibernateUtil;

public class TradesDaoImpl implements TradesDao {

	public long insertTrades(Trade trades) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		Long id = null;

		try {
			tx = session.beginTransaction();
			id = (Long) session.save(trades);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}

		return id;

	}

	public Trade selectTradesByTradeId(Long tradeId) {
		Session session = HibernateUtil.getSession();
		Trade trades = null;

		try {
			Query query = session.createQuery("FROM Trade WHERE tradeId = :givenId");
			query.setParameter("givenId", tradeId);
			trades = (Trade) query.uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}

		return trades;

	}

	@Override
	public List<Trade> selectAllTrades() {
		Session session = HibernateUtil.getSession();
		List<Trade> alltradeslist = null;
		
		try {
			Query query = session.createQuery("FROM Trade");
			alltradeslist = query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return alltradeslist;
	}

	@Override
	public boolean deleteTradesByTradeId(Trade delTrade) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.delete(delTrade);
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

	@Override
	public boolean updateTrades(Trade upTrade) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.update(upTrade);
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

	@Override
	public List<Trade> selectTradesByRequestorAccount(Account a) {
		
		List<Trade> trades = null;
		Session session = HibernateUtil.getSession();
		
		try {
			Query query = session.createQuery("FROM Trade WHERE requesterAccount = :givenAccount");
			query.setParameter("givenAccount", a);
			trades = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		
		return trades;
		
	}

	@Override
	public List<Trade> selectTradesByReceiverAccount(Account a) {
		List<Trade> trades = null;
		Session session = HibernateUtil.getSession();
		
		try {
			Query query = session.createQuery("FROM Trade WHERE receiverAccount = :givenAccount");
			query.setParameter("givenAccount", a);
			trades = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		
		return trades;
	}

}
