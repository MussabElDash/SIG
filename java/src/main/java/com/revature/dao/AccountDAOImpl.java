package com.revature.dao;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.revature.beans.Account;
import com.revature.beans.User;
import com.revature.util.HibernateUtil;

public class AccountDAOImpl implements AccountDAO{

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
			
		}
		catch(HibernateException e) {
			e.printStackTrace();
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
			
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		
		return accounts;
		
	}

	/**
	 * addAccount(Account a) - Add a new persisted Account instance with the Account instance
	 * passed in.
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
			return id;
		}
		catch(HibernateException e) {
			e.printStackTrace();
			t.rollback();
			return id;
		}
		finally {
			s.close();
		}
		
	}

	/**
	 * removeAccount(Account a) - Remove a persisted Account instance of the Account instance
	 * passed in.
	 */
	@Override
	public boolean removeAccount(Account a) {
		
		Session s = HibernateUtil.getSession();
		Transaction t = null;
		
		try {			
			t = s.beginTransaction();
			s.delete(a);
			t.commit();
			return true;			
		}
		catch(HibernateException e) {
			e.printStackTrace();
			t.rollback();
			return false;
		}
		finally {
			s.close();
		}
		
	}

	/**
	 * updateAccount(Account a) - Update persisted Account instance to the given
	 * account passed in.
	 */
	@Override
	public boolean updateAccount(Account a) {

		Session s = HibernateUtil.getSession();
		Transaction t = null;
		
		try {
			t = s.beginTransaction();
			s.update(a);
			t.commit();
			return true;
		}
		catch(HibernateException e) {
			e.printStackTrace();
			t.rollback();
			return false;
		}
		finally {
			s.close();
		}
	}

	
	
}
