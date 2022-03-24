package com.revature;

import com.revature.dao.UsersDao;
import com.revature.model.Users;

public class Driver {
	private static UsersDao cdao = new UsersDao();

	public static void main(String[] args) {

		System.out.println("Running");

		Users c1 = new Users();
		c1.setFName("Bob");
		c1.setLName("Jones");
		c1.setEmail("Myemail@email.com");
		c1.setUsername("SuperkoolMan");
		c1.setPwd("LetMeIn");

		System.out.println("Succesfully created");

		cdao.insert(c1);

		System.out.println("Succesfully inserted Users");

	}
}
