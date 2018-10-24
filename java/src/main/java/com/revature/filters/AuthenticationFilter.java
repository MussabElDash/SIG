package com.revature.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.util.LogInterface;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
public class AuthenticationFilter implements Filter, LogInterface {

	private String[] urls;

	/**
	 * Default constructor.
	 */
	public AuthenticationFilter() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String path = req.getRequestURI();
		logger.info("Uri path: {} is being filtered With Method: {}", path, req.getMethod());
		for (String url : urls) {
			if (path.startsWith(url)) {
				logger.debug("The uri: " + path + " is ignored.");
				chain.doFilter(request, response);
				return;
			}
		}
		if (!AbstractController.isLoggedin(req)) {
			logger.error("There is no session redirecting to the login page");
			HttpSession session = req.getSession();
			session.setAttribute("redirectFrom", req.getServletPath());
			RequestDispatcher dispatcher = req.getRequestDispatcher("/login");
			dispatcher.forward(req, res);
			return;
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		urls = fConfig.getInitParameter("ignorePatterns").split(",");
	}

	@Override
	public void destroy() {

	}

}
