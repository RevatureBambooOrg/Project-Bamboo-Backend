package com.revature.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data // generates getters/setter, toString, hashCode, and equals() method
// automatically
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Users {
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank
	private String username;

	@NotBlank
	private String pwd;

	@Column(unique = true)
	@Email
	private String email;

	@NotBlank
	private String fName;

	@NotBlank
	private String lName;

	public Users(@NotBlank String username, @NotBlank String pwd, @Email String email, @NotBlank String fName,
			@NotBlank String lName) {
		this.username = username;
		this.pwd = pwd;
		this.email = email;
		this.fName = fName;
		this.lName = lName;
	}

	public void Hasher() {
		MessageDigest digest;
		String phrase = pwd;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(phrase.getBytes(StandardCharsets.UTF_8));
			pwd = Base64.getEncoder().encodeToString(hash);

		} catch (NoSuchAlgorithmException e) {

		}
	}

}
