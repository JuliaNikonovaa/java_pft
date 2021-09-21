package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

	@BeforeMethod
	public void ensurePreconditions() {
		app.сontact().returnToContactPage();
		if (app.сontact().contactList().size() == 0) {
			app.сontact().gotoAddNewContactPage();
			app.сontact().create(new ContactData().
					withLastname("Nikonova").withName("Julia").withEmail("j.n@kx.com").withMobile("89119158254").withGroup("test1"), true);
		}
	}

	@Test
	public void testContactModification() {

		List<ContactData> before = app.сontact().contactList();
		int index = before.size() - 1;
		ContactData contact = new ContactData()
				.withId(before.get(index).getId()).withLastname("Nikonova").withName("Julia3").withEmail("j.n@kx.com").withMobile("89119158254").withGroup(null);
		app.сontact().modify(index, contact);
		//app.logout();
		List<ContactData> after = app.сontact().contactList();
		Assert.assertEquals(after.size(), before.size());

		before.remove(index);
		before.add(contact);
		Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(before, after);
	}
	}
