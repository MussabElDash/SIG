package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;

import com.revature.beans.User;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.util.LogInterface;

import java.sql.Date;

/**
 * Servlet implementation class RegisterationServlet
 */
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Logger log = LogInterface.logger;

		UserDAO udao = new UserDAOImpl();
		User u = new User();

		u.setUsername(request.getParameter("username"));
		u.setPass(request.getParameter("pass"));
		u.setFname(request.getParameter("fname"));
		u.setLname(request.getParameter("lname"));
		u.setAddress(request.getParameter("address"));
		u.setCity(request.getParameter("city"));
		u.setState(request.getParameter("state"));
		u.setZip(Integer.parseInt(request.getParameter("zip")));
		u.setSsn(Integer.parseInt(request.getParameter("ssn")));
		Date dob = Date.valueOf(request.getParameter("dob"));
		u.setDateOfBirth(dob);
		u.setPhone(Integer.parseInt(request.getParameter("phone")));
		u.setApprovalFlag(0);

		if (udao.addUser(u)) {
			log.info("New user [ " + u.getUsername() + " ] successfully added.");
		} else {
			log.error("Failed to add new user [ " + u.getUsername() + " ]");
			response.sendError(400);
		}

	}

}
