package com.revature.service;

import com.revature.dao.UsersDao;
import com.revature.model.Users;

public class UserService {
	private static UsersDao cdao = new UsersDao();

	public boolean register(Users user) {

		if (cdao.selectByUsername(user.getUsername()) != null) {
			return false;
		}
		if (cdao.insert(user) != 0) {
			return true;
		}

		return false;

	}

	public boolean login(Users user) {
		Users compared=cdao.selectByUsername(user.getUsername());
		if(compared==null) {
			return false;
		}
		if (compared.getPwd().equals(user.getPwd())) {
			return true;
		}
		return false;
	}

	public boolean logOut() {
		return true;
	}

}
