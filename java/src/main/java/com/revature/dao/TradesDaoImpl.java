package com.revature.dao;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Account;
import com.revature.beans.Trade;
import com.revature.beans.User;

import com.revature.util.HibernateUtil;
import com.revature.util.LogInterface;

public class TradesDaoImpl implements TradesDao {
	
	Logger log = LogInterface.logger;

	/**
	 * long insertTrades(Trade trades) - Inserts a new instance
	 * of Trade to be persisted in the DB. Returns a LONG
	 * of the generated ID for the new Trade upon success.
	 * Returns NULL if the insertion fails.
	 */
	public long insertTrades(Trade trades) {
		
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		Long id = null;

		try {
			tx = session.beginTransaction();
			id = (Long) session.save(trades);
			tx.commit();
			log.info("Successfully added a new Trade request to DB [ ID: " + id + " ]");
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			log.error("FAILED to add a new Trade request to DB");
		} finally {
			session.close();
		}

		return id;

	}

	/**
	 * Trade selectTradesByTradeId(Long tradeId) - Fetches a persisted
	 * instance of a Trade from the DB by its associated id that is passed in.
	 * Returns the specific trade instance upon successful fetch. Returns
	 * NULL upon failure.
	 */
	public Trade selectTradesByTradeId(Long tradeId) {
		Session session = HibernateUtil.getSession();
		Trade trades = null;

		try {
			Query query = session.createQuery("FROM Trade WHERE tradeId = :givenId");
			query.setParameter("givenId", tradeId);
			trades = (Trade) query.uniqueResult();
			log.info("Successfully retrieved Trade from the DB. [ ID: " + tradeId + " ]");

		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("FAILED to retrieve Trade from the DB. [ ID: " + tradeId + " ]");
		} finally {
			session.close();
		}

		return trades;

	}

	/**
	 * List<Trade> selectAllTrades() - Fetches all persisted instances
	 * of Trade from the DB. Returns a list of all persisted Trade
	 * upon success. Returns NULL upon failure.
	 */
	@Override
	public List<Trade> selectAllTrades() {
		Session session = HibernateUtil.getSession();
		List<Trade> alltradeslist = null;
		
		try {
			Query query = session.createQuery("FROM Trade");
			alltradeslist = query.list();
			log.info("Successfully fetched all Trades from DB");
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("FAILED to fetch all Trades from DB");
		} finally {
			session.close();
		}
		return alltradeslist;
	}

	/**
	 * boolean deleteTradesByTradeId(Trade delTrade) - Removes
	 * a persisted Trade from the database matching the given
	 * Trade instance passed in. Returns TRUE upon successful
	 * removal, returns FALSE upon failure.
	 */
	@Override
	public boolean deleteTradesByTradeId(Trade delTrade) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.delete(delTrade);
			tx.commit();
			log.info("Successfully removed Trade from the database.\n"
					+"Details (of removed trade): " + delTrade.toString());
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			log.error("FAILED to remove Trade from the database.\n"
					+"Details (of trade): " + delTrade.toString());
			return false;
		} finally {
			session.close();
		}
		
	}

	/**
	 * boolean updateTrades(Trade upTrade) - Updates a persisted
	 * Trade in the DB to the given instance of Trade. Returns
	 * TRUE if the update succeeds. Returns FALSE if the update
	 * fails.
	 */
	@Override
	public boolean updateTrades(Trade upTrade) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.update(upTrade);
			tx.commit();
			log.info("Successfully updated Trade in DB.\n"
					+"Updated Details: " + upTrade.toString());
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			log.error("FAILED to update Trade in DB.");
			return false;
		} finally {
			session.close();
		}
	}

	/**
	 * List<Trade> selectTradesByRequestorAccount(Account a) - Fetches
	 * a list of all persisted Trades in the DB belonging to the provided
	 * Account instance belonging to the Trade Requestor.
	 * Returns a List of all Trades belonging to the account
	 * upon successful fetch. Returns NULL if the fetch fails.
	 */
	@Override
	public List<Trade> selectTradesByRequestorAccount(Account a) {
		
		List<Trade> trades = null;
		Session session = HibernateUtil.getSession();
		
		try {
			Query query = session.createQuery("FROM Trade WHERE requesterAccount = :givenAccount");
			query.setParameter("givenAccount", a);
			trades = query.list();
			log.info("Successfully retrieved all Trades belonging to REQUESTOR Account [ Account ID: " + a.getId() + " ]");
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("FAILED to retrieve all Trades belonging to REQUESTOR Account [ Account ID: " + a.getId() + " ]");
		}
		finally {
			session.close();
		}
		
		return trades;
		
	}

	/**
	 * List<Trade> selectTradesByReceiverAccount(Account a) - Retrieves
	 * all persisted Trades from the DB belonging to the given Reciever
	 * Account instance. Returns a List of all Trades belonging to the
	 * account upon successful fetch. Returns NULL upon failure.
	 */
	@Override
	public List<Trade> selectTradesByReceiverAccount(Account a) {
		List<Trade> trades = null;
		Session session = HibernateUtil.getSession();
		
		try {
			Query query = session.createQuery("FROM Trade WHERE receiverAccount = :givenAccount");
			query.setParameter("givenAccount", a);
			trades = query.list();
			log.info("Successfully retrieved all Trades belonging to RECIEVER Account [ Account ID: " + a.getId() + " ]");
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("FAILED to retrieve all Trades belonging to RECIEVER Account [ Account ID: " + a.getId() + " ]");
		}
		finally {
			session.close();
		}
		
		return trades;
	}
	
	@Override
	public List<Trade> selectAllTradesPendingBrokerApproval(){
		List<Trade> trades = null;
		Session session = HibernateUtil.getSession();
		
		try {
			Query query = session.createQuery("FROM Trade WHERE brokerStatus = 0");
			trades = query.list();
			log.info("Successfully retrieved all Trades needing Broker approval");
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("FAILED to retrieve all Trades needing Broker approval");
		}
		finally {
			session.close();
		}
		
		return trades;
	}

}
