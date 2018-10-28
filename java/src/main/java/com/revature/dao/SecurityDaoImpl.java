package com.revature.dao;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Account;
import com.revature.beans.Security;
import com.revature.util.HibernateUtil;
import com.revature.util.LogInterface;

public class SecurityDaoImpl implements SecurityDao{

	Logger log = LogInterface.logger;
	
	/**
	 * Long insertSecurity(Security security) - Adds a new instance of
	 * Security to persist to the DB. Returns a LONG of the instance's
	 * generated ID upon success. Returns NULL if the insertion fails.
	 */
	@Override
	public Long insertSecurity(Security security) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		Long id = null;
		
		try {
			tx = session.beginTransaction();
			id = (Long)session.save(security); //Returns the id of the fresh insert
			tx.commit();
			log.info("Successfully added a new Security to the DB [ ID: " + id + " ]");
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			log.info("FAILED to add a new Security to the DB [ ID: " + id + " ]");
		}finally {
			session.close();
		}
		
		return id;
	}

	/**
	 * List<Security> getAllSecurities() - Fetches a list of all persisted
	 * Securities from the database. Returns a list of all securities in the DB
	 * upon success. Returns NULL if the select fails.
	 */
	@Override
	public List<Security> getAllSecurities() {
		List<Security> securities = null;
		Session session = HibernateUtil.getSession();
		
		try {

			securities = session.createQuery("FROM Security").list();
			log.info("Successfully retrieved all Securities from the Database.");
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("FAILED to retrieve all Securities from the Database.");
		} finally {
			session.close();
		}
		return securities;
	}

	/**
	 * Security selectSecurityById(Long id) - Selects a specific persisted
	 * instance of Security by the given id (primary key). Returns the instance
	 * owning the ID upon success. Returns NULL upon failure.
	 */
	@Override
	public Security selectSecurityById(Long id) {
		Session session = HibernateUtil.getSession();
		Security s = null;
		
		try {
			
			Query query = session.createQuery("FROM Security WHERE id = :givenId");
			query.setParameter("givenId", id.longValue());
			s = (Security) query.uniqueResult();
			log.info("Successfully retrieved Security from DB [ ID: " + id + " ]");
			
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("FAILED to retrieve Security from DB [ ID: " + id + " ]");
		} finally {
			session.close();
		}
		
		return s;
	}

	/**
	 * boolean updateSecurity(Security s) - Updates a persisted instance
	 * of Security in the DB to the given Security object passed in.
	 * Returns TRUE if the update succeeds. Returns FALSE if the update
	 * fails.
	 */
	@Override
	public boolean updateSecurity(Security s) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.update(s);
			tx.commit();
			log.info("Successfully updated Security in DB [ ID: " + s.getId() + " ]");
			return true;
			
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
			log.error("FAILED to update Security in DB [ ID: " + s.getId() + " ]");
			return false;
		} finally {
			session.close();
		}

	}

	/**
	 * void removeSecurityById(Long id) - Removes a persisted Security
	 * instance from the DB by its ID.
	 */
	@Override
	public boolean removeSecurityById(Long id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			session.delete(session.get(Security.class, id));
			
			tx.commit();
			log.info("Successfully removed Security from the DB [ ID: " + id + " ]");
			return true;
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
			log.error("FAILED to remove Security from the DB [ ID: " + id + " ]");
			return false;
		} finally {
			session.close();
		}
		
	}

	/**
	 * List<Security> getSecuritiesByAccount(Account acct) - Retrieves all
	 * persisted instances of Securities belonging to the given Account
	 * instance passed in. Returns a list containing those instances upon
	 * success, returns NULL if the select fails.
	 */
	@Override
	public List<Security> getSecuritiesByAccount(Account acct) {
		
		List<Security> securities = null;
		Session session = HibernateUtil.getSession();
		
		try {
			
			Query query = session.createQuery("FROM Security WHERE ownerAccount = :givenAcct");
			query.setParameter("givenAcct", acct);
			
			securities = query.list();
			log.info("Successfully retrieved a list of all Securities belonging to Account [ Account ID: " + acct.getId() + " ]");
			
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("FAILED to retrieve a list of all Securities belonging to Account [ Account ID: " + acct.getId() + " ]");
		} finally {
			session.close();
			
		}
		
		return securities;
	}

}
