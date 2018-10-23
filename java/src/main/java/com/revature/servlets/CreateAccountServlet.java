package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;

import com.revature.beans.Account;
import com.revature.beans.User;
import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.util.LogInterface;

/**
 * Servlet implementation class CreateAccountServlet
 */
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Logger log = LogInterface.logger;
		
		AccountDAO adao = new AccountDAOImpl();
		Account a = new Account();
		User u = (User)request.getSession().getAttribute("user");
		
		a.setAccountType(request.getParameter("accountType"));
		a.setAccountName(request.getParameter("accountName"));
		a.setBalance(0.0);
		a.setOwner((User)request.getSession().getAttribute("user"));
		
		long newid = adao.addAccount(a);
		
		if(newid != 0l) {
			log.info("User [ " + u.getUsername() + " ] succesffully requested a new account [ Account ID: " + newid + " ].");
			log.info("\tTrade information: " + a.toString());
		}
		else {
			log.error("User [ " + u.getUsername() + " ] FAILED to request a new account [ Account ID: " + newid + " ].");
		}
		
	}

}
