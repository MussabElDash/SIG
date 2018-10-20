package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "asset_pricing")
@Check(constraints = "price >= 0")
public class AssetPricing {

	@Id
	@Column(name = "ticker_symbol")
	@SequenceGenerator(sequenceName = "ticker_seq", name = "ticker_seq")
	@GeneratedValue(generator = "ticker_seq", strategy = GenerationType.SEQUENCE)
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

	public AssetPricing(Double price) {
		super();
		this.price = price;
	}

	public AssetPricing() {
		super();
	}

}
