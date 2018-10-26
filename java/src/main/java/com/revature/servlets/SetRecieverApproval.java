package com.revature.servlets;

import java.io.IOException;
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
 * SetRecieverApproval updates a Trade by its given ID to set the RecieverApproval flag
 * in accordance to the reciever's Approval or Denial of a proposed trade.
 */
public class SetRecieverApproval extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger log = LogInterface.logger;
		
		TradesDao tdao = new TradesDaoImpl();
		Trade t = tdao.selectTradesByTradeId(Long.parseLong(request.getParameter("tid")));
		
		int approval = Integer.parseInt(request.getParameter("approval"));
		
		t.setReceiverApproval(approval);
		
		if(tdao.updateTrades(t)) {
			if(approval < 0l) {
				log.info("Trade [ Trade ID: " + t.getId() + " ] successfully DENIED by reciever.");
			}
			else if(approval > 0l) {
				log.info("Trade [ Trade ID: " + t.getId() + " ] successfully APPROVED by reciever.");
				
				if(t.getBrokerStatus() > 0l && t.getReceiverApproval() > 0l) {
					request.getSession().setAttribute("trade", t);
					request.getRequestDispatcher("/FullyApproveTrade").forward(request, response);
				}
			}
		}
		else {
			log.error("ERROR OCCURED in setting reciever approval, from reciever on trade [ Trade ID: " + t.getId() + " ]");
		}
	}
}
