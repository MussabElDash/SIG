package com.revature.servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;

import com.revature.beans.Account;
import com.revature.beans.Security;
import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.SecurityDao;
import com.revature.dao.SecurityDaoImpl;
import com.revature.util.LogInterface;

/**
 * SellSecurityServlet takes a provided Security ID, fetches
 * the persisted security via Hibernate, and performs the necessary
 * operations to transition it into a CASH asset for the owning account.
 */
public class SellSecurityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Logger log = LogInterface.logger;
		
		SecurityDao sdao = new SecurityDaoImpl();
		Security s = sdao.selectSecurityById(Long.parseLong(request.getParameter("sid")));
		Security toCash = null;
		
		Account a = s.getOwnerAccount();
		
		Set<Security> securities = a.getSecurities();
		boolean hasCash = false;
		
		
		for(Security sec : securities) {
			if(sec.getAp().getTickerSymbol().equals("CASH")) {
				hasCash = true;
				break;
			}
		}
		
		if(hasCash == true) {
			//TODO Add to cash
		}
		else {
			//TODO Add a NEW cash security to account
		}
		
	}

}
