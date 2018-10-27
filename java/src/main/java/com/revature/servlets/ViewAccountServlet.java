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
import com.revature.util.JsonUtil;
import com.revature.util.LogInterface;

/**
 * Servlet implementation class ViewAccountServlet
 */
public class ViewAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Logger log = LogInterface.logger;
		User u = LoginServlet.getLoggedUser(request);
		AccountDAO adao = new AccountDAOImpl();
		
		Account a = null;
		a = adao.getAccount(Long.parseLong(request.getParameter("aid")));
		
		String JSONaccount = JsonUtil.convertJavaToJson(a);
		response.setContentType("application/json");
		response.getWriter().write(JSONaccount);
		
		if(a != null) {
			log.info("User  [ " + u.getUsername() + " ] successfully accessing account [ Account ID: " + a.getId() + " ]");	
			log.info("account securities[ " + a.getSecurities() + " ]");
//			log.info("account orders[ " + a.getOrders() + " ]");
		}
		else {
			log.error("User  [ " + u.getUsername() + " ] failed to access account [ Account ID: " + request.getParameter("aid") + " ]");
		}
		
	}

}
