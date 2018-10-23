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
		SecurityDao sdao = new SecurityDaoImpl();
		Trades t = new Trades();
		User requestor = (User)request.getSession().getAttribute("user");
		User reciever = udao.getUser(request.getParameter("reciever"));
		Security requestorSecurity = sdao.selectSecurityById(Long.parseLong(request.getParameter("requestSecurity")));
		Security recieverSecurity = sdao.selectSecurityById(Long.parseLong(request.getParameter("recieverSecurity")));
		
		t.setRequestorUsername(requestor.getUsername());
		t.setReceiverUsername(reciever.getUsername());
		t.setBrokerStatus(0l);
		t.setReceiverApproval(0l);
		t.setSecurityIdRequestor(requestorSecurity.getId());
		t.setSecutiyIdReceiver(recieverSecurity.getId());
		t.setAmountRequestor(Long.parseLong(request.getParameter("requestAmount")));
		t.setAmountReceiver(Long.parseLong(request.getParameter("recieveAmount")));
		t.setCreatedOn(new Date(System.currentTimeMillis()));
		
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
