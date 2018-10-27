package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;

import com.revature.beans.AssetPricing;
import com.revature.services.AssetPricingService;
import com.revature.util.JsonUtil;
import com.revature.util.LogInterface;
import java.util.ArrayList;

/**
 * Servlet implementation class GetAssetPricingServlet
 */
public class GetAssetPricingServlet extends HttpServlet {
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
		
		ArrayList<AssetPricing> allPricings = (ArrayList<AssetPricing>)AssetPricingService.getAllAssets();
		
		String JSONpricings = JsonUtil.convertJavaToJson(allPricings);
		response.setContentType("application/json");
		response.getWriter().write(JSONpricings);
		
		if(allPricings != null) {
			log.info("accessing a list of all of asset prices.");
		}
		else {
			log.error("attempted and failed to retrieve a list of all asset prices.");
		}
	}

}
