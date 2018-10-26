package com.revature.servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;

import com.revature.beans.Account;
import com.revature.beans.AssetPricing;
import com.revature.beans.Security;
import com.revature.dao.AssetPricingDao;
import com.revature.dao.AssetPricingDaoImpl;
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
		AssetPricingDao apdao = new AssetPricingDaoImpl();
		Security s = sdao.selectSecurityById(Long.parseLong(request.getParameter("sid")));
		AssetPricing ap = apdao.selectAssetPricingByTickerSymbol("Dollar");
		
		Account a = s.getOwnerAccount();
		
		Set<Security> securities = a.getSecurities();
		boolean hasCash = false;
		Security cashSecurity = null;
		
		for(Security sec : securities) {
			if(sec.getAp().getTickerSymbol().equals("Dollar")) {
				hasCash = true;
				cashSecurity = sec;
				break;
			}
		}
		
		if(hasCash == true) {
			cashSecurity.setAmount(cashSecurity.getAmount() + (int)(s.getAmount()*s.getAp().getPrice()));
			sdao.removeSecurityById(s.getId());
		}
		else {
			Security dollarSec = new Security();
			
			dollarSec.setType("Dollar");
			dollarSec.setAp(ap);
			dollarSec.setAmount((int)(s.getAmount()*s.getAp().getPrice()));
			dollarSec.setOwnerAccount(a);
			
			sdao.insertSecurity(dollarSec);
			sdao.removeSecurityById(s.getId());
		}
		
	}

}
