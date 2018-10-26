package com.revature.dao;

import java.util.List;

import org.hibernate.Transaction;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.revature.beans.Account;
import com.revature.beans.User;
import com.revature.util.HibernateUtil;
import com.revature.util.LogInterface;

public class AccountDAOImpl implements AccountDAO{

	Logger log = LogInterface.logger;
	
	/**
	 * getAccount(int id) - Fetch an account from the database
	 * by its ID (primary key).
	 */
	@Override
	public Account getAccount(long id) {
		
		Account a = null;
		Session session = HibernateUtil.getSession();
		
		try {
			
			Query query = session.createQuery("FROM Account WHERE id = :givenId");
			query.setParameter("givenId", id);
			a = (Account) query.uniqueResult();
			log.info("Account successfully retrieved: " + a.toString());
			
		}
		catch(HibernateException e) {
			e.printStackTrace();
			log.error("FAILED to retrieve account.");
		}
		finally {
			session.close();
		}
		
		return a;
		
	}

	/**
	 * getAccountsByUser(User u) - Fetch a List (java.util.List) of the Account
	 * objects belonging to the user provided.
	 */
	@Override
	public List<Account> getAccountsByUser(User u) {

		List<Account> accounts = null;
		Session session = HibernateUtil.getSession();
		
		try {
			
			Query query = session.createQuery("FROM Account WHERE owner = :givenUser");
			query.setParameter("givenUser", u);
			
			accounts = query.list();
			log.info("Succesfully retrieved accounts for user [ " + u.getUsername() + " ]");
			
		}
		catch(HibernateException e) {
			e.printStackTrace();
			log.info("FAILED to retrieve accounts for user [ " + u.getUsername() + " ]");
		}
		finally {
			session.close();
		}
		
		return accounts;
		
	}

	/**
	 * addAccount(Account a) - Add a new persisted Account instance with the Account instance
	 * passed in. Provides Long of the new instance ID, or NULL if insertion fails.
	 */
	@Override
	public Long addAccount(Account a) {

		Session s = HibernateUtil.getSession();
		Transaction t = null;
		Long id = null;
		
		try {
			t = s.beginTransaction();
			id = (Long) s.save(a);
			t.commit();
			log.info("Succesfully added new Account [ ID: " + id + " ]");
			return id;
		}
		catch(HibernateException e) {
			e.printStackTrace();
			t.rollback();
			log.error("FAILED to add new Account.");
			return id;
		}
		finally {
			s.close();
		}
		
	}

	/**
	 * removeAccount(Account a) - Remove a persisted Account instance of the Account instance
	 * passed in. Returns TRUE if removal succeeds, returns FALSE if removal fails.
	 */
	@Override
	public boolean removeAccount(Account a) {
		
		Session s = HibernateUtil.getSession();
		Transaction t = null;
		
		try {			
			t = s.beginTransaction();
			s.delete(a);
			t.commit();
			log.info("Succesfully removed Account [ ID: " + a.getId() + " ]");
			return true;			
		}
		catch(HibernateException e) {
			e.printStackTrace();
			t.rollback();
			log.info("FAILED to remove Account [ ID: " + a.getId() + " ]");
			return false;
		}
		finally {
			s.close();
		}
		
	}

	/**
	 * updateAccount(Account a) - Update persisted Account instance to the given
	 * account passed in. Returns TRUE if update succeeds, returns FALSE if 
	 * update fails.
	 */
	@Override
	public boolean updateAccount(Account a) {

		Session s = HibernateUtil.getSession();
		Transaction t = null;
		
		try {
			t = s.beginTransaction();
			s.update(a);
			t.commit();
			log.info("Successfully updated account [ ID: " + a.getId() + " ]");
			return true;
		}
		catch(HibernateException e) {
			e.printStackTrace();
			t.rollback();
			log.error("FAILED to update account [ ID: " + a.getId() + " ]");
			return false;
		}
		finally {
			s.close();
		}
	}

	
	
}
