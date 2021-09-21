package ru.stqa.pft.addressbook.model;

public class ContactData {
	private int id = Integer.MAX_VALUE;
	private String name;
	private String mobile;
	private String email;

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

	private String lastname;
	private String group;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ContactData that = (ContactData) o;

		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
		return result;
	}

	public String getGroup() {
		return group;
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
