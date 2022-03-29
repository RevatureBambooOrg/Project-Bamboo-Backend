package com.revature.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.service.CardService;

@RestController
@RequestMapping("/cards") // all functionality is available at http://localhost:5000/api/users...
@CrossOrigin(origins = "*", allowedHeaders = "*") // allows this usercontroller to be hit by other resources
public class CardController {

	@Autowired // inject the service dependency into our controller class
	private CardService cardServ;

	/*
	 * @PostMapping("/add") public ResponseEntity<Card> addCard(@Valid @RequestBody
	 * Card u) { // @Valid ensures that the user object accepted // abides by the
	 * Validations we set up on its // properties
	 *
	 * return ResponseEntity.ok(cardServ.add(u)); // what happens if a user tries to
	 * add a malformed JSON object???? // we will use power of AOP to intercept the
	 * response that's sent back so the // user/client // knows how to fix it. }
	 */

}
