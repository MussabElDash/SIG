package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;

import com.revature.beans.Account;
import com.revature.beans.Security;
import com.revature.beans.User;
import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.SecurityDao;
import com.revature.dao.SecurityDaoImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.util.LogInterface;



/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Logger log = LogInterface.logger;
		User u = null;
		
		log.info("User [ " + username + " ] requesting login...");
		
		UserDAO udao = new UserDAOImpl();
		u = udao.getUser(username);
		
		if(u != null) {
			if(u.getPass().equals(password)) {
				log.info("User [ " + username + " ] SUCCESSFULLY logged in");
				request.getSession().setAttribute("user", u);
			}
			else {
				log.warn("User [ " + username + " ] failed to logged in...");
			}
		}
		else {
			log.warn("User [ " + username + " ] failed to logged in...");
		}
	}
}
