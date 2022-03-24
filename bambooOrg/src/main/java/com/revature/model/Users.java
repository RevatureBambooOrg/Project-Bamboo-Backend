package com.revature.model;

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
	int id;

	@NotBlank
	String username;

	@NotBlank
	String pwd;

	@Column(unique = true)
	@Email
	String email;

	@NotBlank
	String fName;

	@NotBlank
	String lName;

	public Users(@NotBlank String username, @NotBlank String pwd, @Email String email, @NotBlank String fName,
			@NotBlank String lName) {
		this.username = username;
		this.pwd = pwd;
		this.email = email;
		this.fName = fName;
		this.lName = lName;
	}

}
