package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Account;
import com.revature.beans.Trades;
import com.revature.beans.User;

import com.revature.util.HibernateUtil;

public class TradesDaoImpl implements TradesDao {

	public long insertTrades(Trades trades) {
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

	public Trades selectTradesByTradeId(Long tradeId) {
		Session session = HibernateUtil.getSession();
		Trades trades = null;

		try {
			Query query = session.createQuery("FROM Trades WHERE tradeId = :givenId");
			query.setParameter("givenId", tradeId);
			trades = (Trades) query.uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}

		return trades;

	}
	
	
	
	@Override
	public List<Trades> selectTradesByUser(User user) {
		Session session = HibernateUtil.getSession();
		List<Trades> usertradelist = null;
		
		try {
			Query query = session.createQuery("FROM Trades WHERE user = :givenUser");
			query.setParameter("givenUser", user);
			usertradelist = query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return usertradelist;
	}

	@Override
	public List<Trades> selectAllTrades() {
		Session session = HibernateUtil.getSession();
		List<Trades> alltradeslist = null;
		
		try {
			Query query = session.createQuery("FROM Trades");
			alltradeslist = query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return alltradeslist;
	}

	@Override
	public boolean deleteTradesByTradeId(Trades delTrade) {
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
	public boolean updateTrades(Trades upTrade) {
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
	public List<Trades> selectTradesByAccount(Account a) {
		
		List<Trades> trades = null;
		Session session = HibernateUtil.getSession();
		
		try {
			Query query = session.createQuery("FROM Trades");
		} catch (HibernateException e) {
			// TODO: handle exception
		}
		
		return trades;
		
	}

}
