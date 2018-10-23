package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;

import com.revature.beans.Trades;
import com.revature.dao.TradesDao;
import com.revature.dao.TradesDaoImpl;
import com.revature.util.LogInterface;

/**
 * Servlet implementation class TradeBrokerApproval
 */
public class TradeBrokerApproval extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Logger log = LogInterface.logger;
		
		TradesDao tdao = new TradesDaoImpl();
		Trades t = tdao.selectTradesByTradeId(Long.parseLong(request.getParameter("tid")));
		
		long approval = Long.parseLong(request.getParameter("approval"));
		
		t.setBrokerStatus(approval);
		
		if(tdao.updateTrades(t)) {
			if(approval < 0l) {
				log.info("Trade [ Trade ID: " + t.getTradeId() + " ] successfully DENIED by broker.");
			}
			else if(approval > 0l) {
				log.info("Trade [ Trade ID: " + t.getTradeId() + " ] successfully APPROVED by broker.");
				
				if(t.getBrokerStatus() > 0l && t.getReceiverApproval() > 0l) {
					request.getRequestDispatcher("/FullyApproveTrade").forward(request, response);
				}
				
			}
		}
		else {
			log.error("ERROR OCCURED in setting broker approval on trade [ Trade ID: " + t.getTradeId() + " ]");
		}
		
	}
	

}
