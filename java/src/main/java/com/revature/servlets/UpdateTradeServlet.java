package com.revature.servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;

import com.revature.beans.Security;
import com.revature.beans.Trades;
import com.revature.beans.User;
import com.revature.dao.SecurityDao;
import com.revature.dao.SecurityDaoImpl;
import com.revature.dao.TradesDao;
import com.revature.dao.TradesDaoImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.util.LogInterface;

/**
 * Servlet implementation class UpdateTradeServlet
 */
public class UpdateTradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Logger log = LogInterface.logger;
		
		TradesDao tdao = new TradesDaoImpl();
		Trades t = tdao.selectTradesByTradeId(Long.parseLong(request.getParameter("tid")));
		SecurityDao sdao = new SecurityDaoImpl();
		
		t.setAmountRequestor(Long.parseLong(request.getParameter("requestAmount")));
		t.setAmountReceiver(Long.parseLong(request.getParameter("recieveAmount")));
		
		if(tdao.updateTrades(t)) {
			log.info("Trade [ Trade ID: " + t.getTradeId() + " ] succesffully updated.");
			log.info("\tTrade information: " + t.toString());
		}
		else {
			log.error("Trade [ Trade ID: " + t.getTradeId() + " ] FAILED to update.");
		}
		
	}

}
