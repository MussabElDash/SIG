package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "asset_pricing")
@Check(constraints = "price >= 0")
public class AssetPricing {

	@Id
	@Column(name = "ticker_symbol")
	private String tickerSymbol;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "price")
	@ColumnDefault(value = "0")
	private Double price;

	public String getTickerSymbol() {
		return tickerSymbol;
	}

	public void setTickerSymbol(String tickerSymbol) {
		this.tickerSymbol = tickerSymbol;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "AssetPricing [tickerSymbol=" + tickerSymbol + ", price=" + price + "]";
	}

	public AssetPricing(String tickerSymbol, String companyName, Double price) {
		super();
		this.tickerSymbol = tickerSymbol;
		this.companyName = companyName;
		this.price = price;
	}

	public AssetPricing() {
		super();
	}

}
