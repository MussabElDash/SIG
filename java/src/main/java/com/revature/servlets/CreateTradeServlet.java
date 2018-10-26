package com.revature.servlets;

import java.io.IOException;
import java.sql.Date;

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
import com.revature.util.LogInterface;

/**
 * Servlet implementation class CreateTradeServlet
 */
public class CreateTradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Logger log = LogInterface.logger;
		
		TradesDao tdao = new TradesDaoImpl();
		UserDAO udao = new UserDAOImpl();
		AccountDAO adao = new AccountDAOImpl();
		Trade t = new Trade();
		User requestor = udao.getUser(request.getParameter("requestor"));
		User reciever = udao.getUser(request.getParameter("reciever"));
		Account reqAcc = adao.getAccount(Long.parseLong(request.getParameter("reqaid")));
		Account recAcc = adao.getAccount(Long.parseLong(request.getParameter("recaid")));
		
		t.setBrokerStatus(0);
		t.setReceiverApproval(0);
		t.setrequesterAccount(reqAcc);
		t.setReceiveraccount(recAcc);
		t.setCreatedOn(new Date(System.currentTimeMillis()));
		t.setAmountrequester(Integer.parseInt(request.getParameter("requestorAmount")));
		t.setAmountReceiver(Integer.parseInt(request.getParameter("recieverAmount")));
		
		long newid = tdao.insertTrades(t);
		
		if(newid != 0l) {
			log.info("User [ " + requestor.getUsername() + " ] succesffully requested a new trade [ Trade ID: " + newid + " ] from user [ " + reciever.getUsername() + " ].");
			log.info("\tTrade information: " + t.toString());
		}
		else {
			log.error("User [ " + requestor.getUsername() + " ] FAILED to request a new trade [ Trade ID: " + newid + " ] from user [ " + reciever.getUsername() + " ].");
		}
		
	}

}
