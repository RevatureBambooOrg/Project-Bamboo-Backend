package com.revature.service;

import com.revature.model.Card;

public class CardService {

	public String JSON1(Card card) {
		return card.JSON1();
	}

	public String JSON2(Card card) {
		return card.JSON2();
	}

	public String JSON3(Card card) {
		return card.JSON3();
	}
}
