package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name="asset_pricing")
public class AssetPricing {
	
	@Id
	@Column (name="ticker_symbol")
	@SequenceGenerator(sequenceName="ticker_seq", name="ticker_seq")
	@GeneratedValue(generator="ticker_seq", strategy = GenerationType.SEQUENCE)
	private String tickerSymbol;
	
	@Column (name="price")
	private Number price;
	
	public String getTickerSymbol() {
		return tickerSymbol;
	}
	public void setTickerSymbol(String tickerSymbol) {
		this.tickerSymbol = tickerSymbol;
	}
	public Number getPrice() {
		return price;
	}
	public void setPrice(Number price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "AssetPricing [tickerSymbol=" + tickerSymbol + ", price=" + price + "]";
	}
	public AssetPricing(String tickerSymbol, Number price) {
		super();
		this.tickerSymbol = tickerSymbol;
		this.price = price;
	}
	public AssetPricing(Number price) {
		super();
		this.price = price;
	}
	public AssetPricing() {
		super();
	}
	
	
	

}
