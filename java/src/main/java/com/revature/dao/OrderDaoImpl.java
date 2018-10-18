package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Order;
import com.revature.beans.Security;
import com.revature.util.HibernateUtil;

public class OrderDaoImpl  implements OrderDao{

	@Override
	public Long insertOrder(Order order) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		Long id = null;
		
		try {
			tx = session.beginTransaction();
			id = (Long)session.save(order); //Returns the id of the fresh insert
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			session.close();
		}
		
		return id;
	}

	@Override
	public List<Order> getAllSecurities() {
		List<Order> orders = null;
		Session session = HibernateUtil.getSession();
		
		try {

			orders = session.createQuery("FROM Order").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return orders;
	}

	@Override
	public Order selectOrderById(Long id) {
		Session session = HibernateUtil.getSession();
		Order or = null;
		
		try {
			or = (Order)session.get(Order.class, id);
		} catch (HibernateException e) {

			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return or;
	}

	@Override
	public Order updateOrder(Order or) {
		Order order = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.update(or);
			
			tx.commit();
			
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return order;
	}

	@Override
	public void removeOrderById(Long id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			session.delete(session.get(Order.class, id));
			
			tx.commit();
			
			
		} catch (HibernateException e) {
			tx.rollback();
		} finally {
			session.close();
		}
		
	}

}
