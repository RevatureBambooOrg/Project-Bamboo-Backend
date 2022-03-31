package com.revature;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.model.Users;
import com.revature.service.UserService;

public class Driver {

	@Autowired
	private static UserService cdao;

	public static void main(String[] args) {
		/*
		 * System.out.println("Running"); Users c1 = new Users(); c1.setFName("Bob");
		 * c1.setLName("Jones"); c1.setEmail("Myemail@email.com");
		 * c1.setUsername("SuperkoolMan"); c1.setPwd("LetMeIn");
		 * System.out.println("Succesfully created"); cdao.register(c1);
		 * System.out.println("Succesfully inserted Users"); String s2 = cdao.login(c1);
		 * System.out.println(s2);
		 */
		List<Users> people = cdao.getAllUsers();
		System.out.println(people);
	}
}
