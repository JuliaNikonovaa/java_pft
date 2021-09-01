package ru.stqa.pft.addressbook.model;

public class ContactData {
	private final String name;
	private final String mobile;
	private final String email;
	private final String lastname;

	public ContactData(String name, String mobile, String email, String lastname) {
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.lastname = lastname;
	}

	public String getName() {
		return name;
	}

	public String getMobile() {
		return mobile;
	}

	public String getEmail() {
		return email;
	}

	public String getLastname() {
		return lastname;
	}
}
