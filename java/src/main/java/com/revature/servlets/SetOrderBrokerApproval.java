package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;

import com.revature.beans.Order;
import com.revature.dao.OrderDao;
import com.revature.dao.OrderDaoImpl;
import com.revature.util.LogInterface;

/**
 * Servlet implementation class SetOrderBrokerApproval
 */
public class SetOrderBrokerApproval extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Logger log = LogInterface.logger;
		
		OrderDao odao = new OrderDaoImpl();
		Order o = odao.selectOrderById(Long.parseLong(request.getParameter("oid")));
		
		o.setBrokerStatus(Integer.parseInt(request.getParameter("approvalLevel")));
		
		odao.updateOrder(o);
		
		if(o.getBrokerStatus() == 1) {
			log.info("Broker successfully APPROVED order [ ID: " + o.getId() + " ]");
		}
		else if(o.getBrokerStatus() == -1) {
			log.info("Broker successfully DENIED order [ ID: " + o.getId() + " ]");
		}
		else {
			log.error("Broker FAILED to update approval for order [ ID: " + o.getId() + " ]");
		}
		
	}

}
