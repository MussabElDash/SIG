package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;

import com.revature.beans.Trade;
import com.revature.beans.User;
import com.revature.dao.TradesDao;
import com.revature.dao.TradesDaoImpl;
import com.revature.util.JsonUtil;
import com.revature.util.LogInterface;

/**
 * Servlet implementation class GetAllTradesPendingBroker
 */
public class GetAllTradesPendingBroker extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Logger log = LogInterface.logger;
		
		TradesDao tdao = new TradesDaoImpl();
		User u = LoginServlet.getLoggedUser(request);
		ArrayList<Trade> brokerTrades = null;
		
		brokerTrades = (ArrayList<Trade>)tdao.selectAllTradesPendingBrokerApproval();
		
		String JSONbrokerTrades = JsonUtil.convertJavaToJson(brokerTrades);
		response.setContentType("application/json");
		response.getWriter().write(JSONbrokerTrades);
		
		if(brokerTrades != null) {
			log.info("User [ " + u.getUsername() + " ] accessing trades pending Broker approval.");
		}
		else {
			log.error("User [ " + u.getUsername() + " ] FAILED to access trades pending Broker approval.");
		}
		
		
	}

}
