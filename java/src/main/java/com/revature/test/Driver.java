package com.revature.test;

import java.sql.Date;

import com.revature.beans.Account;
import com.revature.beans.User;
import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.util.HibernateUtil;

public class Driver {
	public static void main(String[] args) {
		UserDAO ud = new UserDAOImpl();
		boolean id = ud.addUser(new User("Mussab", "pass", "fnmae", "lname", "add", "city", "state", 1111, 1111,
				new Date(System.currentTimeMillis()), 111111, 1));
		System.out.println(id);

		AccountDAO adao = new AccountDAOImpl();
		Account a = new Account(0, "Dollar", "wat", 0.0, new User());
		long genId = adao.addAccount(a);
		
		System.out.println(genId);
		
	}
}
