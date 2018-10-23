package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;

import com.revature.beans.Trades;
import com.revature.beans.User;
import com.revature.dao.TradesDao;
import com.revature.dao.TradesDaoImpl;
import com.revature.util.JsonUtil;
import com.revature.util.LogInterface;

/**
 * Servlet implementation class GetAllUserTradesServlet
 */
public class GetAllUserTradesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Logger log = LogInterface.logger;
		
		User u = (User)request.getSession().getAttribute("user");
		ArrayList<Trades> tradeList = null;
		
		TradesDao tdao = new TradesDaoImpl();
		
		tradeList = (ArrayList<Trades>)tdao.selectTradesByUser(u);
		
		String JSONtradeList = JsonUtil.convertJavaToJson(tradeList);
		response.setContentType("application/json");
		response.getWriter().write(JSONtradeList);
		
		if(tradeList != null) {
			log.info("User [ " + u.getUsername() + " ] successfully retrieved a list of all their trades.");
		}
		else {
			log.error("User [ " + u.getUsername() + " ] attempted and failed to retrieve all of their trades.");
		}
		
	}

}
