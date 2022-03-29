package com.revature.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Users;
import com.revature.service.UserService;

@RestController
@RequestMapping("/users") // all functionality is available at http://localhost:5000/api/users...
@CrossOrigin(origins = "*", allowedHeaders = "*") // allows this usercontroller to be hit by other resources
public class UserController {

	@Autowired // inject the service dependency into our controller class
	private UserService userServ;

	@PostMapping("/add")
	public ResponseEntity<String> addUser(@RequestBody Users u) {
		return ResponseEntity.ok(userServ.register(u));
	}

	@GetMapping("/login")
	public ResponseEntity<String> logInUser(@RequestBody Users u) {
		return ResponseEntity.ok(userServ.login(u));
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<Users> getUserById(@PathVariable("id") int id) {
		return ResponseEntity.ok(userServ.getUserById(id));
	}

	@GetMapping("/getByUsername/{username}")
	public ResponseEntity<Users> getUserByUsername(@PathVariable("username") String username) {
		return ResponseEntity.ok(userServ.getUserByUsername(username));
	}

	@GetMapping("/getByEmail/{email}")
	public ResponseEntity<Users> getUserByEmail(@PathVariable("email") String email) {
		return ResponseEntity.ok(userServ.getUserByUsername(email));
	}

	@DeleteMapping("/{id}")
	public void removeUser(@PathVariable("id") int id) {

		userServ.deleteUserById(id);
	}
}
