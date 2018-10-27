package com.revature.dao;

import java.util.List;

import com.revature.beans.Account;
import com.revature.beans.Trade;
import com.revature.beans.User;

public interface TradesDao {
	
	public long insertTrades (Trade trades);
	public Trade selectTradesByTradeId(Long tradeId);
	public List<Trade> selectAllTrades();
	public List<Trade> selectTradesByRequestorAccount(Account a);
	public List<Trade> selectTradesByReceiverAccount(Account a);
	public boolean deleteTradesByTradeId(Trade delTrade);
	public boolean updateTrades(Trade trades);
	public List<Trade> selectAllTradesPendingBrokerApproval();

}
