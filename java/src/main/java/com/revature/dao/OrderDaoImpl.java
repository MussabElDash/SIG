package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Order;
import com.revature.beans.Security;

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
		Security or = null;
		
		try {
			or = (Security)session.get(Security.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return or;
	}

	@Override
	public Order updateOrder(Order s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeOrderById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
