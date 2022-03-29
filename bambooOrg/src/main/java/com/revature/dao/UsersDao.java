package com.revature.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.data.UserRepository;
import com.revature.model.Users;

public class UsersDao {

	@Autowired
	private UserRepository userRepo;

	public int insert(Users user) {
		Users i = userRepo.save(user);
		return i.getId();
	}

	public List<Users> selectAll() {
		return userRepo.findAll();
	}

	public Users selectById(int ID) {
		return userRepo.getById(ID);
	}

	public void update(Users user) {
		userRepo.save(user);
	}

	public Users selectByUsername(String username) {
		Optional<Users> test = userRepo.findByUsername(username);
		return test.orElse(null);
	}

	public Users selectByEmail(String email) {
		Optional<Users> test = userRepo.findByEmail(email);
		return test.orElse(null);
	}

	public void removeById(int Id) {
		userRepo.deleteById(Id);
	}
}
