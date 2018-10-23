package com.revature.servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;

import com.revature.beans.Trade;
import com.revature.dao.TradesDao;
import com.revature.dao.TradesDaoImpl;
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
		Trade t = tdao.selectTradesByTradeId(Long.parseLong(request.getParameter("tid")));
		
		t.setAmountrequester(Integer.parseInt(request.getParameter("requestAmount")));
		t.setAmountReceiver(Integer.parseInt(request.getParameter("recieveAmount")));
		
		if(tdao.updateTrades(t)) {
			log.info("Trade [ Trade ID: " + t.getId() + " ] succesffully updated.");
			log.info("\tTrade information: " + t.toString());
		}
		else {
			log.error("Trade [ Trade ID: " + t.getId() + " ] FAILED to update.");
		}
		
	}

}
