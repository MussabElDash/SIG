package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;

import com.revature.beans.Account;
import com.revature.beans.Security;
import com.revature.beans.User;
import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.SecurityDao;
import com.revature.dao.SecurityDaoImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.util.JsonUtil;
import com.revature.util.LogInterface;

/**
 * Servlet implementation class GetAccountTotalsServlet
 */
public class GetAccountTotalsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Logger log = LogInterface.logger;
		
		UserDAO udao = new UserDAOImpl();
		AccountDAO adao = new AccountDAOImpl();
		SecurityDao sdao = new SecurityDaoImpl();
		
		User u = (User)request.getSession().getAttribute("user");
		ArrayList<Account> pa = (ArrayList<Account>)adao.getAccountsByUser(u);
		ArrayList<Security> as = null;
		double total = 0.0;
		
		for(Account a : pa) {
			as = (ArrayList<Security>)sdao.getSecuritiesByAccount(a);
			if(as != null) {
				for(Security s : as) {
					
					total += s.getAmount();
					
				}
			}
		}
		
		Double amount = total;
		
		String JSONtotalOfAccounts = JsonUtil.convertJavaToJson(amount);
		response.setContentType("application/json");
		response.getWriter().write(JSONtotalOfAccounts);
		
	}

}
