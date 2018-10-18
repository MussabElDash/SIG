package com.revature.dao;

import java.util.List;

import com.revature.beans.Account;
import com.revature.beans.User;

public interface AccountDAO {

	public Account getAccount(int id);
	public List<Account> getAccountsByUser(User u);
	public Long addAccount(Account a);
	public boolean removeAccount(Account a);
	public boolean updateAccount(Account a);
	
}
