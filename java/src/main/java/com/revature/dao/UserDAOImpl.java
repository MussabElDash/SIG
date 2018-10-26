package com.revature.dao;

import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.User;
import com.revature.util.HibernateUtil;
import com.revature.util.LogInterface;

public class UserDAOImpl implements UserDAO{
	
	Logger log = LogInterface.logger;

	/**
	 * getUser(String username) - Fetch a persisted User from the
	 * database based off the given username. Return User instance
	 * upon successful fetch. Return NULL upon failure.
	 */
	@Override
	public User getUser(String username) {
		
		User u = null;
		Session s = HibernateUtil.getSession();
		
		try {
			
			Query query = s.createQuery("FROM User WHERE username = :givenUsername");
			query.setParameter("givenUsername", username);
			u = (User)query.uniqueResult();
			log.info("Successfully retrieved User [ " + u.getUsername() + " ] from DB");
			
		}
		catch(HibernateException e) {
			e.printStackTrace();
			log.error("FAILED to retrieve User [ " + username + " ] from DB");
		}
		finally {
			s.close();
		}
		
		return u;
		
	}

	/**
	 * addUser(User u) - Add a new persisted User instance with the User instance
	 * passed in. Returns TRUE if User was added successfully. Returns FALSE
	 * if the insertion fails.
	 */
	@Override
	public boolean addUser(User u) {

		Session s = HibernateUtil.getSession();
		Transaction t = null;
		
		try {
			t = s.beginTransaction();
			s.save(u);
			t.commit();
			log.info("Successfully added new User to DB.\n"
					+"Details: " + u.toString());
			return true;
		}
		catch(HibernateException e) {
			e.printStackTrace();
			t.rollback();
			log.error("FAILED to add new User to DB.\n"
					+"Details (failed to add): " + u.toString());
			return false;
		}
		finally {
			s.close();
		}
		
	}

	/**
	 * removeUser(User u) - Remove persisted User instance with the User instance
	 * passed in. Returns TRUE if the removal succeeds. Returns FALSE if the removal
	 * fails.
	 */
	@Override
	public boolean removeUser(User u) {
		
		Session s = HibernateUtil.getSession();
		Transaction t = null;
		
		try {			
			t = s.beginTransaction();
			s.delete(u);
			t.commit();
			log.info("Successfully removed User [ " + u.getUsername() + " ] from DB");
			return true;			
		}
		catch(HibernateException e) {
			e.printStackTrace();
			t.rollback();
			log.error("FAILED to remove User [ " + u.getUsername() + " ] from DB");
			return false;
		}
		finally {
			s.close();
		}
		
	}

	/**
	 * updateUser(User u) - Update an existing persisted User instance with the User instance
	 * passed in. Returns TRUE if the update is successful. Returns FALSE if the update fails.
	 */
	@Override
	public boolean updateUser(User u) {
		
		Session s = HibernateUtil.getSession();
		Transaction t = null;
		
		try {
			t = s.beginTransaction();
			s.update(u);
			t.commit();
			log.info("Succesfully updated User [ " + u.getUsername() + " ] in DB");
			return true;
		}
		catch(HibernateException e) {
			e.printStackTrace();
			t.rollback();
			log.error("FAILED to update User [ " + u.getUsername() + " ] in DB");
			return false;
		}
		finally {
			s.close();
		}
		
	}

}
