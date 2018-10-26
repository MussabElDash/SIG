package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;

import com.revature.beans.Account;
import com.revature.beans.Trade;
import com.revature.beans.User;
import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.TradesDao;
import com.revature.dao.TradesDaoImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.util.JsonUtil;
import com.revature.util.LogInterface;

/**
 * Servlet implementation class GetTradeByRequestor
 */
public class GetTradeByRequestor extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger log = LogInterface.logger;
		
		TradesDao tdao = new TradesDaoImpl();
		AccountDAO adao = new AccountDAOImpl();
		UserDAO udao = new UserDAOImpl();
		User u = LoginServlet.getLoggedUser(request);
		Account requestorAccount = adao.getAccount(Long.parseLong(request.getParameter("aid")));
		
		ArrayList<Trade> requestorTrades = null;
		
		requestorTrades = (ArrayList<Trade>)tdao.selectTradesByRequestorAccount(requestorAccount);
		
		String JSONrequestorTrades = JsonUtil.convertJavaToJson(requestorTrades);
		response.setContentType("application/json");
		response.getWriter().write(JSONrequestorTrades);
		
		if(requestorTrades != null) {
			log.info("User [ " + u.getUsername() + " ] accessing requested trades on account [ Account ID: " + requestorAccount.getId() + " ]");
		}
		else {
			log.error("User [ " + u.getUsername() + " ] FAILED to access requested trades on account [ Account ID: " + requestorAccount.getId() + " ]");
		}
	}
}
