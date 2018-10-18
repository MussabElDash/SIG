package com.revature.dao;

import com.revature.beans.User;

public interface UserDAO {
	
	public User getUser(String username);
	public boolean addUser(User u);
	public boolean removeUser(User u);
	public boolean updateUser(User u);

}
