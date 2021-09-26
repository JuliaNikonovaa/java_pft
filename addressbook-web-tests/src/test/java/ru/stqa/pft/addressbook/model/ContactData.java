package ru.stqa.pft.addressbook.model;

import java.io.File;

public class ContactData {
	private int id = Integer.MAX_VALUE;
	private String name;
	private String mobile;
	private String email;
	private String homePhone;
	private String workPhone;
	private String address;
	private String email2;
	private String email3;
	private String lastname;
	private String group;
	private String allPhones;
	private String allEmails;

	public File getPhoto() {
		return photo;
	}

	public ContactData withPhoto(File photo) {
		this.photo = photo;
		return this;
	}

	private File photo;

	public ContactData withAddress(String address) {
		this.address = address;
		return this;
	}



	public ContactData withAllEmails(String allEmails) {
		this.allEmails = allEmails;
		return this;
	}

	public ContactData withEmail2(String email2) {
		this.email2 = email2;
		return this;
	}

	public ContactData withEmail3(String email3) {
		this.email3 = email3;
		return this;
	}


	public ContactData withAllPhones(String allPhones) {
		this.allPhones = allPhones;
		return this;
	}



	public ContactData withWorkPhone(String workPhone) {
		this.workPhone = workPhone;
		return this;
	}

	public ContactData withHomePhone(String homePhone) {
		this.homePhone = homePhone;
		return this;
	}

	public ContactData withId(int id) {
		this.id = id;
		return this;
	}

	public ContactData withName(String name) {
		this.name = name;
		return this;
	}

	public ContactData withMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}

	public ContactData withEmail(String email) {
		this.email = email;
		return this;
	}

	public ContactData withLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public ContactData withGroup(String group) {
		this.group = group;
		return this;
	}



	public int getId() {
		return id;
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

	public String getHomePhone() {
		return homePhone;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public String getGroup() {
		return group;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail2() {
		return email2;
	}

	public String getEmail3() {
		return email3;
	}

	public String getAllPhones() {
		return allPhones;
	}

	public String getAllEmails() {
		return allEmails;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ContactData that = (ContactData) o;

		if (id != that.id) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
		return result;
	}



	@Override
	public String toString() {
		return "ContactData{" +
						"id='" + id + '\'' +
						", name='" + name + '\'' +
						", lastname='" + lastname + '\'' +
						'}';
	}

}
