package ru.stqa.pft.addressbook.model;

public class ContactData {
	public void setId(int id) {
		this.id = id;
	}

	private int id;
	private final String name;
	private final String mobile;
	private final String email;
	private final String lastname;
	private String group;

	public int getId() {
		return id;
	}

	public ContactData(String lastname, String name, String email, String mobile, String group) {
		this.id = Integer.MAX_VALUE;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.lastname = lastname;
		this.group = group;
	}

	public ContactData(int id, String lastname, String name, String email, String mobile, String group) {
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.lastname = lastname;
		this.group = group;
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
