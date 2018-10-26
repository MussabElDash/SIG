package com.revature.servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;

import com.revature.beans.Account;
import com.revature.beans.AssetPricing;
import com.revature.beans.Order;
import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.AssetPricingDao;
import com.revature.dao.AssetPricingDaoImpl;
import com.revature.dao.OrderDao;
import com.revature.dao.OrderDaoImpl;
import com.revature.util.LogInterface;

/**
 * NewOrderServlet processes a request for a new order for a Security to be added to
 * a client's account.
 */
public class NewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Logger log = LogInterface.logger;
		
		OrderDao odao = new OrderDaoImpl();
		AccountDAO adao = new AccountDAOImpl();
		AssetPricingDao apdao = new AssetPricingDaoImpl();
		Account a = adao.getAccount(Long.parseLong(request.getParameter("aid")));
		AssetPricing ap = apdao.selectAssetPricingByTickerSymbol(request.getParameter("tickerSymbol"));
		
		Order o = new Order();
		
		o.setOwnerAccount(a);
		o.setAp(ap);
		o.setAmount(Integer.parseInt(request.getParameter("amount")));
		o.setBrokerStatus(0);
		o.setCreatedOn(new Date(System.currentTimeMillis()));
		
		if(odao.insertOrder(o) != null) {
			log.info("New Order successfully placed by [ " + a.getOwner().getUsername() + " ].\n"+
					"Details: " + o.toString());
		}
		else {
			log.error("New Order FAILED to be placed by [ " + a.getOwner().getUsername() + " ].\n"+
					"Details: " + o.toString());
		}
		
	}

}
