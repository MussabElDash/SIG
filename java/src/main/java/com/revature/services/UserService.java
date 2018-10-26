package com.revature.services;

import com.revature.beans.User;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;

public class UserService extends AbstractService {
	private static UserDAO dao;

	public static UserDAO setDaoIfNotSet(UserDAO Dao) {
		if (dao == null || Dao == null) {
			dao = Dao;
		}
		return dao;
	}

	public static User getUserByUsername(String username) {
		return setDaoIfNotSet(new UserDAOImpl()).getUser(username);
	}
}
