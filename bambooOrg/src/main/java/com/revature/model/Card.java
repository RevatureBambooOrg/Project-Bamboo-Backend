package com.revature.model;

import java.util.List;
import java.util.Map;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Card {

	private int ID;
	private List<String> tag;
	private List<String> relevantTopic;
	private List<Integer> relevantCards;
	private String category;
	private String topic;
	private String question;
	private String answer;
	private String example;
	private String URL;
	private Users creator;// Maybe only store the username?

	@SuppressWarnings("unchecked")
	public Card(Map values) {
		ID = (int) values.getOrDefault("ID", 0);
		tag = (List<String>) values.getOrDefault("tag", null);
		relevantTopic = (List<String>) values.getOrDefault("relevantTopic", null);
		relevantCards = (List<Integer>) values.getOrDefault("relevantCards", null);
		category = (String) values.getOrDefault("category", null);
		topic = (String) values.getOrDefault("topic", null);
		question = (String) values.getOrDefault("question", null);
		answer = (String) values.getOrDefault("answer", null);
		example = (String) values.getOrDefault("example", null);
		URL = (String) values.getOrDefault("URL", null);
		creator = (Users) values.getOrDefault("creator", null);
	}

	public String JSON1() {
		JSONObject obj = new JSONObject();
		try {
			obj.put("ID", ID);
			obj.put("question", question);
			obj.put("answer", answer);
			obj.put("relevantTopic", relevantTopic);
			obj.put("URL", URL);
			obj.put("relevantCards", relevantCards);
			obj.put("creator.username", creator.getUsername());
		} catch (JSONException e) {
			// IDK when this will throw.
			// Will fix if this throws an error
			e.printStackTrace();
		}
		return obj.toString();
		/*
		 * String JSON="{\n" + "ID: \""+ID+"\",\n" + "Question: \""+question+"\",\n" +
		 * "Answer: \"" +answer+"\",\n" + "Relevant topics: ["; for(int i=0; i<
		 * relevantTopic.size(); i++) { JSON+= "\""+relevantTopic.get(i)+"\","; }
		 * JSON.substring(0,JSON.length()-1); JSON+= "],"
		 * +"Extra resources: \""+URL+"\"," + "Relevant cards: ["; for(int i=0; i<
		 * relevantCards.size(); i++) { JSON+= "\""+relevantCards.get(i)+"\","; }
		 * JSON+="Who created: \""+creator.username+"\"}"; return JSON;
		 */
		/*
		 * {
			 Category: "java"
			 {
			  ID or Name: "Card identifier"
			  {
			   Question: "jshdijfdsl"
			   Answer: "sjdbfnskjdnf"
			   Relevant topics: [Jdbc, servlets]
			   Extra resources: "fsjkdjf.URL"
			   Relevant cards: [jdbc 23 45 68, java 35]
			   who created: "fsdjfsdjfd"
			  }
			  ID or Name: "Card identifier"
			   {
			     Question: "jshdijfdsl"
			     Answer: "sjdbfnskjdnf"
			     Relevant topics: [Jdbc, servlets]
			     Extra resources: "fsjkdjf.URL"
			    }
			 }
			}
			Category:"number tracker"
			{
			 id: literally the last number
			}

		 *
		 * */
	}

	public String JSON2() {
		JSONObject obj = new JSONObject();
		try {
			obj.put("ID", ID);
			obj.put("Card", this);
		} catch (JSONException e) {
			// IDK when this will throw.
			// Will fix if this throws an error
			e.printStackTrace();
		}
		return obj.toString();
	}

	public String JSON3() {
		JSONObject obj = new JSONObject();
		try {
			obj.put("User", creator.getUsername());
			obj.put("Card", this);
		} catch (JSONException e) {
			// IDK when this will throw.
			// Will fix if this throws an error
			e.printStackTrace();
		}
		return obj.toString();
	}



}
