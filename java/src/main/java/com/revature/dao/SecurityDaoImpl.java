package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Candy;
import com.revature.beans.Security;
import com.revature.util.HibernateUtil;

public class SecurityDaoImpl implements SecurityDao{

	@Override
	public Long insertSecurity(Security security) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		Long id = null;
		
		try {
			tx = session.beginTransaction();
			id = (Long)session.save(security); //Returns the id of the fresh insert
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
	public List<Security> getAllSecurities() {
		List<Security> securities = null;
		Session session = HibernateUtil.getSession();
		
		try {

			securities = session.createQuery("FROM Security").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return securities;
	}

	@Override
	public Security selectSecurityById(Long id) {
		Session session = HibernateUtil.getSession();
		Security s = null;
		
		try {
			s = (Security)session.get(Security.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return s;
	}

	@Override
	public Security updateSecurity(Security s) {
		Security security = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			security = (Security)session.get(Security.class, s.getId());
			
			
			
			security.setName(s.getName());
			security.setAmount(s.getAmount());
			security.setOwnerAccount(s.getOwnerAccount());
			security.setAp(s.getAp());
			security.setType(s.getType());
			
			session.save(security);
			tx.commit();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return security;
	}

	@Override
	public void removeSecurityById(Long id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			session.delete(session.get(Security.class, id));
			
			tx.commit();
			
			
		} catch (HibernateException e) {
			tx.rollback();
		} finally {
			session.close();
		}
		
	}

}
