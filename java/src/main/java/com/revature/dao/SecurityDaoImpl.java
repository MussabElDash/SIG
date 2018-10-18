package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Account;
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
			
			Query query = session.createQuery("FROM Security WHERE id = :givenId");
			query.setParameter("givenId", id);
			s = (Security) query.uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return s;
	}

	@Override
	public boolean updateSecurity(Security s) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.update(s);
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
	public void removeSecurityById(Long id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			session.delete(session.get(Security.class, id));
			
			tx.commit();
			
			
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}

	@Override
	public List<Security> getSecuritiesByAccount(Account acct) {
		
		List<Security> securities = null;
		Session session = HibernateUtil.getSession();
		
		try {
			
			Query query = session.createQuery("FROM Security WHERE OwnerAccount = :givenAcct");
			query.setParameter("givenAcct", acct);
			
			securities = query.list();
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
			
		}
		
		return securities;
	}

}
