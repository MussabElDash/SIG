package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Account;
import com.revature.beans.Order;
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
	public List<Order> getAllOrders() {
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
			
			Query query = session.createQuery("FROM Order WHERE id = :givenId");
			query.setParameter("givenId", id);
			or = (Order) query.uniqueResult();
			
		} catch (HibernateException e) {

			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return or;
	}

	@Override
	public boolean updateOrder(Order or) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.update(or);
			
			tx.commit();
			return true;
			
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
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

	@Override
	public List<Order> getOrdersByAccount(Account acct) {
		List<Order> orders = null;
		Session session = HibernateUtil.getSession();
		
		try {
			
			Query query = session.createQuery("FROM Security WHERE OwnerAccount = :givenAcct");
			query.setParameter("givenAcct", acct);
			
			orders = query.list();
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
			
		}
		
		return orders;
	}

}
