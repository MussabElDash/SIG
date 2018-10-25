package com.revature.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.revature.beans.User;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.services.auth.KeyService;
import com.revature.services.auth.TokenService;
import com.revature.util.JsonUtil;
import com.revature.util.LogInterface;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Logger log = LogInterface.logger;
		User u = null;

		log.info("User [ " + username + " ] requesting login...");

		UserDAO udao = new UserDAOImpl();
		u = udao.getUser(username);

		if (u != null && u.getPass().equals(password)) {
			log.info("User [ " + username + " ] SUCCESSFULLY logged in");
			request.getSession().setAttribute("user", u);
			String token = null;
			TokenService tokenService = new TokenService();
			try {
				token = tokenService.generateToken(u);
			} catch (NoSuchAlgorithmException | JOSEException e) {
				LogInterface.logStackTrace(e);
			}
//			// create a secure cookie to hold the token
//			Cookie cookie = new Cookie("jwt-auth-token", token);
//			// ensure that the cookie is sent over HTTPS only
//			cookie.setSecure(false);
//			// ensure that the cookie can be accessed from JavaScript
//			cookie.setHttpOnly(false);
//			// a negative value means that the cookie is not stored persistently and will be
//			// deleted when the Web browser exits
//			cookie.setMaxAge(-1);
//			// the domain name within which this cookie is visible; form is according to RFC
//			// 2109
//			// cookie.setDomain(request.getServerName());
//			// make the cookie visible to all pages
//			cookie.setPath("/");
//			// add the cookie to the response and return from the doPost() method
//			response.addCookie(cookie);
			u.setPass("");
			String jsonUser = JsonUtil.convertJavaToJson(u);
			u.setPass(password);
			String json = "{\"user\": " + jsonUser + ", \"token\": \"" + token + "\"}";
			response.setContentType("application/json");
			response.getWriter().print(json);
			response.setStatus(HttpServletResponse.SC_CREATED);
			return;

		} else {
			log.warn("User [ " + username + " ] failed to logged in...");
		}
	}

	public static User getLoggedUser(HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		if (cookies == null) {
			return null;
		}
		for (Cookie c : cookies) {
			if (c.getSecure() && c.getName().equals("jwt-auth-token")) {
				LogInterface.logger.debug("Cookie matches");
				try {
					JWEObject jwe = JWEObject.parse(c.getValue());
					jwe.decrypt(new DirectDecrypter(new KeyService().getEncodedKey()));
					Object obj = jwe.getPayload().toSignedJWT().getJWTClaimsSet().getClaim("username");
					if (obj != null || obj instanceof String) {
						LogInterface.logger.debug("User: {}", obj);
						UserDAO ud = new UserDAOImpl();
						return ud.getUser((String) obj);
					}
				} catch (ParseException | JOSEException e) {
					LogInterface.logStackTrace(e);
				}
			}
		}
		return null;
	}

	public static boolean isLoggedin(HttpServletRequest req) {
		return getLoggedUser(req) != null;
	}
}
