package com.revature.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.User;
import com.revature.util.HibernateUtil;

public class UserDAOImpl implements UserDAO{

	/**
	 * getUser(String username) - Fetch a persisted User from the
	 * database based off the given username.
	 */
	@Override
	public User getUser(String username) {
		
		User u = null;
		Session s = HibernateUtil.getSession();
		
		try {
			
			Query query = s.createQuery("FROM User WHERE username = :givenUsername");
			query.setParameter("givenUsername", username);
			u = (User)query.uniqueResult();
			
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		finally {
			s.close();
		}
		
		return u;
		
	}

	/**
	 * addUser(User u) - Add a new persisted User instance with the User instance
	 * passed in.
	 */
	@Override
	public boolean addUser(User u) {

		Session s = HibernateUtil.getSession();
		Transaction t = null;
		
		try {
			t = s.beginTransaction();
			s.save(u);
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
	 * removeUser(User u) - Remove persisted User instance with the User instance
	 * passed in.
	 */
	@Override
	public boolean removeUser(User u) {
		
		Session s = HibernateUtil.getSession();
		Transaction t = null;
		
		try {			
			t = s.beginTransaction();
			s.delete(u);
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
	 * updateUser(User u) - Update an existing persisted User instance with the User instance
	 * passed in.
	 */
	@Override
	public boolean updateUser(User u) {
		
		Session s = HibernateUtil.getSession();
		Transaction t = null;
		
		try {
			t = s.beginTransaction();
			s.update(u);
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
