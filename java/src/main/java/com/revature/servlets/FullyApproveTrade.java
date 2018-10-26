package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;

import com.revature.beans.Account;
import com.revature.beans.Security;
import com.revature.beans.Trade;
import com.revature.dao.SecurityDao;
import com.revature.dao.SecurityDaoImpl;
import com.revature.util.LogInterface;

/**
 * FullyApproveTrade is invoked once a trade has both reciever
 * and broker approval. It is responsible for exchanging securities between
 * the accounts.
 */
public class FullyApproveTrade extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Logger log = LogInterface.logger;
		
		SecurityDao sdao = new SecurityDaoImpl();
		
		Trade t = (Trade)request.getSession().getAttribute("trade");
		
		Account requestor = t.getrequesterAccount();
		Account reciever = t.getReceiverAccount();
		
		Security suppliment = null;
		
		Security reqSec = t.getRequesterSecurity();
		Security recSec = t.getReceiverSecutiy();
		
		log.info("Finalizing trade between Requestor [ " + requestor.getOwner().getUsername() + " ] and Receiver [ " + reciever.getOwner().getUsername() + " ]\n"
				+"Requestor offer: " + reqSec.toString()+"\n"
				+"Receiver offer: " + recSec.toString());
		
		boolean hasSec = false;
		
		for(Security sec : requestor.getSecurities()) {
			if(sec.getAp().getTickerSymbol().equals(recSec.getAp().getTickerSymbol())){
				suppliment = sec;
				hasSec = true;
				break;
			}
		}
		
		if(hasSec == true) {
			suppliment.setAmount(suppliment.getAmount() + t.getAmountReceiver());
			sdao.updateSecurity(suppliment);
			if(recSec.getAmount() >= t.getAmountReceiver()) {
				sdao.removeSecurityById(recSec.getId());
			}
			else {
				recSec.setAmount(recSec.getAmount()-t.getAmountReceiver());
				sdao.updateSecurity(recSec);
			}
		}
		else {
			Security newRequestorSec = new Security();
			newRequestorSec.setAmount(t.getAmountReceiver());
			newRequestorSec.setAp(recSec.getAp());
			newRequestorSec.setOwnerAccount(requestor);
			newRequestorSec.setType(recSec.getType());
			
			sdao.insertSecurity(newRequestorSec);
			
			if(recSec.getAmount() >= t.getAmountReceiver()) {
				sdao.removeSecurityById(recSec.getId());
			}
			else {
				recSec.setAmount(recSec.getAmount()-t.getAmountReceiver());
				sdao.updateSecurity(recSec);
			}
		}
		
		hasSec = false;
		suppliment = null;
		
		for(Security sec : reciever.getSecurities()) {
			if(sec.getAp().getTickerSymbol().equals(reqSec.getAp().getTickerSymbol())){
				suppliment = sec;
				hasSec = true;
				break;
			}
		}
		
		if(hasSec == true) {
			suppliment.setAmount(suppliment.getAmount() + reqSec.getAmount());
			sdao.updateSecurity(suppliment);
			if(reqSec.getAmount() >= t.getAmountrequester()){
				sdao.removeSecurityById(reqSec.getId());
			}
			else {
				reqSec.setAmount(reqSec.getAmount() - t.getAmountrequester());
				sdao.updateSecurity(reqSec);
			}
		}
		else {
			Security newReceiverSec = new Security();
			newReceiverSec.setAmount(t.getAmountrequester());
			newReceiverSec.setAp(reqSec.getAp());
			newReceiverSec.setOwnerAccount(reciever);
			newReceiverSec.setType(reqSec.getType());
			
			sdao.insertSecurity(newReceiverSec);
			if(reqSec.getAmount() >= t.getAmountrequester()){
				sdao.removeSecurityById(reqSec.getId());
			}
			else {
				reqSec.setAmount(reqSec.getAmount() - t.getAmountrequester());
				sdao.updateSecurity(reqSec);
			}
		}
		
		
		
	}

}
