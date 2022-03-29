package com.revature.service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.revature.dao.UsersDao;
import com.revature.model.Users;

public class UserService {
	private static UsersDao cdao = new UsersDao();
	private Validator validator;

	public String register(Users user) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		Set<ConstraintViolation<Users>> violations = validator.validate(user);
		if (!violations.isEmpty() || (cdao.selectByUsername(user.getUsername()) != null)) {
			return "";
		}
		if (cdao.insert(user) != 0) {
			return user.getUsername();
		}
		return "";
	}

	public String login(Users user) {
		Users compared=cdao.selectByUsername(user.getUsername());
		if ((compared != null) && compared.getPwd().equals(user.getPwd())) {
			return compared.getUsername();
		}
		compared = cdao.selectByEmail(user.getEmail());
		if ((compared != null) && compared.getPwd().equals(user.getPwd())) {
			return compared.getUsername();
		}
		return "";
	}


	public Users getUserById(int Id) {
		return cdao.selectById(Id);
	}

	public Users getUserByUsername(String username) {
		return cdao.selectByUsername(username);
	}

	public Users getUserByEmail(String email) {
		return cdao.selectByEmail(email);
	}

	public void deleteUserById(int id) {
		cdao.removeById(id);
	}

}
