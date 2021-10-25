package ru.stqa.pft.mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "mantis_user_table")

public class UserData {

	@Id
	int id ;

	@Column(name = "username")
	@Type(type = "text")
	private String username;

	@Column(name = "email")
	@Type(type = "text")
	private String email;

	@Column(name = "password")
	@Type(type = "text")
	private String password;

	public int getId() {
		return id;
	}

	public UserData withId(int id) {
		this.id = id;
		return this;
	}

	@Override
	public String toString() {
		return "UserData{" +
						"id=" + id +
						", username='" + username + '\'' +
						", email='" + email + '\'' +
						", password='" + password + '\'' +
						'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserData userData = (UserData) o;

		if (id != userData.id) return false;
		return username != null ? username.equals(userData.username) : userData.username == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (username != null ? username.hashCode() : 0);
		return result;
	}

	public String getUsername() {
		return username;
	}

	public UserData withUsername(String username) {
		this.username = username;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public UserData withEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UserData withPassword(String password) {
		this.password = password;
		return this;
	}


}