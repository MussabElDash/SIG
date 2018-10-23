package com.revature.dao;

import java.util.List;

import com.revature.beans.Account;
import com.revature.beans.Trades;
import com.revature.beans.User;

public interface TradesDao {
	
	public long insertTrades (Trades trades);
	public Trades selectTradesByTradeId(Long tradeId);
	public List<Trades> selectTradesByUser(User user);
	public List<Trades> selectAllTrades();
	public List<Trades> selectTradesByAccount(Account a);
	public boolean deleteTradesByTradeId(Trades delTrade);
	public boolean updateTrades(Trades trades);

}
