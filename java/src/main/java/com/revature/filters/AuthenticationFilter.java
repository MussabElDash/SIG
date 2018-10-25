package com.revature.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.servlets.LoginServlet;
import com.revature.util.LogInterface;

public class AuthenticationFilter implements Filter, LogInterface {

	private String[] urls;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String path = req.getServletPath();
		logger.info("Uri path: {} is being filtered With Method: {}", path, req.getMethod());
		for (String url : urls) {
			if (path.startsWith(url)) {
				logger.debug("The uri: " + path + " is ignored.");
				chain.doFilter(request, response);
				return;
			}
		}
		if (!LoginServlet.isLoggedin(req)) {
			logger.error("User is not permitted to get through");
			res.sendError(401);
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

}
