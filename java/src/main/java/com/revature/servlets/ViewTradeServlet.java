package com.revature.servlets;

import java.io.IOException;
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
 * Servlet implementation class ViewTradeServlet
 */
public class ViewTradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Logger log = LogInterface.logger;
		
		TradesDao tdao = new TradesDaoImpl();
		
		User u = LoginServlet.getLoggedUser(request);
		
		Trade t = tdao.selectTradesByTradeId(Long.parseLong(request.getParameter("tid")));
		
		String JSONtrade = JsonUtil.convertJavaToJson(t);
		response.setContentType("application/json");
		response.getWriter().write(JSONtrade);
		
		if(t != null) {
			log.info("User [ " + u.getUsername() + " ] accessing trade [ Trade ID: " + t.getId() + " ]");
		}
		else {
			log.error("User [ " + u.getUsername() + " ] FAILED to access trade [ Trade ID: " + request.getParameter("tid") + " ]");
		}
		
	}

}
