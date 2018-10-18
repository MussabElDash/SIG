package com.revature.dao;

import java.util.List;

import com.revature.beans.Security;

public interface SecurityDao {
	public Long insertSecurity(Security security);
	public List<Security> getAllSecurities();
	public Security selectSecurityById(Long id);
	public boolean updateSecurity(Security s);
	public void removeSecurityById(Long id);
	public List<Security> getSecuritiesByAccount(Account acct);
}
