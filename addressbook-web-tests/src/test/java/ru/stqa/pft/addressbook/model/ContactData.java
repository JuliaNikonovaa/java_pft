package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;


import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="addressbook")

public class ContactData {

	@Id
	@Column (name = "id")
	private int id = Integer.MAX_VALUE;

	@Expose
	@Column (name = "firstname")
	private String name;

	@Expose
	@Column (name = "mobile")
	@Type(type = "text")
	private String mobile;

	@Expose
	@Column (name = "email")
	@Type(type = "text")
  private String email;

	@Column (name = "home")
	@Type(type = "text")
	private String homePhone;

	@Column (name = "work")
	@Type(type = "text")
	private String workPhone;

	@Column (name = "address")
	@Type(type = "text")
	private String address;

	@Column (name = "email2")
	@Type(type = "text")
	private String email2;

	@Column (name = "email3")
	@Type(type = "text")
	private String email3;

	@Expose
	@Column (name = "lastname")
	private String lastname;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "address_in_groups",
					joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))

	private Set<GroupData> groups = new HashSet<GroupData>();



	@Expose
	transient private String allPhones;
	transient private String allEmails;

	@Override
	public String toString() {
		return "ContactData{" +
						"id=" + id +
						", name='" + name + '\'' +
						", lastname='" + lastname + '\'' +
						'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ContactData that = (ContactData) o;

		if (id != that.id) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
		if (email != null ? !email.equals(that.email) : that.email != null) return false;
		return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
		return result;
	}


	@Type(type = "text")
	transient private String photo;

	public File getPhoto() {
		if (photo != null) {
			return new File(photo);
		} else {
			return null;
		}
	}

	public ContactData withPhoto(File photo) {
		this.photo = photo.getPath();
		return this;
	}



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

	public Groups getGroups() {
		return new Groups(groups);
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


	public ContactData inGroup(GroupData group) {
		groups.add(group);
		return this;
	}
}
