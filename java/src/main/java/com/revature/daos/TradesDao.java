package com.revature.daos;

import java.util.List;

import com.revature.beans.Trades;

public interface TradesDao {
	
	public void insertTrades (Trades trades);
	public Trades selectTradesByTradeId(Long tradeId);
	public Trades selectTradesByUsername(String username);
	public List<Trades> selectAllTrades();
	public Integer deleteTradesByTradeId(Long tradeId);
	public Integer updateTrades(Trades trades);

}
