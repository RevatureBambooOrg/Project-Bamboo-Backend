package com.revature.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.data.UserRepository;
import com.revature.model.Users;

@Repository
public class UsersDao {

	@Autowired
	private UserRepository userRepo;

	public int insert(Users user) {
		Users i = userRepo.save(user);

		System.out.println("Insertion in Dao:");
		System.out.println(i);
		return i.getId();
	}

	public List<Users> selectAll() {
		return userRepo.findAll();
	}

	public Users selectById(int ID) {
		System.out.println("ID in Dao: " + ID);
		System.out.println(userRepo);
		return userRepo.getById(ID);
	}

	public void update(Users user) {
		userRepo.save(user);
	}

	public Users selectByUsername(String username) {
		System.out.println("Username in Dao: " + username);
		Optional<Users> test = userRepo.findByUsername(username);
		return test.orElse(null);
	}

	public Users selectByEmail(String email) {
		System.out.println("Email in Dao: " + email);
		Optional<Users> test = userRepo.findByEmail(email);
		return test.orElse(null);
	}

	public int removeById(int Id) {
		try {
			userRepo.deleteById(Id);
			return Id;
		} catch (IllegalArgumentException e) {
			return 0;
		}
	}
}
