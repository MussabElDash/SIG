package com.revature.dao;

import java.util.List;

import com.revature.beans.Trade;
import com.revature.beans.User;

public interface TradesDao {
	
	public long insertTrades (Trade trades);
	public Trade selectTradesByTradeId(Long tradeId);
	public List<Trade> selectTradesByUser(User user);
	public List<Trade> selectAllTrades();
	public boolean deleteTradesByTradeId(Trade delTrade);
	public boolean updateTrades(Trade trades);

}
