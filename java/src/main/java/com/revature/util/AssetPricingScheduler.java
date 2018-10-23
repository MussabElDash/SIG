package com.revature.util;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.revature.beans.AssetPricing;
import com.revature.services.AssetPricingService;

public class AssetPricingScheduler extends TimerTask implements LogInterface {

	static {
		new AssetPricingScheduler();
	}

	private Timer time = new Timer();

	private AssetPricingScheduler() {
		time.schedule(this, 0, 1000 * 60 * 60 * 24);
	}

	@Override
	public void run() {
		List<AssetPricing> prices = AssetPricingService.getAllAssets();
		List<AssetPricing> tobe = new ArrayList<>(prices);
		logger.info("Updating the price of {} Asset", prices.size());
		while (!tobe.isEmpty()) {
			for (AssetPricing asset : prices) {
				String url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="
						+ asset.getTickerSymbol() + "&apikey=MDXUPZ90PGLZSKLK";
				try {
					try {
						SSLContext sc = SSLContext.getInstance("SSL");
						sc.init(null, trustAllCerts, new java.security.SecureRandom());
						HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
					} catch (Exception e) {
						e.printStackTrace();
					}
					URL obj = new URL(url);
					URLConnection con = obj.openConnection();
					JsonParser parser = Json.createParser(con.getInputStream());
					while (parser.hasNext()) {
						Event ev = parser.next();
						if (ev == Event.KEY_NAME && parser.getString().contains("close") && parser.hasNext()) {
							ev = parser.next();
							Double cost = null;
							try {
								cost = Double.parseDouble(parser.getString());
							} catch (NumberFormatException e) {
								e.printStackTrace();
							}
							if (ev == Event.VALUE_STRING && cost != null && cost > 0) {
								asset.setPrice(cost);
								logger.info("Updating the price of the Asset: {}", asset.getCompanyName());
								AssetPricingService.updateAsset(asset);
								tobe.remove(asset);
								break;
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			prices = new ArrayList<>(tobe);
		}
	}

	TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
		}

		public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
		}
	} };

	public static void main(String[] args) {
	}

}
