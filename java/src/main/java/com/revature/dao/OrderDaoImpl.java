package com.revature.dao;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Account;
import com.revature.beans.Order;
import com.revature.util.HibernateUtil;
import com.revature.util.LogInterface;

public class OrderDaoImpl  implements OrderDao{

	Logger log = LogInterface.logger;
	
	/**
	 * Long insertOrder(Order order) - Inserts a new instance of order to persist
	 * in the DB. Returns a LONG of the newly generated ID for the instance, returns
	 * NULL if the insertion fails.
	 */
	@Override
	public Long insertOrder(Order order) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		Long id = null;
		
		try {
			tx = session.beginTransaction();
			id = (Long)session.save(order); //Returns the id of the fresh insert
			tx.commit();
			log.info("Successfully added a new Order [ ID: " + id + " ]");
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			log.error("FAILED to insert a new Order into the DB.");
		}finally {
			session.close();
		}
		
		return id;
	}

	/**
	 * List<Order> getAllOrders() - Gets a list of all persisted Order
	 * instances from the DB. Returns a LIST of all orders upon success.
	 * Returns NULL if the select fails.
	 */
	@Override
	public List<Order> getAllOrders() {
		List<Order> orders = null;
		Session session = HibernateUtil.getSession();
		
		try {
			
			orders = session.createQuery("FROM Order").list();
			log.info("Succesfully retrieved all Orders from the DB.");
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("FAILED to retrieve all Orders from the DB.");
		} finally {
			session.close();
		}
		return orders;
	}

	/**
	 * Order selectOrderById(Long id) - Selects a single, persisted
	 * instance of Order from the DB by its unique ID. Returns
	 * the Order instance upon success. Returns NULL if select
	 * fails.
	 */
	@Override
	public Order selectOrderById(Long id) {
		Session session = HibernateUtil.getSession();
		Order or = null;
		
		try {
			
			Query query = session.createQuery("FROM Order WHERE id = :givenId");
			query.setParameter("givenId", id);
			or = (Order) query.uniqueResult();
			log.info("Succesffuly retrieved Order from the DB: \n"
					+"Details: " + or.toString());
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("FAILED to retrieve Order from the DB [ ID: " + id + " ]");
		} finally {
			session.close();
		}
		
		return or;
	}

	
	/**
	 * boolean updateOrder(Order or) - Updates a persisted instance
	 * of Order in the DB to the order object passed in. Returns
	 * TRUE if the update successfully executes. Returns FALSE
	 * if the update fails.
	 */
	@Override
	public boolean updateOrder(Order or) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.update(or);
			tx.commit();
			log.info("Successfully updated Order [ ID: " + or.getId() + " ]");
			return true;
			
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
			log.error("FAILED to update Order [ ID: " + or.getId() + " ]");
			return false;
		} finally {
			session.close();
		}
	}

	/**
	 * void removeOrderById(Long id) - Removed a persisted Order from the DB
	 * given its unique ID.
	 */
	@Override
	public boolean removeOrderById(Long id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			session.delete(session.get(Order.class, id));
			
			tx.commit();
			log.info("Successfully removed Order from DB [ ID: " + id + " ]");
			return true;
		} catch (HibernateException e) {
			tx.rollback();
			log.error("FAILED to remove Order from DB [ ID: " + id + " ]");
			return false;
		} finally {
			session.close();
		}
		
	}

	/**
	 * List<Order> getOrdersByAccount(Account acct) - Retrieves all persisted
	 * orders from the DB by their owner Account. Returns a list of all 
	 * persisted Orders associated with the Account instance passed in.
	 * Returns NULL if the select fails.
	 */
	@Override
	public List<Order> getOrdersByAccount(Account acct) {
		List<Order> orders = null;
		Session session = HibernateUtil.getSession();
		
		try {
			
			Query query = session.createQuery("FROM Security WHERE ownerAccount = :givenAcct");
			query.setParameter("givenAcct", acct);
			
			orders = query.list();
			log.info("Successfully retrieved all orders for Account [ Account ID: " + acct.getId() + " ]");
			
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("FAILED to retrieve all orders for Account [ Account ID: " + acct.getId() + " ]");
		} finally {
			session.close();
			
		}
		
		return orders;
	}

}
