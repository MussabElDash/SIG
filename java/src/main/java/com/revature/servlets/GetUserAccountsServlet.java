package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;

import com.revature.beans.Account;
import com.revature.beans.User;
import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.util.JsonUtil;
import com.revature.util.LogInterface;

/**
 * Servlet implementation class GetUserAccountsServlet
 */
public class GetUserAccountsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Logger log = LogInterface.logger;
		
		UserDAO udao = new UserDAOImpl();
		AccountDAO adao = new AccountDAOImpl();
		User u = LoginServlet.getLoggedUser(request);
		ArrayList<Account> userAccounts = (ArrayList<Account>)adao.getAccountsByUser(u);
		
		String JSONaccountList = JsonUtil.convertJavaToJson(userAccounts);
		response.setContentType("application/json");
		response.getWriter().write(JSONaccountList);
		
		if(userAccounts != null && !userAccounts.isEmpty()) {
			log.info("User [ " + u.getUsername() + " ] accessing a list of all of their accounts.");
			System.out.println(u.toString());
			System.out.println(userAccounts.toString());
		}
		else {
			log.error("User [ " + u.getUsername() + " ] attempted and failed to retrieve a list of all of their accounts.");
		}
		
	}

}
